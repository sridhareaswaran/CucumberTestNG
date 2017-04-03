package com.sri.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.sri.selenium.seleniumUtils.*;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class homePage extends basePage {

    private By searchBox = new By.ByCssSelector("#lst-ib");
    private By searchButton = new By.ByCssSelector("#_fZl > span > svg");

    public void navigateTo(String url) {
        open(url);
        smilePls();
    }

    public void searchFor(String text) throws Exception {
        $(searchBox).sendKeys(text);
        $(searchButton).clear();
        smilePls();
    }
}
