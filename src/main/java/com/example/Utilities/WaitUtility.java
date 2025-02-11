package com.example.Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtility {
    private static final int TIMEOUT_INTERVAL_UNIT = 30;
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;


    public WaitUtility(WebDriver driver) {
        this.driver = driver;
        initComponents();
    }

    private void initComponents() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_INTERVAL_UNIT));
        js = ((JavascriptExecutor) driver);
    }

    /**
     * Waits until the element is invisible. Note that this function is not doing what its name is saying.
     * It is actually waiting for the element to be visible, not invisible.
     *
     * @param element the element
     * @return the element
     */
    public WebElement waitForInvisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the element is clickable
     *
     * @param element the element
     * @return the element
     */
    public WebElement waitUntilToBeClickAble(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the element is present
     *
     * @param by the By object
     * @return the element
     */
    public WebElement waitForPresentOf(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
