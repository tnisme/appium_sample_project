package com.example.Utilities;

import org.json.JSONObject;

public class DataTest {
    private static ThreadLocal<String> browser = new ThreadLocal<>();

    public synchronized static void init() {
    }

    public static JSONObject getConfig() {
        return null;
    }

    // env variable in environment.json
    public static String getEVN() {
        return null;
    }

    // get testing browser testing
    public synchronized static String getBrowser() {
        return browser.get();
    }

    // set testing browser,  parameter is get from suite xml
    public synchronized static void setBrowser(String browserStr) {
        browser.set(browserStr);
    }

}
