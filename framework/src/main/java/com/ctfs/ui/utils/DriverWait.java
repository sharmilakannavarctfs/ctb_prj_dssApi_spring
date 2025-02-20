package com.ctfs.ui.utils;

import com.ctfs.common.utils.Constants;
import com.ctfs.ui.utils.expectedConditions.ClickabilityOfElement;
import com.ctfs.ui.utils.expectedConditions.ClickabilityOfElementByLocator;
import com.ctfs.ui.utils.expectedConditions.InvisibilityOfElement;
import com.ctfs.ui.utils.expectedConditions.InvisibilityOfElementByLocator;
import com.ctfs.ui.utils.expectedConditions.VisibilityOfElement;
import com.ctfs.ui.utils.expectedConditions.VisibilityOfElementByLocator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.NoSuchElementException;

@Component
public class DriverWait {
	
	@Autowired
    public DriverHelper driverHelper;
	
	private static Logger log = LogManager.getLogger(DriverWait.class);

    private static final ThreadLocal<Wait<WebDriver>> driverWaitThreadLocal = new ThreadLocal<>();
    private final DriverManager driverManager;

    @Autowired
    public DriverWait(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public Wait<WebDriver> getDriverWait() {
        return driverWaitThreadLocal.get();
    }

    public ThreadLocal<Wait<WebDriver>> getDriverWaitThreadLocal() {
        return driverWaitThreadLocal;
    }

    public void waitForAngular() {
        waitUntilAngularReady();
    }

    public void waitForElementToLoad(WebElement element) throws NoSuchFieldException {
        waitForAngular();
        waitForElementVisible(element);
        waitForElementClickable(element);
    }

    public void waitForElementToLoad(By locator) throws NoSuchFieldException {
        waitForAngular();
        waitForElementVisible(locator);
        waitForElementClickable(locator);
    }

    /**
     * wait for element visible by element
     */
    private void waitForElementVisible(WebElement element) {
        try {
            waitLong().until(new VisibilityOfElement (element));
        } catch (Exception ignored) {
        }
    }

    /**
     * wait for element visible by locator
     */
    private void waitForElementVisible(By locator) {
        try {
            waitLong().until(new VisibilityOfElementByLocator (locator));
        } catch (Exception ignored) {
        }
    }

    /**
     * wait for element Invisible by locator
     */
    private void waitForElementInVisible(By locator) {
        try {
            waitLong().until(new InvisibilityOfElementByLocator (locator));
        } catch (Exception ignored) {
        }
    }

    /**
     * wait for element Invisible by locator
     */
    public void waitForElementInVisible(WebElement element) {
        try {
        	System.out.println("Control enter into the waitForElementInVisible methods");
           boolean flag= waitLong().until(new InvisibilityOfElement (element));
            System.out.println("Control exited from waitForElementInVisible methods. Flag is: " +flag);
        } catch (Exception ignored) {
        	
        }
    }

    /**
     * wait for element clickable by element
     */
    private void waitForElementClickable(WebElement element) throws NoSuchFieldException {
        try {
            waitLong().until(new ClickabilityOfElement (element));
        } catch (Exception t) {
            throw new NoSuchFieldException("could not interact with the element " + element);
        }
    }

    /**
     * wait for element clickable by locator
     */
    private void waitForElementClickable(By locator) throws NoSuchFieldException {
        try {
            waitLong().until(new ClickabilityOfElementByLocator(locator));
        } catch (Exception t) {
            throw new NoSuchFieldException("could not interact with the element by locator " + locator);
        }
    }

    public Wait<WebDriver> waitLong() {
        return new FluentWait<>(driverManager.getWebDriver())
                .withTimeout(Duration.ofSeconds(Constants.timeoutLong))
                .pollingEvery(Duration.ofMillis(Constants.pollingLong))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    }

    public Wait<WebDriver> waitShort() {
        return new FluentWait<>(driverManager.getWebDriver())
                .withTimeout(Duration.ofSeconds(Constants.timeoutShort))
                .pollingEvery(Duration.ofMillis(Constants.pollingShort))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    }

    private void waitUntilAngularReady() {


        final Boolean angularUnDefined = (Boolean) driverManager.getJSExecutor()
                .executeScript("return window.angular === undefined");

        if (!angularUnDefined) {
            Boolean angularInjectorUnDefined = (Boolean) driverManager.getJSExecutor()
                    .executeScript("return angular.element(document).injector() === undefined");
            if (!angularInjectorUnDefined) {
                waitForAngularLoad();
                waitUntilJSReady();
                waitForJQueryLoad();
            }
        }
    }

    private void waitForAngularLoad() {
        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        final ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(
                (driverManager.getJSExecutor()).executeScript(angularReadyScript).toString());
        boolean angularReady = Boolean
                .parseBoolean(driverManager.getJSExecutor().executeScript(angularReadyScript).toString());
        if (!angularReady) {
            waitLong().until(angularLoad);
        }
    }

    public void waitUntilJSReady() {
        final ExpectedCondition<Boolean> jsLoad = driver -> (driverManager.getJSExecutor())
                .executeScript("return document.readyState")
                .toString()
                .equals("complete");

        boolean jsReady = driverManager.getJSExecutor().executeScript("return document.readyState")
                .toString().equals("complete");

        if (!jsReady) {
            waitLong().until(jsLoad);
        }
        log.info("Page status: "+jsReady);
    }

    private void waitForJQueryLoad() {
        final ExpectedCondition<Boolean> jQueryLoad = driver -> (
                (Long) (driverManager.getJSExecutor()).executeScript("return jQuery.active") == 0);
        boolean jqueryReady = (Boolean) driverManager.getJSExecutor()
                .executeScript("return jQuery.active==0");
        if (!jqueryReady) {
            waitLong().until(jQueryLoad);
        }
    }
    
    
}
