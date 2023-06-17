package com.jecrc.foundation.expense_tracker.dao;

import com.jecrc.foundation.expense_tracker.dbos.UserDBO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAO extends BaseDAO<UserDBO>{
  @Override
  public UserDBO save(UserDBO userDbo) {
    return null;
  }

  @Override
  public Integer update(UserDBO userDbo) {
    return null;
  }

  @Override
  public UserDBO findById(Long id) {
    return null;
  }

  @Override
  public <T> List<T> findAll() {
    return null;
  }
}
