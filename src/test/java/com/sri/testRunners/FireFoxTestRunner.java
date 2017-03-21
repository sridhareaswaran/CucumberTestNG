package com.sri.testRunners;

import com.sri.utils.configReader;
import com.sri.utils.logManager;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.magentys.donut.gherkin.Generator;
import io.magentys.donut.gherkin.model.ReportConsole;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.sri.utils.logManager.log;

/**
 * Created by Sridhar on 6/3/2016.
 */

@CucumberOptions(
        format = {"pretty", "json:cucu-report/firefox_json/firefox_json.json", "html:cucu-report/firefox"},
        glue = {"com.sri.stepdefs","com.sri.hooks.firefox"},
        features = "src/test/resources/features",
        tags = {"@web"}
)
public class FireFoxTestRunner extends AbstractTestNGCucumberTests {

    String baseDir = System.getProperty("user.dir");
    String sourceDir = baseDir + "\\cucu-report\\firefox_json";
    String outputDir = baseDir + "\\test-report";

    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.US).format(new Date());

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        logManager.initLogManager();
        log.info("before - Suite");
        configReader.initConfigReader();
    }

    @BeforeTest(alwaysRun = true)
    public void bt() {
        log.info("before - test");
    }

    @AfterTest(alwaysRun = true)
    public void at() {
        log.info("after - test");
    }

    @BeforeMethod(alwaysRun = true)
    public void bm() {
        log.info("before - method");
    }

    @AfterMethod(alwaysRun = true)
    public void am() {
        log.info("after - method");
    }

    @BeforeClass(alwaysRun = true)
    public void bc() {
        log.info("before - class");
    }

    @AfterClass(alwaysRun = true)
    public void ac() {
        log.info("after - class");
    }

    @AfterSuite(alwaysRun = true)
    public void generateReport() throws InterruptedException {
        log.info("Creating HTML report: FF-" + timeStamp);
        Thread.sleep(5000);
        ReportConsole report =
                Generator.apply(
                        sourceDir,                    //source dir
                        outputDir,                    //output dir
                        "FF-" + timeStamp,               //fileNamePrefix
                        "yyyy-MM-dd-HHmm",            //timestamp
                        "default",                    //template
                        false,                        //count Skipped As Failure
                        false,                        //count Pending As Failure
                        false,                        //count Undefined As Failure
                        false,                        //count Missing As Failure
                        "CompanyName",     //project Name
                        "1.0"                         //project Version
                );
    }
}


