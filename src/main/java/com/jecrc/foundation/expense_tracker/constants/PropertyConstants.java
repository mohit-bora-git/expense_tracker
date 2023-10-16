package com.jecrc.foundation.expense_tracker.constants;

public class PropertyConstants {
  private PropertyConstants() {
  }

  /*******************************MYSQL CONSTANTS********************************************/
  public static final String MYSQL_DRIVER = "mysql.driver";
  public static final String MYSQL_USERNAME = "mysql.username";
  public static final String MYSQL_PASSWORD = "mysql.password";
  public static final String MYSQL_DB_URL = "mysql.db.url";
  public static final String MYSQL_DB_NAME = "mysql.db.name";
  public static final String MYSQL_PORT = "mysql.port";
  public static final String MYSQL_ACTIVE_CONNECTIONS = "mysql.active.connections";
  public static final String MYSQL_MAX_IDLE_CONNECTIONS = "mysql.max.idle.connections";

  /********************************ASYNC THREAD CONSTANTS*************************************/
  public static final String ASYNC_THREADPOOL_MAX_POOL_SIZE = "async.threadpool.max.pool.size";
  public static final String ASYNC_THREADPOOL_CORE_POOL_SIZE = "async.threadpool.core.pool.size";
  public static final String ASYNC_THREADPOOL_QUEUE_CAPACITY = "async.threadpool.queue.capacity";
  public static final String ASYNC_THREADPOOL_AWAIT_TERMINATION_SECONDS =
      "async.threadpool.await.termination.seconds";

  /*********************************GENERAL CONSTANTS***************************************/
  public static final String PASSWORD_MIN_LENGTH = "password.min.length";
  public static final String PASSWORD_MAX_LENGTH = "password.max.length";
  public static final String EMAIL_REGEXP = "email.regex";

  /*********************************JSON WEB TOKEN CONSTANTS*********************************/
  public static final String JWT_ACCESS_TOKEN_MAX_LIFE = "jwt.access.token.max.life";
  public static final String JWT_AUTH_SUBJECT = "jwt.auth.subject";
  public static final String JWT_ISSUER = "jwt.issuer";
  public static final String JWT_SECRET_KEY = "jwt.secret.key";

  /*********************************GENERAL CONSTANTS*********************************/
  public static final String APP_EMAIL="app.email";
}
