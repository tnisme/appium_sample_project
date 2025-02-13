package com.example.Dirvers;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            try {
                File file = new File(System.getProperty("user.dir") + File.separator + "environment.json");
                String content = FileUtils.readFileToString(file, "utf-8");
                JSONObject env = new JSONObject(content);
                JSONObject androidDeviceInfo = env.getJSONObject("deviceInfo").getJSONObject("android");
                JSONObject androidAppInfo = env.getJSONObject("appInfo").getJSONObject("android");
                JSONObject serverInfo = env.getJSONObject("serverInfo");

                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", androidDeviceInfo.getString("platformName"));
                caps.setCapability("appium:deviceName", androidDeviceInfo.getString("deviceName"));
                caps.setCapability("appium:appPackage", androidAppInfo.getString("appPackage"));
                caps.setCapability("appium:appActivity", androidAppInfo.getString("appActivity"));
                caps.setCapability("appium:automationName", androidDeviceInfo.getString("automationName"));
                driver = new AndroidDriver(new URL(serverInfo.getString("serverUrl")), caps);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error when connecting to Appium Server", e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}