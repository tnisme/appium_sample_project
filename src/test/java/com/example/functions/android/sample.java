package com.example.functions.android;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class sample extends BaseTest {

    @Test
    public void init() {
        WebElement button = getDriver().findElement(new By.ById("com.example:id/button"));
        button.click();
    }

}
