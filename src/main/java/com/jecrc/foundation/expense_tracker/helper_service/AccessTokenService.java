package com.jecrc.foundation.expense_tracker.helper_service;

import com.jecrc.foundation.expense_tracker.config.ConfigProps;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseCode;
import com.jecrc.foundation.expense_tracker.constants.HttpResponseMessage;
import com.jecrc.foundation.expense_tracker.converter.DataConverter;
import com.jecrc.foundation.expense_tracker.dao.UserDao;
import com.jecrc.foundation.expense_tracker.dbos.UserDbo;
import com.jecrc.foundation.expense_tracker.dos.UserDo;
import com.jecrc.foundation.expense_tracker.exception.TokenAuthorizationFailedException;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Service
@Slf4j
public class AccessTokenService {

  private final ConfigProps config;

  private final UserDao userDao;

  private AccessTokenService(ConfigProps config, UserDao userDao){
    this.config=config;
    this.userDao=userDao;
  }

  public String generateToken(Long id) {
    Date jwtIssuedAt = new Date(System.currentTimeMillis());
    Date jwtExpiredAt = new Date(System.currentTimeMillis() + config.getAccessTokenMaxLife());
    return Jwts.builder().setSubject(config.getJwtAuthSubject()).setIssuer(config.getJwtIssuer())
        .claim("id", id).setIssuedAt(jwtIssuedAt).setExpiration(jwtExpiredAt).signWith(
            new SecretKeySpec(Base64.getDecoder().decode(config.getSecretKey()),
                SignatureAlgorithm.HS512.getValue()), SignatureAlgorithm.HS512).compact();
  }

  public UserDo verifyAccessToken(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    log.debug("accessToken: {}", authorizationHeader);
    if (StringUtils.isNullOrEmpty(authorizationHeader)) {
      throw new TokenAuthorizationFailedException(
          HttpResponseCode.AUTHORIZATION_HEADER_IS_MISSING,
          HttpResponseMessage.AUTHORIZATION_HEADER_IS_MISSING);
    }
    if (!authorizationHeader.contains("Bearer ")) {
      throw new TokenAuthorizationFailedException(
          HttpResponseCode.INVALID_AUTHORIZATION_HEADER,
          HttpResponseMessage.INVALID_AUTHORIZATION_HEADER);
    }
    String accessToken = authorizationHeader.replace("Bearer ", "");
    Claims claims = decodeToken(accessToken);
    Date expDate = claims.getExpiration();
    boolean isTokenExpired = expDate.getTime() < System.currentTimeMillis();
    if (isTokenExpired) {
      throw new TokenAuthorizationFailedException(HttpResponseCode.TOKEN_EXPIRED_OR_INVALID,
          HttpResponseMessage.TOKEN_EXPIRED_OR_INVALID);
    }
    Long userId = claims.get("id", Long.class);
    return getUser(userId);
  }

  private Claims decodeToken(String token) {
    return Jwts.parserBuilder().setSigningKey(config.getSecretKey()).build().parseClaimsJwt(token)
        .getBody();
  }

  private UserDo getUser(Long id) {
    UserDbo user = userDao.findById(id);
    if (user == null) {
      throw new TokenAuthorizationFailedException(HttpResponseCode.TOKEN_EXPIRED_OR_INVALID,
          HttpResponseMessage.TOKEN_EXPIRED_OR_INVALID);
    }
    return DataConverter.convertUserDboToUserDo(user);
  }
}
