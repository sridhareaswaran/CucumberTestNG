package com.sri.hooks.firefox;

import com.sri.selenium.driverFactory;
import com.sri.utils.logManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class ffHook {

    WebDriver driver;
    driverFactory df =new driverFactory();

    @Before
    public void beforeScenario() throws MalformedURLException {
        logManager.log.info("cb - firefox");
        df.createDriver("firefox");
        driver = df.getDriver();
    }

    @After
    public void afterScenario() {
        df.getDriver().quit();
    }

}
