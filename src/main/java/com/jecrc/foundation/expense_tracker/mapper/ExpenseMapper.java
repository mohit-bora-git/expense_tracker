package com.jecrc.foundation.expense_tracker.mapper;

import com.jecrc.foundation.expense_tracker.dbos.ExpenseDBO;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ExpenseMapper {

  @Insert(
      "INSERT INTO expense (date,expense_type,transaction_amount,transaction_type,description,user_id) VALUES(#{date},#{expenseType},#{transactionAmount},#{transactionType},#{description},#{userId})")
  void save(ExpenseDBO expense);

  @Update(
      "UPDATE expense SET expense_type=#{expenseType},transaction_amount=#{transactionAmount},transaction_type=#{transactionType},description=#{description} WHERE id=#{id}")
  void update(ExpenseDBO expense);

  @Select("SELECT * FROM expense WHERE user_id=#{userId} AND date=#{date} ORDER BY created_at DESC")
  List<ExpenseDBO> findExpensesWithDate(Date date, Long userId);

  @Select(
      "SELECT * FROM expense WHERE user_id=#{userId} AND date BETWEEN #{startDate} AND #{endDate} ORDER BY created_at")
  List<ExpenseDBO> findExpensesWithDateRange(Date startDate, Date endDate, Long userId);

  @Select("SELECT * FROM expense WHERE id=#{id} ORDER BY create_at DESC")
  ExpenseDBO findById(Long id);

  @Select("SELECT SUM(transaction_amount) FROM expense GROUP BY (user_id) HAVING user_id=#{id} AND date BETWEEN #{startDate} AND #{endDate}")
  Double findTotalMonthlyExpense(Long id,Date startDate,Date endDate);

  @Delete("DELETE FROM expense WHERE id=#{id}")
  void delete(Long id);
}
