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

/**
 * Created by Sridhar on 6/3/2016.
 */

@CucumberOptions(
        format = {"pretty", "json:cucu-report/edge_json/edge_json.json", "html:cucu-report/edge"},
        glue = {"com.sri.stepdefs","com.sri.hooks.edge"},
        features = {"src/test/resources/features"},
        //tags = {"@smoke, @regression, @negative, @failing"}
        tags = {"@smoke, @negative, @failing"}
)
public class EdgeTestRunner extends AbstractTestNGCucumberTests {

    String baseDir = System.getProperty("user.dir");
    String sourceDir = baseDir + "\\cucu-report\\edge_json";
    String outputDir = baseDir + "\\test-report";
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.US).format(new Date());

    @BeforeSuite
    public void setUp() throws Exception {
        logManager.initLogManager();
        logManager.log.info("before - Suite");
        configReader.initConfigReader();
    }

    @BeforeTest(alwaysRun = true)
    public void bt() {
        logManager.log.info("before - test");
    }

    @AfterTest(alwaysRun = true)
    public void at() {
        logManager.log.info("after - test");
    }

    @BeforeMethod(alwaysRun = true)
    public void bm() {
        logManager.log.info("before - method");
    }

    @AfterMethod(alwaysRun = true)
    public void am() {
        logManager.log.info("after - method");
    }

    @BeforeClass(alwaysRun = true)
    public void bc() {
        logManager.log.info("before - class");
    }

    @AfterClass(alwaysRun = true)
    public void ac() {
        logManager.log.info("after - class");
    }

    @AfterSuite
    public void generateReport() throws InterruptedException {
        logManager.log.info("Creating HTML report: Edge-" + timeStamp);
        Thread.sleep(5000);
        ReportConsole report =
                Generator.apply(
                        sourceDir,                    //source dir
                        outputDir,                    //output dir
                        "Edge-" + timeStamp,                      //fileNamePrefix
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


