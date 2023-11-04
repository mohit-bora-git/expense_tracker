package com.jecrc.foundation.expense_tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

  private final ConfigProps config;

  @Autowired
  private AsyncConfig(ConfigProps config){
    this.config=config;
  }

  public static final String API_EXECUTOR="asyncExecutor";
  public static final String TASK_EXECUTOR="taskExecutor";

  @Bean(name = API_EXECUTOR)
  public ThreadPoolTaskExecutor asyncExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setMaxPoolSize(config.getAsyncThreadPoolMaxPoolSize());
    threadPoolTaskExecutor.setCorePoolSize(config.getAsyncThreadpoolCorePoolSize());
    threadPoolTaskExecutor.setQueueCapacity(config.getAsyncThreadPoolQueueCapacity());
    threadPoolTaskExecutor.setAwaitTerminationSeconds(
        config.getAsyncThreadPoolAwaitTerminationSeconds());
    return threadPoolTaskExecutor;
  }

  @Bean(name = TASK_EXECUTOR)
  public ThreadPoolTaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setMaxPoolSize(config.getAsyncThreadPoolMaxPoolSize());
    threadPoolTaskExecutor.setCorePoolSize(config.getAsyncThreadpoolCorePoolSize());
    threadPoolTaskExecutor.setQueueCapacity(config.getAsyncThreadPoolQueueCapacity());
    threadPoolTaskExecutor.setAwaitTerminationSeconds(
        config.getAsyncThreadPoolAwaitTerminationSeconds());
    return threadPoolTaskExecutor;
  }
}
