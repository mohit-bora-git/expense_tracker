package com.jecrc.foundation.expense_tracker.mapper;

import com.jecrc.foundation.expense_tracker.dbos.UserDbo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

  @Insert("INSERT INTO users (email,password) VALUES (#{email},#{password})")
  void save(UserDbo userDbo);

  @Select("SELECT * FROM users WHERE email=#{email}")
  UserDbo findByEmail(String email);

  @Select("SELECT * FROM users WHERE id=#{id}")
  UserDbo findById(Long id);

  @Select("SELECT * FROM users")
  List<UserDbo> findAll();

  @Select("SELECT * FROM users WHERE username=#{username}")
  UserDbo findByUsername(String username);

  @Update("UPDATE users SET name=#{name}, username=#{username} WHERE id=#{id}")
  void update(UserDbo userDbo);

  @Update("UPDATE users SET profile_image_url=#{profileImageUrl} WHERE id=#{id}")
  void updateProfileImage(Long id, String imageUrl);

  @Update("UPDATE users SET transaction_limit=#{limit} WHERE id=#{id}")
  void updateTransactionLimit(Long id, Double limit);
}
