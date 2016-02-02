package com.karumi.headerrecyclerview.sample.util;

import android.util.Log;

/**
 * Created by cmicat on 2016/1/27.
 */
public class DebugHelper {
    /**
     * log这个方法就可以显示超链
     */
    private static String getCallMethodAndLine() {
        String result = "at ";
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result += thisMethodStack.getClassName() + ".";
        result += thisMethodStack.getMethodName();
        result += "(" + thisMethodStack.getFileName();
        result += ":" + thisMethodStack.getLineNumber() + ")  ";
        return result;
    }

    public static void logMethod(String tag) {
        Log.v(tag, getCallMethodAndLine());
    }

}
