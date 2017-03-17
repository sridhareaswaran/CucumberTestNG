package com.sri.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.sri.utils.configReader.baseConfig_data;

/**
 * Created by sridhar.easwaran on 3/16/2017.
 */
public class driverFactory {

    public static WebDriver driver;
    DesiredCapabilities capabilities;
    private String ENV;
    private String RunTestsIn;
    String baseDir = System.getProperty("user.dir");

    private static driverFactory instance = new driverFactory();

    public static driverFactory getInstance()
    {
        return instance;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void createDriver(String browserType) throws MalformedURLException {

        /** Check ENV setup from command line, if not fetch from config file */
        if (System.getProperty("env")!= null) {
            ENV = System.getProperty("env");
        } else {
            ENV = baseConfig_data.get("TestEnvironment");
        }

        /** Fetch "RunTestsIn" from config file and create respective driver */
        RunTestsIn = baseConfig_data.get("RunTestsIn");
        if (RunTestsIn.equals("local"))
            createLocalDriverFor(browserType);
        else if (RunTestsIn.equals("Remote"))
            createRemoteDriverFor(browserType);

    }

    private void createLocalDriverFor(String browserType) {

        switch (browserType) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", baseDir + baseConfig_data.get("geckopath"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", baseDir + baseConfig_data.get("chromepath"));
                driver = new ChromeDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", baseDir + baseConfig_data.get("edgepath"));
                driver = new InternetExplorerDriver();
                break;
            case "htmlunit":
                driver = new HtmlUnitDriver();
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

}
