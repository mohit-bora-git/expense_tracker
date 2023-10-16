package com.jecrc.foundation.expense_tracker.dao;

import com.jecrc.foundation.expense_tracker.dbos.ExpenseDBO;
import com.jecrc.foundation.expense_tracker.mapper.ExpenseMapper;
import com.jecrc.foundation.expense_tracker.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.List;

@Service
public class ExpenseDAO {

  private ExpenseMapper expenseMapper;

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;

  @PostConstruct
  public void init(){
    expenseMapper=sqlSessionTemplate.getMapper(ExpenseMapper.class);
  }

  public void save(ExpenseDBO expenseDbo) {
    expenseMapper.save(expenseDbo);
  }

  public void update(ExpenseDBO expenseDbo) {
    expenseMapper.update(expenseDbo);
  }

  public ExpenseDBO findById(Long id) {
    return expenseMapper.findById(id);
  }

  public List<ExpenseDBO> findExpensesByDateRange(Date startDate, Date endDate, Long userId) {
    return expenseMapper.findExpensesWithDateRange(startDate, endDate, userId);
  }

  public List<ExpenseDBO> findExpenseByDate(Date date, Long userId) {
    return expenseMapper.findExpensesWithDate(date, userId);
  }

  public Double findTotalMonthlyExpense(Long id, Date startDate,Date endDate){
    return expenseMapper.findTotalMonthlyExpense(id,startDate,endDate);
  }
}
