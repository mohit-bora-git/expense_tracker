package com.jecrc.foundation.expense_tracker.dao;

import com.jecrc.foundation.expense_tracker.dbos.ExpenseDBO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseDAO extends BaseDAO<ExpenseDBO> {
  @Override
  public ExpenseDBO save(ExpenseDBO expenseDbo) {
    return null;
  }

  @Override
  public Integer update(ExpenseDBO expenseDbo) {
    return null;
  }

  @Override
  public ExpenseDBO findById(Long id) {
    return null;
  }

  @Override
  public <T> List<T> findAll() {
    return null;
  }
}
