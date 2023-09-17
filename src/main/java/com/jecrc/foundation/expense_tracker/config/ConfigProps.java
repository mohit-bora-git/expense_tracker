package com.jecrc.foundation.expense_tracker.config;

import com.jecrc.foundation.expense_tracker.configurator.Configurator;
import com.jecrc.foundation.expense_tracker.constants.PropertyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigProps {

  @Autowired
  private Configurator config;

  /*****************************MYSQL CONFIGS*************************************************/
  public String getMySQLDriver() {
    return config.getString(PropertyConstants.MYSQL_DRIVER);
  }

  public String getMySQLUsername() {
    return config.getString(PropertyConstants.MYSQL_USERNAME);
  }

  public String getMySQLPassword() {
    return config.getString(PropertyConstants.MYSQL_PASSWORD);
  }

  public String getMySQLDbUri() {
    return config.getString(PropertyConstants.MYSQL_DB_URL);
  }

  public String getMySQLDbName() {
    return config.getString(PropertyConstants.MYSQL_DB_NAME);
  }

  public Integer getMySQLPort() {
    return config.getInt(PropertyConstants.MYSQL_PORT);
  }

  public Integer getMySQLActiveConnections() {
    return config.getInt(PropertyConstants.MYSQL_ACTIVE_CONNECTIONS);
  }

  public Integer getMySQLMaxIdleConnections() {
    return config.getInt(PropertyConstants.MYSQL_MAX_IDLE_CONNECTIONS);
  }


  /***************************************ASYNC THREAD CONFIGS************************************/
  public Integer getAsyncThreadPoolMaxPoolSize() {
    return config.getInt(PropertyConstants.ASYNC_THREADPOOL_MAX_POOL_SIZE, 10);
  }

  public Integer getAsyncThreadpoolCorePoolSize() {
    return config.getInt(PropertyConstants.ASYNC_THREADPOOL_CORE_POOL_SIZE);
  }

  public Integer getAsyncThreadPoolQueueCapacity() {
    return config.getInt(PropertyConstants.ASYNC_THREADPOOL_QUEUE_CAPACITY);
  }

  public Integer getAsyncThreadPoolAwaitTerminationSeconds() {
    return config.getInt(PropertyConstants.ASYNC_THREADPOOL_AWAIT_TERMINATION_SECONDS);
  }

  /************************************GENERAL PROPS CONFIGS**************************************/
  public Integer getPasswordMinLength() {
    return config.getInt(PropertyConstants.PASSWORD_MIN_LENGTH);
  }

  public Integer getPasswordMaxLength() {
    return config.getInt(PropertyConstants.PASSWORD_MAX_LENGTH);
  }

  public String getEmailRegex() {
    return config.getString(PropertyConstants.EMAIL_REGEXP);
  }

  public String getProfilePictureStorageLocation() {
    return config.getString("profile.picture.storage.location");
  }

  /************************************JSON WEB TOKEN CONFIGS**************************************/

  public Long getAccessTokenMaxLife() {
    return config.getLong(PropertyConstants.JWT_ACCESS_TOKEN_MAX_LIFE);
  }

  public String getJwtAuthSubject() {
    return config.getString(PropertyConstants.JWT_AUTH_SUBJECT);
  }

  public String getJwtIssuer() {
    return config.getString(PropertyConstants.JWT_ISSUER);
  }

  public byte[] getSecretKey() {
    return config.getString(PropertyConstants.JWT_SECRET_KEY).getBytes();
  }
}
