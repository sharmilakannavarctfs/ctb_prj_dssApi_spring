package com.ctfs.mobile.utils.expectedConditions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class VisibilityOfElementByLocator implements ExpectedCondition<Boolean> {

    private final By locator;

    public VisibilityOfElementByLocator(By locator) {
        this.locator = locator;
    }

    @Override
    public Boolean apply(WebDriver d) {
        try {
            return d.findElement(locator).isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException | ElementNotVisibleException e) {
            return false;
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
}
