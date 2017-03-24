package com.sri.pages;

import com.sri.selenium.driverFactory;
import cucumber.api.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class homePage extends basePage {

    public void open() {
        driver.get("https://www.google.co.in");
        smilePls();
    }

    public void searchFor(String text) throws Exception {
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys(text);
        driver.findElement(By.cssSelector("#_fZl > span > svg")).click();
        smilePls();
    }
}
