package com.sri.pages;

import com.sri.selenium.driverFactory;
import org.openqa.selenium.WebDriver;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public abstract class basePage extends driverFactory {
    WebDriver driver=getDriver();
}
