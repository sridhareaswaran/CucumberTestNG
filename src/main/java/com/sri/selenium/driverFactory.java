package com.sri.selenium;

import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.sri.utils.configReader.BROWSER;
import static com.sri.utils.configReader.RunTestsIn;
import static com.sri.utils.configReader.baseConfig_data;
import static com.sri.utils.logManager.log;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class driverFactory {

    protected static WebDriver driver;
    DesiredCapabilities capabilities;
    String baseDir = System.getProperty("user.dir");
    public static Scenario currentScenario;

    public static WebDriver getDriver() {
        return driver;
    }


    public void createDriver() throws MalformedURLException {

        String browserType = BROWSER.toLowerCase();

        if (RunTestsIn.equalsIgnoreCase("local"))
            createLocalDriverFor(browserType);
        else if (RunTestsIn.equalsIgnoreCase("Remote"))
            createRemoteDriverFor(browserType);

    }

    private void createLocalDriverFor(String browserType) {

        switch (browserType) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", baseDir + baseConfig_data.get("geckopath"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                log.info(">>> creating chrome local driver");
                System.setProperty("webdriver.chrome.driver", baseDir + baseConfig_data.get("chromepath"));
                driver = new ChromeDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", baseDir + baseConfig_data.get("edgepath"));
                driver = new EdgeDriver();
                break;
            case "opera":
                driver = new OperaDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser type :" + browserType);
        }
    }

    private void createRemoteDriverFor(String browserType) throws MalformedURLException {
        URL remoteURL = new URL(baseConfig_data.get("GridURL"));

        switch (browserType) {
            case "chrome":
                log.info(">>> creating chrome remote driver");
                capabilities = DesiredCapabilities.chrome();
                driver = new RemoteWebDriver(remoteURL, capabilities);
                break;
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                driver = new RemoteWebDriver(remoteURL, capabilities);
                break;
            case "edge":
                capabilities = DesiredCapabilities.edge();
                driver = new RemoteWebDriver(remoteURL, capabilities);
                break;
            default:
                throw new IllegalArgumentException("Invalid browser type :" + browserType);
        }
    }

    public static void smilePls(){
        byte[] pic=((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        currentScenario.embed(pic,"img.png");
    }


}
