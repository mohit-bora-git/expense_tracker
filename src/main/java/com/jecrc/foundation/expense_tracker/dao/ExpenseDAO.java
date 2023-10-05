package com.jecrc.foundation.expense_tracker.dao;

import com.jecrc.foundation.expense_tracker.dbos.ExpenseDBO;
import com.jecrc.foundation.expense_tracker.mapper.ExpenseMapper;
import com.jecrc.foundation.expense_tracker.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ExpenseDAO {

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;

  private ExpenseMapper getExpenseMapper() {
    return sqlSessionTemplate.getMapper(ExpenseMapper.class);
  }

  public void save(ExpenseDBO expenseDbo) {
    getExpenseMapper().save(expenseDbo);
  }

  public void update(ExpenseDBO expenseDbo) {
    getExpenseMapper().update(expenseDbo);
  }

  public ExpenseDBO findById(Long id) {
    return getExpenseMapper().findById(id);
  }

  public List<ExpenseDBO> findExpensesByDateRange(Date startDate, Date endDate, Long userId) {
    return getExpenseMapper().findExpensesWithDateRange(startDate, endDate, userId);
  }

  public List<ExpenseDBO> findExpenseByDate(Date date, Long userId) {
    return getExpenseMapper().findExpensesWithDate(date, userId);
  }

  public <T> List<T> findAll() {
    return null;
  }
}
