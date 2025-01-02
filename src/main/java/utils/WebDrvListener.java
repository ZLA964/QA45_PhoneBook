package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class WebDrvListener extends AbstractWebDriverEventListener {

    Logger logger = LoggerFactory.getLogger(WebDrvListener.class);

    public WebDrvListener() {
        super();
    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        super.beforeAlertAccept(driver);
        try {
            String alertText = driver.switchTo().alert().getText();
            logger.info("--- Alert text before acceptance: " + alertText);   // Log the alert text
            // Log the current URL and page title
//  not work!!!          logger.info("--- Current URL before alert acceptance: " + driver.getCurrentUrl());
//  not work!!!          logger.info("--- Page title before alert acceptance: " + driver.getTitle());

            // Take a screenshot of the entire page including the alert


        } catch (NoAlertPresentException e) { // Log a warning if no alert is present
            logger.warn("No alert present before to accept.");
        } catch (Exception e) { // Log any other unexpected errors
            logger.error("An error occurred before alert acceptance: ", e);
        }
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        super.afterAlertAccept(driver);
        try {
            // Log a message about the alert being accepted
            logger.info("--- Alert accepted during test");
            // Log the current URL and page title
            logger.info("--- Current URL after alert acceptance: " + driver.getCurrentUrl());
            logger.info("--- Page title after alert acceptance: " + driver.getTitle());

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "screenshots/alert_before_accept_" + System.currentTimeMillis() + ".png";
            File destination = new File(screenshotPath);
            Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            logger.info("Screenshot saved at: " + screenshotPath);

        } catch (NoAlertPresentException e) {
            // Log a warning if there is no alert present
            logger.warn("No alert present to accept (after).");
        } catch (IOException e) {  // Log an error if screenshot saving fails
            logger.error("Failed to save screenshot: ", e);
        } catch (Exception e) {
            // Log any other unexpected errors
            logger.error("An error occurred after alert acceptance: ", e);
        }
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        super.afterAlertDismiss(driver);
        try {
            logger.info("Alert dismissed during test.");
            logger.info("Current URL after alert dismissal: " + driver.getCurrentUrl());
            logger.info("Page title after alert dismissal: " + driver.getTitle());
        } catch (NoAlertPresentException e) {
            logger.warn("No alert present to dismiss.");
        } catch (Exception e) {
            logger.error("An error occurred after alert dismissal: ", e);
        }
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        super.beforeAlertDismiss(driver);
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        super.beforeNavigateTo(url, driver);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        super.afterNavigateTo(url, driver);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        super.beforeNavigateBack(driver);
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        super.afterNavigateBack(driver);
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        super.beforeNavigateForward(driver);
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        super.afterNavigateForward(driver);
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        super.beforeNavigateRefresh(driver);
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        super.afterNavigateRefresh(driver);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        super.beforeFindBy(by, element, driver);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        super.afterFindBy(by, element, driver);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        super.beforeClickOn(element, driver);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        super.afterClickOn(element, driver);
        if (element != null) {
            logger.info("--- click on element --> " + element.toString());
        } else {
            logger.info("--- ellement is null");
        }
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        super.beforeChangeValueOf(element, driver, keysToSend);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        super.afterChangeValueOf(element, driver, keysToSend);
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        super.beforeScript(script, driver);
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        super.afterScript(script, driver);
    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        super.afterSwitchToWindow(windowName, driver);
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        super.beforeSwitchToWindow(windowName, driver);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        super.beforeGetScreenshotAs(target);
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        super.afterGetScreenshotAs(target, screenshot);
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        super.beforeGetText(element, driver);
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        super.afterGetText(element, driver, text);
    }
}
