package com.jecrc.foundation.expense_tracker.exception;

public class ConfiguratorModeRequiredException extends RuntimeException {

    private static final long serialVersionUID = 7288316082096396830L;

    public ConfiguratorModeRequiredException() {
        super("Configurator Mode is required to create Configurator Bean.");
    }
}
