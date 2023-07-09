package org.example;

public class Util {

  public static String removeLastChar(String str) {
    if (str == null || str.length() == 0) {
      return str;
    }
    return str.substring(0, str.length() - 1);
  }
}
