package com.jecrc.foundation.expense_tracker.mapper;

import com.jecrc.foundation.expense_tracker.dbos.ExpenseDBO;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ExpenseMapper {

  @Insert("INSERT INTO expense (date,expense_type,transaction_amount,transaction_type,description,user_id) VALUES(#{date},#{expenseType},#{transactionAmount},#{transactionType},#{description},#{userId})")
  void save(ExpenseDBO expense);

  @Update("UPDATE expense SET expense_type=#{expenseType},transaction_amount=#{transactionAmount},transaction_type=#{transactionType},description=#{description}")
  void update(ExpenseDBO expense);

  @Select("SELECT * FROM expense WHERE date=#{date} ORDER BY updated_at DESC")
  List<ExpenseDBO> findExpensesWithDate(Date date);

  @Select("SELECT * FROM expense WHERE date BETWEEN #{startDate} AND #{endDate}")
  List<ExpenseDBO> findExpensesWithDateRange(Date startDate,Date endDate);

  @Delete("DELETE FROM expense WHERE id=#{id}")
  void delete(Long id);
}
