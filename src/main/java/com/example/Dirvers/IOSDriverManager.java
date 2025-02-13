package com.example.Dirvers;

import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class IOSDriverManager {
    private static IOSDriver driver;

    public static IOSDriver getDriver() {
        if (driver == null) {
            try {
                File file = new File(System.getProperty("user.dir") + File.separator + "environment.json");
                String content = FileUtils.readFileToString(file, "utf-8");
                JSONObject env = new JSONObject(content);
                JSONObject iosDeviceInfo = env.getJSONObject("deviceInfo").getJSONObject("ios");
                JSONObject iosAppInfo = env.getJSONObject("appInfo").getJSONObject("ios");
                JSONObject serverInfo = env.getJSONObject("serverInfo");

                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", iosDeviceInfo.getString("platformName"));
                caps.setCapability("appium:deviceName", iosDeviceInfo.getString("deviceName"));
                caps.setCapability("appium:platformVersion", iosDeviceInfo.getString("platformVersion"));
                caps.setCapability("appium:automationName", iosDeviceInfo.getString("automationName"));
                caps.setCapability("appium:bundleId", iosAppInfo.getString("bundleId"));
                caps.setCapability("appium:udid", iosDeviceInfo.getString("udid"));
                driver = new IOSDriver(new URL(serverInfo.getString("serverUrl")), caps);
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
