package com.jecrc.foundation.expense_tracker.dao;

import com.jecrc.foundation.expense_tracker.dbos.ExpenseDbo;
import com.jecrc.foundation.expense_tracker.mapper.ExpenseMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.List;

@Service
public class ExpenseDao {

  private final SqlSessionTemplate sqlSessionTemplate;

  @Autowired
  private ExpenseDao(SqlSessionTemplate sqlSessionTemplate){
    this.sqlSessionTemplate=sqlSessionTemplate;
  }

  private ExpenseMapper expenseMapper;

  @PostConstruct
  public void init(){
    expenseMapper=sqlSessionTemplate.getMapper(ExpenseMapper.class);
  }

  public void save(ExpenseDbo expenseDbo) {
    expenseMapper.save(expenseDbo);
  }

  public void update(ExpenseDbo expenseDbo) {
    expenseMapper.update(expenseDbo);
  }

  public ExpenseDbo findById(Long id) {
    return expenseMapper.findById(id);
  }

  public List<ExpenseDbo> findExpensesByDateRange(Date startDate, Date endDate, Long userId) {
    return expenseMapper.findExpensesWithDateRange(startDate, endDate, userId);
  }

  public List<ExpenseDbo> findExpenseByDate(Date date, Long userId) {
    return expenseMapper.findExpensesWithDate(date, userId);
  }

  public Double findTotalMonthlyExpense(Long id, Date startDate,Date endDate){
    return expenseMapper.findTotalMonthlyExpense(id,startDate,endDate);
  }
}
