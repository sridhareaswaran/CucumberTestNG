package com.sri.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by sridhar.easwaran on 3/27/2017.
 */
public class seleniumUtils extends driverFactory {

    public static void smilePls() {
        byte[] pic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        currentScenario.embed(pic, "img.png");
    }

    static WebDriverWait wait = new WebDriverWait(driver, 20);

    public static WebElement $(By bySelector) {
        return driver.findElement(bySelector);
    }

    public static void open(String url) {
        driver.get(url);
    }

    public static void refershPage() {
        driver.navigate().refresh();
    }

    public static void browserBack() {
        driver.navigate().back();
    }

    public static void browserForward() {
        driver.navigate().forward();
    }

    public static Object executeScript(String script) {
        return ((JavascriptExecutor) driver).executeScript(script);
    }

    public static void scrollToBottom() {
        executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void scrollToTop() {
        executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    public static void scrollToElement(By bySelector) {
        WebElement element = driver.findElement(bySelector);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void scrollToElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public static void doubleClickOn(By bySelector) {
        WebElement element = driver.findElement(bySelector);
        Actions action = new Actions(driver);
        action.doubleClick(element).build().perform();
    }

    public static void rightCLickOn(By bySelector) {
        WebElement element = driver.findElement(bySelector);
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
    }

    public static void dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public static void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public static void drapAndDrop(By source, By destination) {
        WebElement sourceEle = $(source);
        WebElement destinationEle = $(destination);
        if (sourceEle.isDisplayed() && destinationEle.isDisplayed()) {
            Actions action = new Actions(driver);
            action.dragAndDrop(sourceEle, destinationEle).build().perform();
        }
    }

    public static void drapAndDrop(WebElement sourceEle, WebElement destinationEle) {
        if (sourceEle.isDisplayed() && destinationEle.isDisplayed()) {
            Actions action = new Actions(driver);
            action.dragAndDrop(sourceEle, destinationEle).build().perform();
        }
    }

    enum ElementState {
        VISIBLE,
        PRESENT,
        CLICKABLE
    }

    public static ExpectedCondition<WebElement> waitUtil(By element, ElementState elementState) {

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(3, TimeUnit.MINUTES)
                .pollingEvery(1, TimeUnit.SECONDS);

        ExpectedCondition<WebElement> webElement = null;

        switch (elementState) {
            case VISIBLE:
                webElement = wait.until(webDriver -> ExpectedConditions.visibilityOfElementLocated(element));
                break;
            case PRESENT:
                webElement = wait.until(webDriver -> ExpectedConditions.presenceOfElementLocated(element));
                break;
            case CLICKABLE:
                webElement = wait.until(webDriver -> ExpectedConditions.elementToBeClickable(element));
                break;
            default:
                break;
        }
        return webElement;
    }

    public static void waitUntil_PageLoad() {

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(3, TimeUnit.MINUTES)
                .pollingEvery(1, TimeUnit.SECONDS);

        Function<WebDriver, Boolean> func = webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete");

        wait.until(func);
    }

    public static void waitUntil_PageTitleEquals(String title) {

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(1, TimeUnit.MINUTES)
                .pollingEvery(1, TimeUnit.SECONDS);

        wait.until(webDriver -> webDriver.getTitle().equals(title));
    }

    public static void waitUntil_PageTitleEwetquals(String title) {

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(1, TimeUnit.MINUTES)
                .pollingEvery(1, TimeUnit.SECONDS);

        wait.until(webDriver -> webDriver.getTitle().equals(title));
    }

    public static void waitUntil_PageTitleContains(String title) {

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(1, TimeUnit.MINUTES)
                .pollingEvery(1, TimeUnit.SECONDS);

        wait.until(webDriver -> webDriver.getTitle().contains(title));
    }


}
