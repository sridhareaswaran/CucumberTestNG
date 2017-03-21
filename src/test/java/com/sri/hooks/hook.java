package com.sri.hooks;

import com.sri.selenium.driverFactory;
import com.sri.utils.logManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.net.MalformedURLException;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class hook extends driverFactory {

    driverFactory driverFactory = new driverFactory();

    @Before
    public void beforeScenario() throws MalformedURLException {
        logManager.log.info("cb - edge");
        driverFactory.createDriver("edge");
    }

    @After
    public void afterScenario() {
        driver.quit();
    }

}
