package com.jecrc.foundation.expense_tracker.encryptor;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class Encryptor {

  private StrongPasswordEncryptor strongPasswordEncryptor;

  @PostConstruct
  public void init() {
    this.strongPasswordEncryptor = new StrongPasswordEncryptor();
  }

  public String getEncryptedPassword(String password) {
    return strongPasswordEncryptor.encryptPassword(password);
  }

  public boolean checkEncryptedPassword(String password, String encryptedPassword) {
    return strongPasswordEncryptor.checkPassword(password, encryptedPassword);
  }
}
