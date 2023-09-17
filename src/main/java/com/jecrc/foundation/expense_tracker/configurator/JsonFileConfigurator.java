package com.jecrc.foundation.expense_tracker.configurator;


import com.jecrc.foundation.expense_tracker.utils.FileUtils;
import com.jecrc.foundation.expense_tracker.utils.JacksonUtils;
import com.jecrc.foundation.expense_tracker.utils.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class JsonFileConfigurator implements Configurator {
  private Map<String, String> configMap = null;

  private JsonFileConfigurator(Builder builder) {
    this.configMap = builder.configMap;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getString(String property) {
    return this.configMap.get(property);
  }

  public Integer getInt(String property) {
    return StringUtils.convertToInt(this.configMap.get(property));
  }

  public Double getDouble(String property) {
    return StringUtils.convertToDouble(this.configMap.get(property));
  }

  public Float getFloat(String property) {
    return StringUtils.convertToFloat(this.configMap.get(property));
  }

  public Long getLong(String property) {
    return StringUtils.convertToLong(this.configMap.get(property));
  }

  public String getString(String property, String defaultValue) {
    return this.configMap.getOrDefault(property, defaultValue);
  }

  public Integer getInt(String property, Integer defaultValue) {
    if (!this.configMap.containsKey(property)) {
      return defaultValue;
    }
    return StringUtils.convertToInt(this.configMap.get(property));
  }

  public Double getDouble(String property, Double defaultValue) {
    if (!this.configMap.containsKey(property)) {
      return defaultValue;
    }
    return StringUtils.convertToDouble(this.configMap.get(property));
  }

  public Float getFloat(String property, Float defaultValue) {
    if (!this.configMap.containsKey(property)) {
      return defaultValue;
    }
    return StringUtils.convertToFloat(this.configMap.get(property));
  }

  public Long getLong(String property, Long defaultValue) {
    if (!this.configMap.containsKey(property)) {
      return defaultValue;
    }
    return StringUtils.convertToLong(this.configMap.get(property));
  }

  public Boolean getBoolean(String property) {
    return StringUtils.convertToBoolean(this.configMap.get(property));
  }


  static class Builder {
    private String fileName;
    private Map<String, String> configMap;

    public Builder addFileName(String fileName) {
      this.fileName = fileName;
      return this;
    }

    @SuppressWarnings("unchecked")
    public JsonFileConfigurator build() throws IOException, FileNotFoundException {
      if (fileName == null || fileName.length() == 0) {
        throw new FileNotFoundException("Configuration file " + fileName + " not provided.");
      }
      String fileProps = FileUtils.readFile(fileName);
      this.configMap = JacksonUtils.fromJson(fileProps, Map.class);
      return new JsonFileConfigurator(this);
    }
  }

}
