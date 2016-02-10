package com.karumi.headerrecyclerview.sample.util;

import android.util.Log;

/**
 * Debug utility class
 */
public class DebugHelper {
  private static String getCallMethodAndLine() {
    String result = "at ";
    StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
    result += thisMethodStack.getClassName() + ".";
    result += thisMethodStack.getMethodName();
    result += "(" + thisMethodStack.getFileName();
    result += ":" + thisMethodStack.getLineNumber() + ")  ";
    return result;
  }

  /**
   * You can call this method to log current method
   *
   * @param tag LOG_TAG
   */
  public static void logMethod(String tag) {
    Log.v(tag, getCallMethodAndLine());
  }
}
