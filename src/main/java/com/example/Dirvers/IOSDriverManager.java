package com.example.Dirvers;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSDriverManager {
    private static IOSDriver driver;

    public static IOSDriver getDriver() {
        if (driver == null) {
            try {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", "iOS");
                caps.setCapability("appium:deviceName", "iPhone 16 Pro");
                caps.setCapability("appium:platformVersion", "18.1");
                caps.setCapability("appium:automationName", "XCUITest");
                caps.setCapability("appium:bundleId", "com.apple.MobileAddressBook");
                caps.setCapability("appium:udid", "26956273-C299-49B7-9FE2-8D58751BDD71");

                driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), caps);
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
