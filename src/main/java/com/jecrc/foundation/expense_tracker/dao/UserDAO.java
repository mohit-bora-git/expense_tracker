package com.jecrc.foundation.expense_tracker.dao;

import com.jecrc.foundation.expense_tracker.dbos.UserDBO;
import com.jecrc.foundation.expense_tracker.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAO extends BaseDAO<UserDBO> {

  private UserMapper getUserMapper() {
    return sqlSessionTemplate.getMapper(UserMapper.class);
  }

  @Override
  public UserDBO save(UserDBO user) {
    getUserMapper().save(user);
    return user;
  }

  @Override
  public Integer update(UserDBO userDbo) {
    return getUserMapper().update(userDbo);
  }

  @Override
  public UserDBO findById(Long id) {
    return getUserMapper().findById(id);
  }

  @Override
  public List<UserDBO> findAll() {
    return getUserMapper().findAll();
  }

  public UserDBO findUserByUsername(String username) {
    return getUserMapper().findByUsername(username);
  }

  public UserDBO findUserByEmail(String email) {
    return getUserMapper().findByEmail(email);
  }

  public Integer updateProfile(Long id, String imageUrl) {
    return getUserMapper().updateProfileImage(id, imageUrl);
  }

  public Integer updateTransactionLimit(Long id, Double transactionLimit) {
    return getUserMapper().updateTransactionLimit(id, transactionLimit);
  }
}
