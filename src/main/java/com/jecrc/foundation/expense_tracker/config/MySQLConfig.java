package com.jecrc.foundation.expense_tracker.config;

import com.jecrc.foundation.expense_tracker.mapper.ExpenseMapper;
import com.jecrc.foundation.expense_tracker.mapper.UserMapper;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MySQLConfig {

  private final ConfigProps config;

  @Autowired
  private MySQLConfig(ConfigProps config){
    this.config=config;
  }

  private DataSource dataSource;

  private void registerMappers(SqlSessionTemplate sqlSessionTemplate) {
    Class<?>[] mappers = {UserMapper.class, ExpenseMapper.class};
    for (Class<?> cls : mappers) {
      sqlSessionTemplate.getConfiguration().addMapper(cls);
    }
  }

  @Bean("sqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    dataSource = createDataSource(config.getMySQLDriver(), config.getMySQLUsername(),
        config.getMySQLPassword(), config.getMySQLDbUri(), config.getMySQLActiveConnections(),
        config.getMySQLMaxIdleConnections());
    SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(dataSource);
    SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
    registerMappers(sqlSessionTemplate);
    return sqlSessionTemplate;
  }

  private SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    org.apache.ibatis.session.Configuration configuration =
        new org.apache.ibatis.session.Configuration();
    factoryBean.setConfiguration(configuration);
    return factoryBean.getObject();
  }

  private DataSource createDataSource(String driver, String username, String password, String dbUri,
      Integer maxActiveConnections, Integer maxIdleConnections) {
    PooledDataSource dataSource = new PooledDataSource();
    if (StringUtils.isNotNullAndEmpty(username)) {
      dataSource.setUsername(username);
    }
    if (StringUtils.isNotNullAndEmpty(password)) {
      dataSource.setPassword(password);
    }
    dataSource.setDriver(driver);
    dataSource.setUrl(dbUri);
    if (maxActiveConnections != null && maxActiveConnections != 0) {
      dataSource.setPoolMaximumActiveConnections(maxActiveConnections);
    }
    if (maxIdleConnections != null && maxIdleConnections != 0) {
      dataSource.setPoolMaximumIdleConnections(maxIdleConnections);
    }
    dataSource.setDefaultAutoCommit(false);
    dataSource.setDefaultTransactionIsolationLevel(
        TransactionIsolationLevel.READ_COMMITTED.getLevel());
    return dataSource;
  }
}
