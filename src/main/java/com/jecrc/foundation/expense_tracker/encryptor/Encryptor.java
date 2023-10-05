package com.jecrc.foundation.expense_tracker.encryptor;

import jakarta.annotation.PostConstruct;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Configuration;

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
