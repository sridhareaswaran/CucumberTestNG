package com.sri.pages;

import com.sri.selenium.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class homePage extends basePage {

    WebDriver driver= driverFactory.getDriver();

    public void open() {
        driver.get("https://www.google.co.in");
    }

    public void searchFor(String text) {
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys(text);
        driver.findElement(By.cssSelector("#_fZl > span > svg")).click();
    }
}
