package com.jecrc.foundation.expense_tracker.configurator;


import com.jecrc.foundation.expense_tracker.exception.ConfiguratorModeRequiredException;
import com.jecrc.foundation.expense_tracker.exception.InvalidConfiguratorModeException;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConfiguratorInitializer {
  Logger logger = LoggerFactory.getLogger(ConfiguratorInitializer.class);

  @Value("${configurator.filename}")
  private String configFileName;

  @Value("${configurator.mode}")
  private String configMode;

  private Set<String> supportedConfigModes;
  private static final String CONFIG_MODE_JSON = "json";

  @PostConstruct
  public void init() {
    this.supportedConfigModes = new HashSet<>();
    this.supportedConfigModes.add(CONFIG_MODE_JSON);
  }

  @Bean
  public Configurator configurator() throws FileNotFoundException, IOException {
    if (StringUtils.isNullOrEmpty(configMode)) {
      throw new ConfiguratorModeRequiredException();
    }
    logger.info("Creating Configurator Bean for ConfigMode: {}, ConfigFileName: {}", configMode,
        configFileName);
    switch (configMode) {
      case CONFIG_MODE_JSON:
        return JsonFileConfigurator.builder().addFileName(configFileName).build();
      default:
        throw new InvalidConfiguratorModeException(
            "Configuration Mode " + configMode + " is invalid. Only " + this.supportedConfigModes.toString() + " are allowed.");
    }
  }
}
