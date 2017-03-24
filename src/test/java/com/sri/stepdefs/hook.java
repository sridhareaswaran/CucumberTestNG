package com.sri.stepdefs;

import com.sri.selenium.driverFactory;
import com.sri.utils.logManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;

import static com.sri.utils.configReader.BROWSER;
import static com.sri.utils.configReader.RunTestsIn;
import static com.sri.utils.logManager.*;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class hook extends driverFactory {

    driverFactory driverFactory = new driverFactory();

    @Before
    public void beforeScenario(Scenario scenario) throws MalformedURLException {
        currentScenario=scenario;
        log.info("-----------------------  SCENARIO  -----------------------");
        log.info(scenario.getName());
        log.info("Starting " + BROWSER + " in " + RunTestsIn + " instance");
        log.info("----------------------------------------------------------");
        driverFactory.createDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "pic.png");
        }
        driver.quit();
        log.info("----------------------------------------------------------\n\n");
    }

}
