package com.sri.hooks.chrome;

import com.sri.selenium.driverFactory;
import com.sri.utils.logManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class chromeHook {

    WebDriver driver;
    driverFactory df =new driverFactory();

    @Before
    public void beforeScenario() throws MalformedURLException {
        logManager.log.info("cb - chrome");
        df.createDriver("chrome");
        // driverFactory.getInstance().createDriver("chrome");
        driver = df.getDriver();
    }

    @After
    public void afterScenario() {
        driver.quit();
    }


}
