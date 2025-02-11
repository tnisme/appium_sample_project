package com.example.Dirvers;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            try {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", "Android");
                caps.setCapability("appium:deviceName", "emulator-5554");
                caps.setCapability("appium:appPackage", "com.google.android.contacts");
                caps.setCapability("appium:appActivity", "com.android.contacts.activities.PeopleActivity");
                caps.setCapability("appium:automationName", "UiAutomator2");

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error when connecting to Appium Server", e);
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