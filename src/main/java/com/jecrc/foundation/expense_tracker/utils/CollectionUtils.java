package com.jecrc.foundation.expense_tracker.utils;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionUtils {
  private CollectionUtils() {
  }

  public static String convertCollectionToCsv(Collection<?> collection) {
    return StringUtils.collectionToCommaDelimitedString(collection);
  }

  public static List<String> convertCsvToList(String csv) {
    return Arrays.asList(csv.split(","));
  }

  public static List<Integer> convertCsvToIntList(String csv) {
    return convertCsvToList(csv.trim().replace(" ", "")).stream().map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  public static Set<String> convertCsvToSet(String csv) {
    return Arrays.stream(csv.split(",")).collect(Collectors.toSet());
  }

  public static Set<Integer> convertCsvToIntSet(String csv) {
    return Arrays.stream(csv.split(",")).map(Integer::parseInt).collect(Collectors.toSet());
  }

  public static String convertLongSetToCsv(Set<Long> set) {
    StringBuilder csv = new StringBuilder();
    set.forEach(num -> csv.append(num).append(","));
    if (csv.length() > 0) {
      csv.setLength(csv.length() - 1);
    }
    return csv.toString();
  }

  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static boolean isNotEmpty(Collection<?> collection) {
    return collection != null && !collection.isEmpty();
  }

  public static List<Long> convertCsvToLongList(String csv) {
    return convertCsvToList(csv.trim().replace(" ", "")).stream().map(Long::parseLong)
        .collect(Collectors.toList());
  }
}
