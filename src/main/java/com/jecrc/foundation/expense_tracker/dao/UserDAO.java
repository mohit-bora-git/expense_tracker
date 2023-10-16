package com.jecrc.foundation.expense_tracker.dao;

import com.jecrc.foundation.expense_tracker.dbos.UserDBO;
import com.jecrc.foundation.expense_tracker.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserDAO {

  private UserMapper userMapper;

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;

  @PostConstruct
  public void init(){
    userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
  }

  public void save(UserDBO user) {
    userMapper.save(user);
  }

  public void update(UserDBO userDbo) {
    userMapper.update(userDbo);
  }

  public UserDBO findById(Long id) {
    return userMapper.findById(id);
  }

  public List<UserDBO> findAll() {
    return userMapper.findAll();
  }

  public UserDBO findUserByUsername(String username) {
    return userMapper.findByUsername(username);
  }

  public UserDBO findUserByEmail(String email) {
    return userMapper.findByEmail(email);
  }

  public void updateProfile(Long id, String imageUrl) {
    userMapper.updateProfileImage(id, imageUrl);
  }

  public void updateTransactionLimit(Long id, Double transactionLimit) {
    userMapper.updateTransactionLimit(id, transactionLimit);
  }
}
