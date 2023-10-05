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

  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static boolean isNotEmpty(Collection<?> collection) {
    return collection != null && !collection.isEmpty();
  }
}
