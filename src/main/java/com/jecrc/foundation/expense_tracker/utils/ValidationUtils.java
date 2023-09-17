package com.jecrc.foundation.expense_tracker.utils;

import java.util.regex.PatternSyntaxException;

public class ValidationUtils {
  /**
   * Check for validation of the provided value with the given Regex Pattern.
   *
   * @param validationRegex Regex Pattern to Validate.
   * @param value           Value to check against.
   * @return Boolean returning result of validation check.
   * @throws PatternSyntaxException on Invalid Regex Provided.
   * @throws NullPointerException   on either null Regex Pattern or Value.
   */
  public static Boolean validate(String validationRegex, String value)
      throws PatternSyntaxException, NullPointerException {
    return value.matches(validationRegex);
  }

  /**
   * Inverse of validation check of the provided value with the given Regex Pattern.
   *
   * @param validationRegex Regex Pattern to Validate.
   * @param value           Value to check against.
   * @return Boolean returning inverse of the result of validation check.
   * @throws PatternSyntaxException on Invalid Regex Provided.
   * @throws NullPointerException   on either null Regex Pattern or Value.
   */
  public static Boolean invalidate(String validationRegex, String value)
      throws PatternSyntaxException, NullPointerException {
    return !(value.matches(validationRegex));
  }
}
