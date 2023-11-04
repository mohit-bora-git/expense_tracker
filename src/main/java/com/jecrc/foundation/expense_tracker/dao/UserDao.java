package com.jecrc.foundation.expense_tracker.dao;

import com.jecrc.foundation.expense_tracker.dbos.UserDbo;
import com.jecrc.foundation.expense_tracker.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserDao {

  private UserMapper userMapper;

  private final SqlSessionTemplate sqlSessionTemplate;

  @Autowired
  private UserDao(SqlSessionTemplate sqlSessionTemplate){
    this.sqlSessionTemplate=sqlSessionTemplate;
  }

  @PostConstruct
  public void init(){
    userMapper=sqlSessionTemplate.getMapper(UserMapper.class);
  }

  public void save(UserDbo user) {
    userMapper.save(user);
  }

  public void update(UserDbo userDbo) {
    userMapper.update(userDbo);
  }

  public UserDbo findById(Long id) {
    return userMapper.findById(id);
  }

  public List<UserDbo> findAll() {
    return userMapper.findAll();
  }

  public UserDbo findUserByUsername(String username) {
    return userMapper.findByUsername(username);
  }

  public UserDbo findUserByEmail(String email) {
    return userMapper.findByEmail(email);
  }

  public void updateProfile(Long id, String imageUrl) {
    userMapper.updateProfileImage(id, imageUrl);
  }

  public void updateTransactionLimit(Long id, Double transactionLimit) {
    userMapper.updateTransactionLimit(id, transactionLimit);
  }
}
