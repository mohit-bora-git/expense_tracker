package com.jecrc.foundation.expense_tracker.helper_service;

import com.jecrc.foundation.expense_tracker.config.ConfigProps;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseErrorMessage;
import com.jecrc.foundation.expense_tracker.converter.DataConverter;
import com.jecrc.foundation.expense_tracker.dao.UserDAO;
import com.jecrc.foundation.expense_tracker.dbos.UserDBO;
import com.jecrc.foundation.expense_tracker.dos.UserDO;
import com.jecrc.foundation.expense_tracker.exception.TokenAuthorizationFailedException;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Service
@Slf4j
public class AccessTokenService {

  @Autowired
  private ConfigProps config;

  @Autowired
  private UserDAO userDao;

  public String generateToken(Long id) {
    Date jwtIssuedAt = new Date(System.currentTimeMillis());
    Date jwtExpiredAt = new Date(System.currentTimeMillis() + config.getAccessTokenMaxLife());
    return Jwts.builder().setSubject(config.getJwtAuthSubject()).setIssuer(config.getJwtIssuer())
        .claim("id", id).setIssuedAt(jwtIssuedAt).setExpiration(jwtExpiredAt).signWith(
            new SecretKeySpec(Base64.getDecoder().decode(config.getSecretKey()),
                SignatureAlgorithm.HS512.getValue()), SignatureAlgorithm.HS512).compact();
  }

  public UserDO verifyAccessToken(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    log.debug("accessToken: {}", authorizationHeader);
    if (StringUtils.isNullOrEmpty(authorizationHeader)) {
      throw new TokenAuthorizationFailedException(
          HttpResponseErrorCode.AUTHORIZATION_HEADER_IS_MISSING,
          HttpResponseErrorMessage.AUTHORIZATION_HEADER_IS_MISSING);
    }
    if (!authorizationHeader.contains("Bearer ")) {
      throw new TokenAuthorizationFailedException(
          HttpResponseErrorCode.INVALID_AUTHORIZATION_HEADER,
          HttpResponseErrorMessage.INVALID_AUTHORIZATION_HEADER);
    }
    String accessToken = authorizationHeader.replace("Bearer ", "");
    Claims claims = decodeToken(accessToken);
    Date expDate = claims.getExpiration();
    boolean isTokenExpired = expDate.getTime() < System.currentTimeMillis();
    if (isTokenExpired) {
      throw new TokenAuthorizationFailedException(HttpResponseErrorCode.TOKEN_EXPIRED_OR_INVALID,
          HttpResponseErrorMessage.TOKEN_EXPIRED_OR_INVALID);
    }
    Long userId = claims.get("id", Long.class);
    return getUser(userId);
  }

  private Claims decodeToken(String token) {
    return Jwts.parserBuilder().setSigningKey(config.getSecretKey()).build().parseClaimsJwt(token)
        .getBody();
  }

  private UserDO getUser(Long id) {
    UserDBO user = userDao.findById(id);
    if (user == null) {
      throw new TokenAuthorizationFailedException(HttpResponseErrorCode.TOKEN_EXPIRED_OR_INVALID,
          HttpResponseErrorMessage.TOKEN_EXPIRED_OR_INVALID);
    }
    return DataConverter.convertUserDboToUserDo(user);
  }
}
