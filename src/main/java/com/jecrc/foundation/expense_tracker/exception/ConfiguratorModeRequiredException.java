package com.jecrc.foundation.expense_tracker.exception;

public class ConfiguratorModeRequiredException extends RuntimeException {
  public ConfiguratorModeRequiredException() {
    super("Configurator Mode is required to create Configurator Bean.");
  }
}
