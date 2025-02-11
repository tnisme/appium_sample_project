package com.example.Factory;

import com.example.Utilities.ActionUtility;
import com.example.Utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class AppiumFactory {

    public static Map<WebDriver, WaitUtility> waitUtility = new HashMap<>();
    public static Map<WebDriver, ActionUtility> actionUtility = new HashMap<>();
    public WebDriver driver;

    public AppiumFactory(WebDriver driver) {
        this.driver = driver;
        initComponents();
    }

    private void initComponents() {
        if (waitUtility.get(driver) == null)
            waitUtility.put(driver, new WaitUtility(driver));
        if (actionUtility.get(driver) == null)
            actionUtility.put(driver, new ActionUtility(driver));
        PageFactory.initElements(driver, this);
    }

    public WaitUtility getWaitUtility() {
        return waitUtility.get(driver);
    }

    public ActionUtility getActionUtility() {
        return actionUtility.get(driver);
    }

}
