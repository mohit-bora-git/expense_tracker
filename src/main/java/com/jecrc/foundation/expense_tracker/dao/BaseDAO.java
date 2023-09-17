package com.jecrc.foundation.expense_tracker.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class BaseDAO<T> {

  @Autowired
  protected SqlSessionTemplate sqlSessionTemplate;

  public abstract T save(T object);

  public abstract Integer update(T object);

  public abstract T findById(Long id);

  public abstract <T> List<T> findAll();

}
