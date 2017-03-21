package com.sri.pages;

import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;


/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class resultsPage extends basePage {

    public void verifyTitleContains(String text) throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(driver.getTitle().toLowerCase().contains(text.toLowerCase()));
    }
}
