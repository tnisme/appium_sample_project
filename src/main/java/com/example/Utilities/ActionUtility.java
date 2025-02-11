package com.example.Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class ActionUtility {
    private final WebDriver driver;
    private Actions actions;

    public ActionUtility(WebDriver driver) {
        this.driver = driver;
        initComponents();
    }

    private void initComponents() {
        actions = new Actions(driver);
    }

    /**
     * sleep for a given time
     * @param time time in milliseconds
     */
    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Moves the mouse to the middle of the element.
     * @param element the element
     * @return the actions
     */
    public Actions moveToElement(WebElement element) {
        return actions.moveToElement(element);
    }

    /**
     * Clicks (without releasing) in the middle of the given element.
     * @param element the element
     */
    public void clickAndHold(WebElement element) {
        actions.clickAndHold(element).perform();
    }
}