package com.jecrc.foundation.expense_tracker.helper;

import com.jecrc.foundation.expense_tracker.configurator.Configurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigPropertyService {

  @Autowired
  private Configurator config;

  public String getMySQLDriver() {
    return config.getString("mysql.driver");
  }

  public String getMySQLUsername() {
    return config.getString("mysql.master.username");
  }

  public String getMySQLPassword() {
    return config.getString("mysql.master.password");
  }

  public String getMySQLDBURI() {
    return config.getString("mysql.master.db.url");
  }

  public String getMySQLDBName() {
    return config.getString("mysql.master.db.name");
  }

  public Integer getMySQLPort() {
    return config.getInt("mysql.master.port");
  }

  public Integer getMySQLActiveConnections() {
    return config.getInt("mysql.master.active.connections");
  }

  public Integer getMySQLMaxIdleConnections() {
    return config.getInt("mysql.master.max.idle.connections");
  }

  public Integer getAsyncThreadPoolMaxPoolSize() {
    return config.getInt("async.threadpool.max.pool.size", 10);
  }

  public Integer getAsyncThreadpoolCorePoolSize() {
    return config.getInt("async.threadpool.core.pool.size");
  }

  public Integer getAsyncThreadPoolQueueCapacity() {
    return config.getInt("async.threadpool.queue.capacity");
  }

  public Integer getAsyncThreadPoolAwaitTerminationSeconds() {
    return config.getInt("async.threadpool.await.termination.seconds");
  }

  public Integer getPasswordMinLength(){return config.getInt("password.min.length");}

  public Integer getPasswordMaxLength(){return config.getInt("password.max.length");}

  public String getPhoneNumberRegex() {
    return config.getString("phone.number.regex");
  }

  public String getEmailRegex() {
    return config.getString("email.regex");
  }

  public String getProfilePictureStorageLocation() {
    return config.getString("profile.picture.storage.location");
  }
}
