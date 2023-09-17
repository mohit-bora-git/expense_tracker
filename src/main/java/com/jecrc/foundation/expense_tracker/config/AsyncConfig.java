package com.jecrc.foundation.expense_tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

  @Autowired
  private ConfigProps config;

  @Bean(name = "asyncExecutor")
  public ThreadPoolTaskExecutor asyncExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setMaxPoolSize(config.getAsyncThreadPoolMaxPoolSize());
    threadPoolTaskExecutor.setCorePoolSize(config.getAsyncThreadpoolCorePoolSize());
    threadPoolTaskExecutor.setQueueCapacity(config.getAsyncThreadPoolQueueCapacity());
    threadPoolTaskExecutor.setAwaitTerminationSeconds(
        config.getAsyncThreadPoolAwaitTerminationSeconds());
    return threadPoolTaskExecutor;
  }
}
