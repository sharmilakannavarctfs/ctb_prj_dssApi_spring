package com.ctfs.ui.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import com.ctfs.framework.ColorConstants;
import com.ctfs.framework.Constants.Language;
import com.ctfs.ui.utils.expectedConditions.InvisibilityOfElement;
import com.ctfs.framework.DataDictionary;
import com.ctfs.framework.IFormatFilter;
import com.ctfs.framework.TestLanguage;
import com.ctfs.framework.ValidationException;
import com.ctfs.profile.MiscFunctions;
//import com.ctfs.miscfunctions.util.Constants.Language;
//import com.ctfs.miscfunctions.util.TestLanguage;

@Component
@SuppressWarnings("unused")
public class DriverHelper {

	private final Logger logger = LoggerFactory.getLogger(DriverHelper.class);

	@Autowired
	private final DriverManager driverManager;

	private final DriverWait driverWait;

	@Autowired
	public DriverHelper(DriverManager driverManager, DriverWait driverWait) {
		this.driverManager = driverManager;
		this.driverWait = driverWait;
	}

	/**
	 * Send Keys to the specified element, clears the element first
	 */
	public void sendKeys(WebElement element, String value) {
		if (value != null) {
			if (value.length() > 0) {
				clear(element);
				element.sendKeys(value);
			} else {
				clear(element);
			}
		}
	}

	/**
	 * Clicks on an element by WebElement
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public void click(WebElement element) throws NoSuchFieldException {
		try {
			driverWait.waitForElementToLoad(element);
			element.click();
		} catch (StaleElementReferenceException e) {
			logger.warn("Could not click on the element");
			throw new RetryException("Could not click on the element : " + element);
		}
	}

	/**
	 * Clicks on an element by Locator
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public void click(By locator) throws NoSuchFieldException {
		try {
			driverWait.waitForElementToLoad(locator);
			driverManager.getWebDriver().findElement(locator).click();
		} catch (StaleElementReferenceException e) {
			logger.warn("Could not click on the element");
			throw new RetryException("Could not click on the element");
		}
	}

	/**
	 * Clicks on an element by Locator
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public void rightClick(By locator) throws NoSuchFieldException {
		driverWait.waitForElementToLoad(locator);
		final WebElement element = driverManager.getWebDriver().findElement(locator);
		try {
			final Actions builder = new Actions(driverManager.getWebDriver());
			builder.moveToElement(element).contextClick(element);
			builder.perform();
		} catch (Exception ser) {
			logger.warn("Could not click on the element : " + element);
			throw new RetryException("Could not click on the element : " + element);
		}
	}

	public void launchTemenosApplication() {
		driverManager.launchApp();
	}

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public void scrollElementIntoView(WebElement element) {
		try {
			driverManager.getJSExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception ignored) {
			logger.warn("Could not click on the element : " + element);
			throw new RetryException("Could not click on the element : " + element);
		}
	}

	/**
	 * Clicks on an element by WebElement
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public void rightClick(WebElement element) throws NoSuchFieldException {
		driverWait.waitForElementToLoad(element);

		try {
			final Actions builder = new Actions(driverManager.getWebDriver());
			builder.moveToElement(element).contextClick(element);
			builder.perform();
		} catch (Exception ser) {
			logger.warn("Could not click on the element : " + element);
			throw new RetryException("Could not click on the element : " + element);
		}
	}

	/**
	 * Clicks on an element using Actions
	 */

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public void clickAction(WebElement element) throws NoSuchFieldException {
		driverWait.waitForElementToLoad(element);
		try {
			final Actions builder = new Actions(driverManager.getWebDriver());
			builder.moveToElement(element).click(element);
			builder.perform();
		} catch (Exception ser) {
			logger.warn("Could not click on the element");
			throw new RetryException("Could not click on the element : " + element);
		}
	}

	/**
	 * Clicks on an element using Actions
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public void clickAction(By locator) throws NoSuchFieldException {
		driverWait.waitForElementToLoad(locator);

		final WebElement element = driverManager.getWebDriver().findElement(locator);
		try {
			final Actions builder = new Actions(driverManager.getWebDriver());
			builder.moveToElement(element).click(element);
			builder.perform();
		} catch (Exception ser) {
			logger.warn("Could not click on the element");
			throw new RetryException("Could not click on the element : " + element);
		}
	}

	/**
	 * Checks if the specified element is displayed
	 */
	public boolean isElementDisplayed(WebElement element) {
		boolean present = false;
		try {
			present = element.isDisplayed();
		} catch (Exception ignored) {
		}
		return present;
	}

	/**
	 * Clear text from a field
	 */
	private void clear(WebElement element) {
		try {
			driverManager.getJSExecutor().executeScript("arguments[0].value='';", element);
		} catch (ElementNotInteractableException ignored) {
		}
	}

	public WebElement getElementWhenVisible(WebElement element, int timeoutSec) {
		return getWait(timeoutSec).until(ExpectedConditions.visibilityOf(element));
	}

	public boolean isElementVisibleWithTime(WebElement ele, int timeoutSec) {
		try {
			getElementWhenVisible(ele, timeoutSec);
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}

	public WebElement getwebElement(String val) {
		MiscFunctions.sleep(3000);
		return driverManager.getWebDriver().findElement(By.xpath(val));
	}

	public List<WebElement> getwebElements(String val) {
		return driverManager.getWebDriver().findElements(By.xpath(val));
	}

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public List<WebElement> getAllVisibleElements(List<WebElement> els) throws InterruptedException {
//	log.info(String.format("Attempting to find and return all visible WebElement(s) with the following By value: [%s]",
//            _by.toString()));
		long cutoff = new Date().getTime() + 10000;
		List<WebElement> elems = new ArrayList<WebElement>();
		int eCount = 0;
		do {
			eCount = 0;
			if (els != null) {
				eCount = els.size();
				for (WebElement e : els) {
					if (e.isDisplayed()) {
						elems.add(e);
					}
				}
				// Break if found visible elements
				// Check twice to ensure new elements don't become visible and send back
				// imcomplete list
				if (elems.size() > 0 && elems.size() >= -1) {
					break;
				}
				elems.clear();
			}
			Thread.sleep(100);
		} while (new Date().getTime() < cutoff);

		if (elems.size() == 0) {
			if (eCount == 0) {
				throw new org.openqa.selenium.NoSuchElementException("xpath=" + els);
			}
			throw new org.openqa.selenium.ElementNotVisibleException("xpath=" + els);
		}

		return elems;
	}

//	protected boolean waitForEmptyText(WebElement element, int waitSeconds) {
//		try {
//			getWait(waitSeconds).until(new ExpectedCondition<Boolean>() {
//
//				@Override
//				public Boolean apply(WebDriver driver) {
//					return getText(element).length() == 0;
//				}
//
//			});
//			return true;
//		} catch (TimeoutException e) {
//			return false;
//		}
//	}
//
//	public void validateEmptyText(WebElement ele, int waitSeconds) throws ValidationException {
//		if (!waitForEmptyText(ele, waitSeconds)) {
//			throw new ValidationException(
//					"Failed validateEmptyText by=" + ele.toString() + " waitSeconds=" + waitSeconds);
//		}
//	}

	/**
	 * Waits for the element at the given XPATH to be visible, and then returns a
	 * WebElement instance of the element.
	 *
	 * @param _by locator for element to locate and check for visibility
	 * @return WebElement if found
	 */

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public WebElement getElementWhenVisible(WebElement element) {
//    	log.info(String.format("Attempting to find and return the WebElement with the following By value: [%s]",
//                    _by.toString()));
		return driverWait.getDriverWait().until(ExpectedConditions.visibilityOf(element));
	}

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public boolean isElementVisible(WebElement element) {
		try {
			getElementWhenVisible(element);
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}

	/**
	 * Waits for the element(s) at the given XPATH to be visible, and then returns a
	 * list of visible WebElement elements.
	 *
	 * @param _by locator for element to locate and check for visibility
	 * @return WebElement list if found
	 * @throws InterruptedException
	 */

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public List<WebElement> getAllVisibleElements(String xpath) throws InterruptedException {
//    	log.info(String.format("Attempting to find and return all visible WebElement(s) with the following By value: [%s]",
//                _by.toString()));
		long cutoff = new Date().getTime() + 10000;
		List<WebElement> elems = new ArrayList<WebElement>();
		int eCount = 0;
		do {
			eCount = 0;
			List<WebElement> els = driverManager.getWebDriver().findElements(By.xpath(xpath));
			if (els != null) {
				eCount = els.size();
				for (WebElement e : els) {
					if (e.isDisplayed()) {
						elems.add(e);
					}
				}
				// Break if found visible elements
				// Check twice to ensure new elements don't become visible and send back
				// imcomplete list
				if (elems.size() > 0 && elems.size() >= -1) {
					break;
				}
				elems.clear();
			}
			Thread.sleep(100);
		} while (new Date().getTime() < cutoff);

		if (elems.size() == 0) {
			if (eCount == 0) {
				throw new org.openqa.selenium.NoSuchElementException("xpath=" + xpath);
			}
			throw new org.openqa.selenium.ElementNotVisibleException("xpath=" + xpath);
		}

		return elems;
	}

	/**
	 * Waits for the element(s) at the given XPATH to be visible, and then returns a
	 * list of WebElement elements.
	 *
	 * @param _by locator for element to locate and check for visibility
	 * @return WebElement list if found
	 */
	public List<WebElement> getElementsWhenVisible(List<WebElement> elements) {
//    	log.info(String.format("Attempting to find and return the WebElement(s) with the following By value: [%s]", _by.toString()));
		return driverWait.getDriverWait().until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	/**
	 * Waits for an element to be visible. May be more than one element that matches
	 * xpath, will return first visible element.
	 *
	 * @param _by locator for element to locate and check for visibility
	 * @return WebElement if found
	 * @throws InterruptedException
	 */
	public WebElement getAnyElementWhenVisible(List<WebElement> elementsList) throws InterruptedException {
//    	log.info(String.format("Attempting to find and return the WebElement with the following By value: [%s]",  _by.toString()));
		long cutoff = new Date().getTime() + 10000;
		WebElement el = null;
		int eCount = 0;
		do {
			eCount = 0;
			try {
				if (elementsList != null) {
					eCount = elementsList.size();
					for (WebElement e : elementsList) {
						if (e.isDisplayed()) {
							el = e;
							break;
						}
					}
				}
			} catch (StaleElementReferenceException e) {
				// Eat the exception, will correct next time through loop
			}
			Thread.sleep(100);
		} while (new Date().getTime() < cutoff);

		if (el == null) {
			if (eCount == 0) {
				throw new org.openqa.selenium.NoSuchElementException("Count is zero");
			}
			throw new org.openqa.selenium.ElementNotVisibleException("Element is null");
		}

		return el;
	}

	/**
	 * Waits for the WebElement to be visible
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is visible within the default wait time
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public void waitForVisible(WebElement element) {
//    	log.info(String.format("Waiting for WebElement with locator: [%s] to be visible",
//                _locator.toString()));
		// Remove the timeout catch
		// Calling routine are not checking the return value and errors result since the
		// element is not
		// visible
		driverWait.getDriverWait().until((_driver) -> element.isDisplayed());
	}

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public boolean waitForVisible_NoException(WebElement element) {
//    	log.info(String.format("Waiting for WebElement with locator: [%s] to be visible", _locator.toString()));
		try {
			return driverWait.getDriverWait().until((_driver) -> element.isDisplayed());
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Waits for the WebElement to be invisible
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is visible within the default wait time
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public boolean waitForInvisible(WebElement element) {
//    	log.info(String.format("Waiting for WebElement with locator: [%s] to be NOT visible", _locator.toString()));
		try {
			// Use invisiblilityOfElementLocated instead of isDisplayed because isDisplayed
			// does not handle the case of the element no longer existing
			return  driverWait.waitLong().until(new InvisibilityOfElement (element));

//			return true;
			// return getWait().until((_driver) ->
			// !_driver.findElement(_locator).isDisplayed());
		} catch (TimeoutException e) {
			return false;
		}
	}

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public boolean waitForEmptyText(WebElement element) {
//    	log.info("Waiting for empty text locator=" + _locator.toString());
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return element.getText().length() == 0;
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public boolean waitForNonEmptyText(WebElement element) {
//    	log.info("Waiting for empty text locator=" + _locator.toString());
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return element.getText().length() != 0;
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public boolean waitForText(WebElement element, String expectedText, boolean textEquals) {
//    	log.info("Waiting for text locator=" + _locator.toString() + " expectedText=" + expectedText);
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return textEquals ? element.getText().equals(expectedText)
							: element.getText().contains(expectedText);
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Waits for the WebElement to be enabled
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is enabled within the default wait time
	 */
	public boolean waitForEnabled(WebElement element) {
//    	log.info(String.format("Waiting for WebElement with locator: [%s] to be enabled", _locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> element.isEnabled());
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isEnabled(WebElement element) {
//    	log.info(String.format("Waiting for WebElement with locator: [%s] to be enabled", _locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> element.isEnabled());
		} catch (TimeoutException e) {
			return false;
		}
	}

	public String getCurrentURL() {
		return driverManager.getWebDriver().getCurrentUrl();
	}

	public boolean waitForDisabled(WebElement element) {
//    	log.info(String.format("Waiting for WebElement with locator: [%s] to be disabled", _locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> !element.isEnabled());
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Waits for the WebElement to be selected
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is selected within the default wait time
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public boolean waitForSelected(WebElement element) {
//    	log.info(String.format("Waiting for WebElement with locator: [%s] to be selected",_locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> element.isSelected());
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Navigates to the specified URL
	 *
	 * @param _url URL to navigate to
	 */
	public void navigateTo(String _url) {
//    	log.info(String.format("Navigating to the following URL: [%s]", _url));
		driverManager.getWebDriver().navigate().to(_url);
	}

	/**
	 * Navigates backwards
	 */
	public void navigateBack() {
//    	log.info("Navigating to the previous page..");
		driverManager.getWebDriver().navigate().back();
	}

	/**
	 * Navigates forward
	 */
	public void navigateForward() {
//    	log.info("Navigating to the next page..");
		driverManager.getWebDriver().navigate().forward();
	}

	/**
	 * Switch tabs to the specified tab. Tab is chosen based on the title of the tab
	 *
	 * @param _tab Tab Title
	 */
	public void switchTab(String _tab) {
//    	log.info(String.format("Switching to tab using title value: [%s]..", _tab));
		driverManager.getWebDriver().switchTo().window(_tab);
	}

	/**
	 * Switch tabs to the specified tab. Tab is chosen based on the index of the tab
	 *
	 * @param _tab Tab Index
	 */
	public void switchTab(int _tab) {
//    	log.info(String.format("Switching to tab using index value: [%d]..", _tab));
		driverManager.getWebDriver().switchTo().window(Integer.toString(_tab));
	}

	/**
	 * Inputs a String into a WebElement using Selenium's built-in sendKeys
	 * functionality. Clears the element prior to inputting the text.
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _input Text to send to the WebElement
	 */
	public void inputText(WebElement element, String _input) {
//    	log.info(String.format("Inputting the following text: [%s] into the given WebElement: [%s]",  _input, _by.toString()));

		element.clear();
		element.sendKeys(_input);
	}

	public void inputTextByCharacter(WebElement element, String _input) throws InterruptedException {
//    	log.info(String.format("Inputting the following text: [%s] into the given WebElement: [%s]", _input, _by.toString()));

		element.clear();
		element.click();
		for (int i = 0; i < _input.length(); i++) {
			char c = _input.charAt(i);
			String s = new StringBuilder().append(c).toString();
			element.sendKeys(s);
			Thread.sleep(200);
		}
	}

	public void validateElementSelected(WebElement element) throws ValidationException {
		if (element.isSelected()) {
			logger.info(element + ": element is Selected");
			// return true;
		} else {
			throw new ValidationException("Element is not selected");
			// return false;
		}
	}

	public void moveToElement(WebElement element) {
		try {
			final Actions builder = new Actions(driverManager.getWebDriver());
			builder.moveToElement(element);
			builder.perform();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Appends a String into a WebElement using Selenium's built-in sendKeys
	 * functionality.
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _input Text to send to the WebElement
	 */
	public void appendText(WebElement element, String _input) {
//    	log.info(String.format("Appending the following text: [%s] into the given WebElement: [%s]", _input, _by.toString()));

		element.sendKeys(_input);
	}

	/**
	 * Clears the text of the given WebElement using Selenium's built-in clear()
	 * functionality
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void clearText(WebElement element) {
//    	log.info(String.format("Clearing the text from the given WebElement: [%s]",  _by.toString()));
		element.clear();
	}

	/**
	 * Selects an option from the drop-down menu by its value
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByValue(WebElement element, String _value) {
//    	log.info(String.format("Selecting the following option: [%s] from the given dropdown WebElement: [%s] by VALUE",_value, _by.toString()));

		Select select = new Select(getElementWhenVisible(element));
		select.selectByValue(_value);
	}

	/**
	 * Selects an option from the drop-down menu by its value
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByText(WebElement element, String _value) {
//    	log.info(String.format("Selecting the following option: [%s] from the given dropdown WebElement: [%s] by TEXT",
//                _value, _by.toString()));
		Select select = new Select(getElementWhenVisible(element));
		select.selectByVisibleText(_value);
	}

	/**
	 * Selects an option from the drop-down menu by its index
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByIndex(WebElement element, int _value) {
//    	log.info(String.format("Selecting the following option at index: [%d] from the given dropdown WebElement: [%s]",
//                _value, _by.toString()));
		Select select = new Select(getElementWhenVisible(element));
		select.selectByIndex(_value);
	}

	public void scrollIntoView(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
				element);
		Thread.sleep(500);
		((JavascriptExecutor) driverManager.getWebDriver()).executeScript("window.scrollBy(0,-150)");
		Thread.sleep(500);
	}

	/**
	 * Clicks the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 * @throws InterruptedException
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public void clickElement(WebElement element) throws InterruptedException {

		try {

			// ScreenShot.takeScreenShot(Drivers.getInstance().getDashDriver(), "PRE
			// SCROLL");
			scrollIntoView(element);
			// ScreenShot.takeScreenShot(Drivers.getInstance().getDashDriver(), "POST SCROLL
			// PRE CLICK");
			element.click();

		} catch (ElementClickInterceptedException ex) {
//        	log.info("Caught an ElementClickInterceptedException. Will try close info dialog and reclick", ex);
//        	String fname = ScreenShot.takeScreenShot(Drivers.getInstance().getDashDriver(), this.getClass().getName());
//			log.error("Caught exception screenShotName=" + fname);
//			List<WebElement> els = driverManager.getWebDriver().findElements(By.xpath("//button[@class='con-x']"));
//			if (els.size() > 0 && els.get(0).isDisplayed()) {
//        		log.info("Clicking on possible dialog");
//				els.get(0).click();
//				Thread.sleep(500);
//        		log.info("After Clicking on possible dialog");
			// }
			try {
//        		log.info("Reclicking the element");
				element.click();
//        		log.info("After reclicking the element");
			} catch (Exception e) {
//            	log.info("Caught an exception clicking an element, falling back to javascript click", e);
				Thread.sleep(500);
				((JavascriptExecutor) driverManager.getWebDriver()).executeScript("arguments[0].click();", element);
//            	log.info("After clicking the element with JS");

			}

		} catch (Exception ex) {
//        	log.info("Caught an exception clicking an element, falling back to javascript click", ex);
			Thread.sleep(500);
			((JavascriptExecutor) driverManager.getWebDriver()).executeScript("arguments[0].click();", element);
//        	log.info("After clicking the element with JS2");
			ex.printStackTrace();
		}
	}

	public void clickElementJS(WebElement element) throws InterruptedException {
//    	log.info("Clicking the given WebElement with javascript");
		try {
			scrollIntoView(element);
			((JavascriptExecutor) driverManager.getWebDriver()).executeScript("arguments[0].click();", element);

		} catch (ElementClickInterceptedException ex) {
//        	log.info("Caught an ElementClickInterceptedException. Will try close info dialog and reclick", ex);
//			List<WebElement> els = driverManager.getWebDriver().findElements(By.xpath("//button[@class='con-x']"));
//			if (els.size() > 0 && els.get(0).isDisplayed()) {
//				els.get(0).click();
//				Thread.sleep(500);
//			}
			((JavascriptExecutor) driverManager.getWebDriver()).executeScript("arguments[0].click();", element);

		} catch (Exception ex) {
//        	log.info("Caught an exception clicking an element, falling back to javascript click", ex);
			Thread.sleep(500);
			((JavascriptExecutor) driverManager.getWebDriver()).executeScript("arguments[0].click();", element);

		}
	}

	public void clickElement(WebElement _visibleElement, WebElement _clickElement) throws InterruptedException {
//    	log.info(String.format("Clicking the given WebElement: [%s]", _visibleBy.toString()));
		try {
			scrollIntoView(_visibleElement);
			_clickElement.click();
		} catch (Exception ex) {
//        	log.info("Caught exception trying to click element, will retry in 1 second incase it is transient");
			Thread.sleep(500);
			// Scroll down a bit in case another object like "Chat" is blocking the element
			((JavascriptExecutor) driverManager.getWebDriver()).executeScript("return window.scrollBy(0,100);");
			Thread.sleep(500);
			_visibleElement.click();
		}
	}

	public boolean isFieldActive(WebElement _locator) throws Exception {
//        log.info(String.format("Checking if field with locator: [%s] is active", _locator));
		WebElement we = getElementWhenVisible(_locator);
		if (we.getAttribute("aria-checked") != null) {
			return we.getAttribute("aria-checked").equals("true");
		} else {
			String _attrVal = we.getAttribute("type");

			if (_attrVal == null || _attrVal.isEmpty()) {
				we = getElementWhenVisible(By.xpath(_locator + "/../input"));
				_attrVal = we.getAttribute("type");
			}
			if (_attrVal.equals("radio")) {
				// return (Boolean) ((JavascriptExecutor)
				// CommonUtil.getDriver()).executeScript("return arguments[0].checked;", we);
				return false;
			} else {
				return we.isSelected();
			}
		}

	}

	/**
	 * Get the active webElement in a list of Elements based on Xpath Overload to
	 * allow removal of spaces, line breaks and tabs
	 *
	 * @param _text Text to compare
	 */
	public WebElement getActiveWebElement(List<WebElement> allElements) throws ValidationException {
//    	dismissHelper();

		if (allElements.size() == 0) {
			throw new ValidationException(String.format("Failed to find any WebElements with given xpath."));
		}

		for (int i = 0; i < allElements.size(); i++) {
			if (allElements.get(i).isDisplayed()) {
				return allElements.get(i);
			}
		}

		throw new ValidationException(String.format("Failed to find active WebElement."));
	}

	public WebElement getActiveWebElementFromWebElement(WebElement element) throws ValidationException {
//    	dismissHelper();
		String[] a = element.toString().split("xpath:");
		String xpath = a[a.length - 1].trim();
		String modifiedXpath = xpath.substring(0, xpath.length() - 1);
		System.out.println("XPath: " + modifiedXpath);
		List<WebElement> allElements = driverManager.getWebDriver().findElements(By.xpath(modifiedXpath));
		if (allElements.size() == 0) {
			throw new ValidationException(String.format("Failed to find any WebElements with given xpath."));
		}

		for (int i = 0; i < allElements.size(); i++) {
			if (allElements.get(i).isDisplayed()) {
				return allElements.get(i);
			}
		}

		throw new ValidationException(String.format("Failed to find active WebElement."));
	}

	/**
	 * Validate the text of the given WebElement against the supplied text By
	 * default no spaces, line breaks of tabs are removed
	 *
	 * @param _text Text to compare
	 */
	public void validateText(WebElement element, String _text, boolean stripSpacesTabsNewLines)
			throws ValidationException {
		String toCompare = element.getText().trim();
		validateText(toCompare, _text, stripSpacesTabsNewLines);
	}

	public void validateText(WebElement element, String _text) throws ValidationException {
		String toCompare = element.getText().trim();
		validateText(toCompare, _text, true);
	}

	public void validateTextWithStripSpacesTrue(WebElement element, String _text) throws ValidationException {
		String toCompare = element.getText().trim();
		validateText(toCompare, _text, true);
	}

	public void validateTextWithStripSpacesFalse(WebElement element, String _text) throws ValidationException {
		String toCompare = element.getText().trim();
		validateText(toCompare, _text, false);
	}

	public void validateValue(WebElement ele, String _text) throws ValidationException {
		String toCompare = ele.getAttribute("value");
		_text = replaceStringTableElements(_text);
		validateText(toCompare, _text, true);
//		        if(!toCompare.equals(_text)) {
//		            throw new ValidationException(String.format(
//		                    "Failed to validate the given value against the value of the WebElement.\n" +
//		                            "--------------------------\n" +
//		                            "Element:  \"%s\"\n" +
//		                            "Expected: \"%s\"\n" +
//		                            "Received: \"%s\"", element, _text, toCompare)
//		            );
//		        }
	}

	public String stripSpacesTabsNewLines(String text) {
		try {
			text = text.replaceAll("[\\n\\t\\r ]", "");

			text = text.replaceAll("[\\p{Cf}]", "");

			text = text.replaceAll("[^\\x00-\\x7f]", "");

			text = text.replaceAll("[^a-zA-Z0-9\\s+]", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return text;

	}

	public void validatePlaceholder(WebElement ele, String placeholder) throws ValidationException {
		placeholder = searchDataDictionary(placeholder);
		validateString(retrieveValuePlaceholder(ele), placeholder);
	}

	public void validateText(String toCompare, String _text, boolean stripSpacesTabsNewLines)
			throws ValidationException {
		if (stripSpacesTabsNewLines) {
			toCompare = toCompare.replaceAll("[\\n\\t\\r ]", "");
			_text = _text.replaceAll("[\\n\\t\\r ]", "");

			toCompare = toCompare.replaceAll("[\\p{Cf}]", "");
			_text = _text.replaceAll("[\\p{Cf}]", "");

			toCompare = toCompare.replaceAll("[^\\x00-\\x7f]", "");
			_text = _text.replaceAll("[^\\x00-\\x7f]", "");

			toCompare = toCompare.replaceAll("[^a-zA-Z0-9\\s+]", "");
			_text = _text.replaceAll("[^a-zA-Z0-9\\s+]", "");
		}

		String toCompare2 = toCompare.replaceAll("â€™", "'");
		String _text2 = _text.replaceAll("â€™", "'");
		if (!toCompare.equals(toCompare2)) {
//    		log.info("Updated toCompare=\"" + toCompare + "\" toCompare2=\"" + toCompare2 + "\"");
		}
		if (!_text.equals(_text2)) {
//    		log.info("Updated _text=\"" + _text + "\" _text2=\"" + _text2 + "\"");
		}
//    	log.info("validateText toCompare2=" + toCompare2 + " _text2=" + _text2);
		if (!toCompare2.equals(_text2)) {
			throw new ValidationException(
					String.format("Failed to validate the given text against the text of the WebElement.\n"
							+ "--------------------------\n" + "Element:  \"%s\"\n" + "Expected: \"%s\"\n"
							+ "Received: \"%s\"", toCompare, _text2, toCompare2));
		}
	}

	/**
	 * Validate the text of the given WebElement against the supplied text array. If
	 * any match, validation passes. By default no spaces, line breaks of tabs are
	 * removed
	 *
	 * @param _text Text to compare
	 */
	public void validateText(WebElement element, String[] _textA) throws ValidationException {
		String toCompare = element.getText();
//    	log.info("Entering validateText");
		for (String _text : _textA) {
//    		log.info("_textA element " + _text);
		}

		boolean foundMatch = false;
		for (String _text : _textA) {
			if (toCompare.equals(_text)) {
				foundMatch = true;
				break;
			}
		}

		if (!foundMatch) {
			throw new ValidationException(String.format(
					"Failed to validate the given text against the text array of the WebElement.\n"
							+ "--------------------------\n" + "Element:  \"%s\"\n" + "Received: \"%s\"",
					element, toCompare));
		}
	}

	public void validateTextRegEx(WebElement element, String patternS) throws ValidationException {
//    	log.info("Entering validateTextRegEx patternS=" + patternS);
		Pattern pattern = Pattern.compile(patternS);
		Matcher matcher = pattern.matcher(element.getText());
		if (matcher.find() == false) {
			throw new ValidationException(
					"Unable to find text matching pattern=" + patternS + "\n in text=" + element.getText());
		}
	}

	public void validateTextRegEx(String matchingText, String patternS) throws ValidationException {
//    	log.info("Entering validateTextRegEx patternS=" + patternS);
		Pattern pattern = Pattern.compile(patternS);
		Matcher matcher = pattern.matcher(matchingText);
		if (matcher.find() == false) {
			throw new ValidationException(
					"Unable to find text matching pattern=" + patternS + "\n in text=" + matchingText);
		}
	}

	public void validateElementDisplayed(WebElement element) throws ValidationException {
//    	log.info("Entering validateTextRegEx patternS=" + patternS);

		if (element.isDisplayed() == false) {
			throw new ValidationException("Element : " + element + " not displayed on UI");
		}
	}

	public void validateElementNotDisplayed(WebElement element) throws ValidationException {
//    	log.info("Entering validateTextRegEx patternS=" + patternS);
		try {
			if (element.isDisplayed())
				throw new ValidationException("Element : " + element + " displayed on UI");
		} catch (NoSuchElementException e) {
			System.out.println("Element Not Displayed. So not throwing exception");
		}
	}

	public boolean compareText(WebElement element, String _text) {
		String toCompare = element.getText();
		return toCompare.equals(_text);
	}

	public boolean compareText(String toCompare, String _text) {
		return toCompare.equals(_text);
	}

	public boolean compareTextIgnoreCase(String toCompare, String _text) {
		return toCompare.equalsIgnoreCase(_text);
	}

	public boolean compareMapValues(HashMap<String, List<String>> toCompare, HashMap<String, List<String>> _text) {
		return toCompare.equals(_text);
	}

	public String retrieveValue(WebElement el) {
		return el.getAttribute("value");
	}

	public List<String> retrieveValuesFromSelect(WebElement el) {
		Select select = new Select(el);
		return retrieveValuesFromSelect(select);
	}

	public List<String> retrieveValuesFromSelect(Select select) {
		List<WebElement> els = select.getOptions();
		List<String> vals = new ArrayList<String>();
		for (WebElement el : els) {
			vals.add(el.getText());
		}
		return vals;
	}

	public String retrieveValueFromSelect(WebElement el) {
		Select select = new Select(el);
		return retrieveValueFromSelect(select);
	}

	public String retrieveValueFromSelect(Select select) {
		WebElement el = select.getFirstSelectedOption();
		return el.getText();
	}

	public String retrieveValuePlaceholder(WebElement el) {
		return el.getAttribute("placeholder");
	}

	/**
	 * Validate that the text of the given WebElement contains the supplied text
	 *
	 * @param _by   Locator to the WebElement to act on
	 * @param _text Text to find
	 */
	public void validateTextContains(WebElement element, String _text) throws ValidationException {
		String toCompare = element.getText();
		if (!toCompare.contains(_text)) {
			throw new ValidationException(
					String.format("Failed to validate if the given text is contained in the text of the WebElement.\n"
							+ "--------------------------\n" + "Element:  \"%s\"\n" + "Expected: \"%s\"\n"
							+ "Received: \"%s\"", element, _text, toCompare));
		}
	}

	public void validateTextContains(String actualText, String expectedText) throws ValidationException {
//    	log.info("validateTextContains actualText=" + actualText + " expectedText=" + expectedText);
		if (!actualText.contains(expectedText)) {
			throw new ValidationException(
					String.format(
							"Failed to validate if the given text is contained in the text.\n"
									+ "--------------------------\n" + "Expected: \"%s\"\n" + "Received: \"%s\"",
							actualText, expectedText));
		}
	}

	public void validateText(String text1, String text2) throws ValidationException {
		validateText(text1, text2, null, null);
	}

	public void validateText(String text1, List<String> text2) throws ValidationException {
		if (!text2.contains(text1)) {
			throw new ValidationException(String.format(
					"Failed to validate if the given text is contained in the text of the WebElement.\n"
							+ "--------------------------\n" + "Expected: \"%s\"\n" + "Received: \"%s\"",
					text2, text1));
		}
	}

	public void validateTextDisplayed(String text) throws ValidationException {
		try {
			WebElement element = driverManager.getWebDriver().findElement(By.xpath("//*[text()='" + text + "']"));
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			throw new ValidationException(
					String.format("Failed to validate there is no WebElement with expected text.\n"
							+ "--------------------------\n" + "Expected: \"%s\"\n", text));
		}
	}

	public void validateText(String text1, String text2, IFormatFilter filter1, IFormatFilter filter2)
			throws ValidationException {
		if (filter1 != null) {
			text1 = filter1.filter(text1);
		}
		if (filter2 != null) {
			text2 = filter2.filter(text2);
		}
		String t1 = text1.replaceAll("â€™", "'");
		String t2 = text2.replaceAll("â€™", "'");
		if (!t1.equals(text1)) {
//    		log.info("Updated text1=\"" + text1 + "\" t1=\"" + t1 + "\"");
		}
		if (!t2.equals(text2)) {
//    		log.info("Updated text2=\"" + text2 + "\" t2=\"" + t2 + "\"");
		}
//    	log.info("Validate text t1=" + t1 + " t2=" + t2);
		if (!text1.equals(text2)) {
			throw new ValidationException(
					String.format(
							"Failed to validate if the given text is contained in the text of the WebElement.\n"
									+ "--------------------------\n" + "Expected: \"%s\"\n" + "Received: \"%s\"",
							t1, t2));
		}
	}

	public void validateTooltip(WebElement we, String message) throws ValidationException {
//        log.info(String.format("Attempting to validate the tool-tip has value: [%s]", message));
		if (isElementVisible(we)) {
			System.out.println("web element trimmed text: " + we.getAttribute("class"));
			String[] ttMsg = we.getText().trim().split("\n");
			if (message.equalsIgnoreCase(ttMsg[1])) {
//            	log.info("Successfully validated tooltip message");
			} else {
//            	log.error("Expected value:" + message);
//            	log.error("Actual value:" + ttMsg[1]);
				throw new ValidationException("Failed to validate - Tool tip message does not match.");
			}
		} else {
			throw new ValidationException("Failed to validate - Tool Tip message is not visible - Check your locator");
		}
	}

	public void validateBackgroundColor(String[] _colorOption, WebElement element) throws ValidationException {
//    	log.info("Entering validateBackgroundColor");
		if (Arrays.asList(_colorOption).contains(element.getCssValue("background-color").trim())) {
//    		log.info("validation passed");
		} else {
			throw new ValidationException(
					"Failed validating the background color color=" + element.getCssValue("background-color"));
		}
	}

	public void validateBackgroundColor(String[] _colorOption, String elementBackColor) throws ValidationException {
//    	log.info("Entering validateBackgroundColor");
		if (Arrays.asList(_colorOption).contains(elementBackColor)) {
//    		log.info("validation passed");
		} else {
			throw new ValidationException("Failed validating the background color color=" + elementBackColor);
		}
	}

	public void validateColor(String[] _colorOption, WebElement element) throws ValidationException {
//    	log.info("Entering validateColor");
		validateColor(_colorOption, element.getCssValue("color").trim());
	}

	public void validateColor(String[] _colorOption, String color) throws ValidationException {
//    	log.info("Entering validateColor color=" + color);

		System.out.println("color=" + color);
		System.out.println("_colorOption=" + Arrays.deepToString(_colorOption));

		if (Arrays.asList(_colorOption).contains(color)) {
//    		log.info("validation passed");
		} else {
			throw new ValidationException(
					"Failed validating the color color=" + color + " colorOption=" + Arrays.deepToString(_colorOption));
		}
	}

	public boolean isColor(String[] _colorOption, String color) {
		return Arrays.asList(_colorOption).contains(color);
	}

	/**
	 * Validate the visibility status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateVisible(WebElement ele) throws ValidationException {
		try {
			waitForVisible(ele);
		} catch (Exception ex) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is visible.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", ele));
		}
	}

	/**
	 * Validate the invisibility status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateInvisible(WebElement ele) throws ValidationException {
		if (!waitForInvisible(ele)) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is invisible.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", ele));
		}
	}

	/**
	 * Validate the enabled status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateEnabled(WebElement ele) throws ValidationException {
		if (!waitForEnabled(ele)) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is enabled.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", ele));
		}
	}

	/**
	 * Validate the disabled status of the given WebElement. Should be visible and
	 * disabled
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateDisabled(WebElement ele) throws ValidationException {
		getElementWhenVisible(ele);
		boolean disabled = waitForDisabled(ele);
		if (disabled == true) {
			// Make sure it stays disabled
			getElementWhenVisible(ele);
			disabled = waitForDisabled(ele);
		}
		if (!disabled) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is disabled.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", ele));
		}
	}

	/**
	 * Validate the given attribute on the given WebElement
	 *
	 * @param _by       Locator to the WebElement to act on
	 * @param _attr     Attribute to find on the WebElement
	 * @param _expected Expected value of the element
	 */
	public void validateAttribute(WebElement element, String _attr, String _expected) throws ValidationException {
		String toCompare = element.getAttribute(_attr).trim();
//        log.info("validateAttribute _attr=" + _attr + " _expected=" + _expected);;
		if (!toCompare.equals(_expected)) {
			throw new ValidationException(String.format(
					"Failed to validate WebElement attribute against expected value.\n" + "--------------------------\n"
							+ "Element:  \"%s\"\n" + "Attribute: \"%s\"\n" + "Expected: \"%s\"\n" + "Received: \"%s\"",
					element, _attr, _expected, toCompare));
		}
	}

	public void validateAttributeContains(WebElement element, String _attr, String _expected)
			throws ValidationException {
		String toCompare = element.getAttribute(_attr);
//        log.info("validateAttributeContains _attr=" + _attr + " _expected=" + _expected + " toCompare=" + toCompare);
		if (!toCompare.contains(_expected)) {
			throw new ValidationException(
					String.format("Failed to validate WebElement attribute contains against expected value.\n"
							+ "--------------------------\n" + "Element:  \"%s\"\n" + "Expected Contains: \"%s\"\n"
							+ "Received: \"%s\"", element, _attr, toCompare));
		}
	}

	public void validateAttributeContains(WebElement element, String _attr, String[] _expecteds)
			throws ValidationException {
		String toCompare = element.getAttribute(_attr);
//        log.info("validateAttributeContains _attr=" + _attr + " toCompare=" + toCompare);
		boolean foundIt = false;

		for (String _expected : _expecteds) {
//        	log.info("validateAttributeContains _expected=" + _expected);
			if (toCompare.contains(_expected)) {
				foundIt = true;
//	        	log.info("validateAttributeContains found match");
				break;
			}
		}
		if (foundIt == false) {
			throw new ValidationException(
					String.format("Failed to validate WebElement attribute contains against expected value.\n"
							+ "--------------------------\n" + "Element:  \"%s\"\n" + "Expected Contains: \"%s\"\n"
							+ "Received: \"%s\"", element, _attr, toCompare));
		}
	}

	public void validateFieldErrorNoRedField(String _xpath, String _expected) throws ValidationException {
//    	log.info("Entering validateFieldErrorNoRedField _xpath=" + _xpath + " _expected=" + _expected);   	
		By locator = By.xpath(_xpath + "div//small");
		WebElement el = getElementWhenVisible(locator);
		validateText(el.getText(), _expected);
		validateColor(ColorConstants.RED, el);
//    	log.info("Exiting validateFieldErrorNoRedField");
	}

	public void validateFieldErrorWithString(String _xpath, String _expected) throws ValidationException {
//    	log.info("Entering validateFieldError _xpath=" + _xpath + " _expected=" + _expected);   	
		By locator = By.xpath(_xpath + "/parent::div//small");
		WebElement el = getElementWhenVisible(locator);
		validateText(el.getText(), _expected);
		validateColor(ColorConstants.RED, el);

		// Validate the parent is PINK
		el = getElementWhenVisible(By.xpath(_xpath));
		validateBackgroundColor(ColorConstants.PINK, el);

//    	log.info("Exiting validateFieldError");
	}

	public void validateFieldError(WebElement _xpath, String _expected) throws ValidationException {
		WebElement el = getElementWhenVisible(_xpath);
		WebElement el1 = el.findElement(By.xpath("/parent::div//small"));
		validateText(el1.getText(), _expected);
		validateColor(ColorConstants.RED, el1);

		// Validate the parent is PINK
		el = getElementWhenVisible(_xpath);
		validateBackgroundColor(ColorConstants.PINK, el1);

//    	log.info("Exiting validateFieldError");
	}

	public void validateNoFieldError(String _xpath) throws ValidationException {
//    	log.info("Entering validateNoFieldError _xpath=" + _xpath);
		By locator = By.xpath(_xpath + "/parent::div//small");
		boolean gotError = true;
		try {
			WebElement el = getElementWhenVisible(locator);
//    		log.info("Found error element text=" + el.getText());
		} catch (TimeoutException ex) {
//    		log.info("No error element was visible");
			gotError = true;
		}
		validateBoolean(gotError, true);

//    	log.info("Exiting validateNoFieldError");
	}

	/**
	 * Additional helper validation methods
	 */
	public void validateLong(long _value, long _expected) throws ValidationException {

		if (_value == _expected) {
//        	log.info("validateLong passed _value=" + _value + " _expected=" + _expected);
		} else {
			throw new ValidationException("Failed to validateLong  _value=" + _value + " _expected=" + _expected);
		}
	}

	protected WebDriverWait getWait(int _timer) {
		return new WebDriverWait(driverManager.getWebDriver(), _timer);
	}

	protected void waitForVisible(By _locator, int waitSeconds) {
		logger.info(String.format("Waiting for WebElement with locator: [%s] to be visible", _locator.toString()));
		// Remove the timeout catch
		// Calling routine are not checking the return value and errors result since the
		// element is not
		// visible
//	        try {
//	            return getWait(waitSeconds).until((_driver) -> _driver.findElement(_locator).isDisplayed());
//	        } catch (TimeoutException e) {
//	            return false;
//	        }
		getWait(waitSeconds).until((_driver) -> _driver.findElement(_locator).isDisplayed());
	}

	public void validateBoolean(boolean _value, boolean _expected) throws ValidationException {

		if (_value == _expected) {
//        	log.info("validateBoolean passed _value=" + _value + " _expected=" + _expected);
		} else {
			throw new ValidationException("Failed to validateBoolean  _value=" + _value + " _expected=" + _expected);
		}
	}

	public void validateNotNull(Object o) throws ValidationException {

		if (o != null) {
//        	log.info("validateNotNull passed o==" + o);
		} else {
			throw new ValidationException("Failed to validateNotNull");
		}
	}

	public void validateString(String _value, String _expected) throws ValidationException {
		if (_value.contentEquals(_expected)) {
//        	log.info("validateString passed _value=" + _value + " _expected=" + _expected);
		} else {
			throw new ValidationException("Failed to validateString  _value=" + _value + " _expected=" + _expected);
		}
	}

	public void validateBetween(double _value, double _lowerBound, double _upperBound) throws ValidationException {

		if (_value >= _lowerBound && _value <= _upperBound) {
//        	log.info("validateInt passed _value=" + _value + " _lowerBound=" + _lowerBound + " _upperBound=" + _upperBound);
		} else {
			throw new ValidationException("Failed to validateInt  _value=" + _value + " _lowerBound=" + _lowerBound
					+ " _upperBound=" + _upperBound);
		}
	}

	public void validateCurrencyFormat(String currency) throws ValidationException {
		Pattern pat1 = Pattern.compile("^\\$(?:0|[1-9]\\d{0,2}(?:\\,\\d{3})*).\\d{2}$");
		Pattern pat2 = Pattern.compile("^(?:0|[1-9]\\d{0,2}(?:\\ \\d{3})*),\\d{2} \\$$");
		Matcher match1 = pat1.matcher(currency);
		Matcher match2 = pat2.matcher(currency);
//		log.info("validateCurrencyFormat currency=" + currency);
		if ((match1.find() || match2.find()) == false) {
			throw new ValidationException("Invalid currency format " + currency);
		}
	}

	public void validateDateFormat(String dateS) throws ValidationException {
		SimpleDateFormat format = TestLanguage.getLanguage() == Language.English ? new SimpleDateFormat("MMMM dd, yyyy")
				: new SimpleDateFormat("dd MMMM yyyy", Locale.CANADA_FRENCH);
//    	log.info("validateDateFormat dateS=" + dateS);
		try {
			format.parse(dateS);
		} catch (ParseException e) {
			throw new ValidationException("Invalid formatted date " + dateS);
		}
	}

	/**
	 * Return the errors associated with the given element locator. Typically, we
	 * will find the parent div object, and then any child <small> tags, as these
	 * are how DASH error messages are formatted.
	 *
	 * Note: This will not always work, as the error mechanism is not always the
	 * same. This is primarily for field validation
	 *
	 * @param _xpath XPATH String
	 * @return Collection of errors found associated with the element xpath
	 */
	private String[] getFieldErrors(String _xpath) {
		By locator = By.xpath(_xpath + "/parent::div//small");
		List<WebElement> elements = driverWait.getDriverWait()
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		String[] toReturn = new String[elements.size()];
		for (int i = 0; i < elements.size(); i++) {
			toReturn[i] = elements.get(i).getText();
		}
		return toReturn;
	}

	public String replaceStringTableElements(String _input) {
		if (_input.toUpperCase().equals("<BLANK>")) {
			return "";
		} else if (_input.toUpperCase().equals("<SPACE>")) {
			return " ";
		}
		return searchDataDictionary(_input);
	}

	public String searchDataDictionary(String _input) {
//    	log.info(String.format("Searching for an entry in the data dictionary for input: %s", _input));
		if (_input.startsWith("dd:")) {
//        	log.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			String lang = TestLanguage.getLanguage().toString();
			return DataDictionary.getMessageFromDataDictionary(lang, parts[1]);
		}
		return _input;
	}

	public String searchDataDictionaryDontStripHTML(String _input) {
//    	log.info(String.format("Searching for an entry in the data dictionary dont strip html for input: %s", _input));
		if (_input.startsWith("dd:")) {
//        	log.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			String lang = TestLanguage.getLanguage().toString();
			return DataDictionary.getMessageFromDataDictionaryDontStripHTML(lang, parts[1]);
		}
		return _input;
	}

	public String searchDataDictionary(String _input, Language lang) {
//    	log.info(String.format("Searching for an entry in the data dictionary for input: %s", _input));
		if (_input.startsWith("dd:")) {
//        	log.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			return DataDictionary.getMessageFromDataDictionary(lang.toString().substring(0, 1), parts[1]);
		}
		return _input;
	}

	private static String locUpdateSuccessAlert = ".//div[@id='successErrorAlert']/div";
	private static String locUpdateSuccessAlertButton = "//button[@id='success-error-banner']/../div";
	private static String locUpdateSuccessAlertText = "//span[@ng-bind-html='alert.msg']";

	public void validateUpdatedSuccessAlert(String msgKey) throws Exception {
//    	log.info("Entering validateUpdatedSuccessAlert");
		WebElement locUpdateSuccAlert = driverManager.getWebDriver().findElement(By.xpath(locUpdateSuccessAlert));
		waitForVisible(locUpdateSuccAlert);
//    	WebElement el = getElementWhenVisible(By.xpath(locUpdateSuccessAlertButton));   	
		validateBackgroundColor(ColorConstants.GREEN, locUpdateSuccessAlertButton);
		validateText(locUpdateSuccessAlertText, searchDataDictionary(msgKey));
		if (driverManager.isMobile()) {
			WebElement el = driverManager.getWebDriver().findElement(By.xpath("//button[@id='success-error-banner']"));
			clickElementJS(el);
		} else {
			WebElement el = driverManager.getWebDriver().findElement(By.xpath("//button[@id='success-error-banner']"));
			clickElement(el);
		}

//    	log.info("Exiting validateUpdatedSuccessAlert");
	}

	public void validateUpdatedFailureAlert(String msgKey) throws Exception {
//    	log.info("Entering validateUpdatedFailureAlert");

		WebElement el = driverManager.getWebDriver().findElement(By.xpath(locUpdateSuccessAlert));
		validateBackgroundColor(ColorConstants.RED, el);
		validateText(locUpdateSuccessAlertText, searchDataDictionary(msgKey));
		WebElement alert = driverManager.getWebDriver()
				.findElement(By.xpath("//*[@id=\"success-error-banner\"]/span[1]"));
		clickElement(alert);

//    	log.info("Exiting validateUpdatedFailureAlert");
	}

	public boolean isAlertPresent() {
		try {
			driverManager.getWebDriver().switchTo().alert();
			return true;
		} catch (Exception Ex) {
			return false;
		}
	}

	public boolean todayAfter_yyyyMMdd(String dateS) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		boolean rv = false;
		try {
			Date d = format.parse(dateS);
			if (new Date().after(d)) {
				rv = true;
			}
		} catch (ParseException e) {
			throw new RuntimeException("Date format error on dateS=" + dateS, e);
		}
		return rv;
	}

	/**
	 * Takes a parent element and strips out the textContent of all child elements
	 * and returns textNode content only
	 * 
	 * @param e the parent element
	 * @return the text from the child textNodes
	 */
	public String getParentTextNode(WebElement e) {
		String text = e.getText().trim();
		List<WebElement> children = e.findElements(By.xpath("./*"));
		for (WebElement child : children) {
			text = text.replaceFirst(child.getText(), "").trim();
		}
		return text;
	}

	public String getRelativeURL(Language lang) {

		return null;
	}

	/**
	 * getting the text based the webelement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public String getText(WebElement element) {
//        WebElement element   = getElementWhenVisible(_by);
		String text = element.getText().replaceAll("&nbsp;", " ");
		return text;
	}

	public void dismissHelper() {
		// Dismiss helper
		List<WebElement> helper = driverManager.getWebDriver()
				.findElements(By.xpath("//*[@id=\"con-notification-title\"]"));

//		log.info("Dismiss helper. Popover list size: " + helper.size());
		if (helper.size() > 0 && helper.get(0).getAttribute("aria-hidden").equalsIgnoreCase("false")) {
//			log.info("Found popover");
			WebElement button = driverManager.getWebDriver()
					.findElement(By.xpath("//*[@id=\"con-notification\"]/button"));
			try {
				button.click();
			} catch (Exception ex) {
//				log.info("Unable to click con-notification button");
			}
		}
	}

	// Used to wait for page loading spinners for pages that load text early Eg.
	// Settings/Payments titles
	public void waitForSpinnersInvisible() {
		if (driverManager.isMobile()) {
			dismissHelper();
		}
		List<WebElement> spinners = driverManager.getWebDriver().findElements(By.xpath("//div[@class='spinner']"));
		// List<WebElement> spinners =
		// getAllVisibleElements(By.xpath("//div[@class='spinner']"));

//    	log.info("Wait for spinners invisible. list size: " + spinners.size());
		driverWait.getDriverWait().until(ExpectedConditions.invisibilityOfAllElements(spinners));
	}

	// Overloaded Method by passing attribute name as Parameter
	/**
	 * Get the Attribute of the passed Attribute name
	 *
	 * @param _by  Locator to the WebElement to act on
	 * @param Name of the Attribute for which we need value
	 */
	public String getAttribute(WebElement _element, String attributeName) {
//		WebElement _element=getElementWhenVisible(_by);
		return _element.getAttribute(attributeName);
	}

	/**
	 * Getting the text based on passed text/based on Data dictionary Label
	 *
	 * @param Input Text for which we need to get Value
	 */
	public String getInputText(String input) {
//		log.info(String.format("Scrubbing input text: [%s]...", input));
		String value = "";
		try {
			if (input == null) {
				return value;
			} else if (input.equals("BLANK")) {
				return value;
			} else if (input.equalsIgnoreCase("<phone_blank>")) {
				return "(___) ___-____";
			} else if (input.equalsIgnoreCase("<space>")) {
				value = " ";
			} else if (input.contains("<rtd:")) {
				// Regex match here
				Pattern pattern = Pattern.compile("<rtd:.*?>");
				Matcher matcher = pattern.matcher(input);
				StringBuffer buffer = new StringBuffer();
				int _counter = 0;
				while (matcher.find()) {
					// matcher.appendReplacement(buffer,
					// RunTimeData.getValue(matcher.group(_counter)).replaceAll("\\$", "\\\\\\$"));
					_counter++;
				}
				matcher.appendTail(buffer);
				value = buffer.toString();
			} else if (input.startsWith("dd:normalized:")) {
				input = input.replace("normalized:", "");
				value = searchDataDictionary(input);
			} else if (input.startsWith("dd:")) {
				value = searchDataDictionaryDontStripHTML(input);
			} else {
				value = input.trim().replace("<SPACE>", " ").replace("<space>", " ").replace("<blank>", "")
						.replace("<BLANK>", "").replace("<HASH>", "#").replace("<hash>", "#").replace("<#>", "#")
						.replace("<VERTICAL>", "|").replace("<vertical>", "|");
			}
		} catch (Exception e) {
//			log.error("An error occurred while processing input text", e);
		}
//		log.info("Returning value: " + value);
		return value;
	}

	/**
	 * Verifying that tool tip is checked
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void toolTipChecked(WebElement we) throws Exception {
//			log.info(String.format("Attempting to validate that tooltip [%s] is checked", _by));
		String ttVal = we.getCssValue("color").trim();
		if (Arrays.asList(ColorConstants.GREEN).contains(ttVal)) {
//	            log.info("Successfully validated tooltip as checked");
		} else {
			throw new Exception("failed to validate tool tip as checked. Original displayed color =" + ttVal);
		}
	}

	/**
	 * Verifying the password strength meter based on th passed length
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param length od the password
	 */
	public void passwordlength(WebElement we, String _lineLength) throws Exception {
//	        log.info(String.format("Attempting to validate tool-tip: [%s] strength meter length as: [%s]",_by ,_lineLength));
		int lineLength = Integer.parseInt(we.getAttribute("aria-valuenow"));
		if (_lineLength.equalsIgnoreCase("Too Short") && lineLength == 20) {
//				log.info("Line is too short.");
		} else if (_lineLength.equalsIgnoreCase("Short") && lineLength == 45) {
//				log.info("Line is short.");
		} else if (_lineLength.equalsIgnoreCase("Medium") && lineLength == 60) {
//				log.info("Line is medium.");
		} else if (_lineLength.equalsIgnoreCase("Long") && lineLength == 75) {
//				log.info("Line is long.");
		} else if (_lineLength.equalsIgnoreCase("Full") && lineLength == 100) {
//				log.info("Line is full.");
		} else {
			throw new ValidationException("Line length not identified");
		}
	}

	/**
	 * Verifying that tool tip is not checked
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void toolTipNotChecked(WebElement we) throws Exception {
//			log.info(String.format("Attempting to validate that tooltip [%s] is NOT checked", _by));
		String ttVal = we.getCssValue("color").trim();
		if (Arrays.asList(ColorConstants.GREY).contains(ttVal)) {
//	            log.info("Successfully validated tooltip as not checked");
		} else {
			throw new Exception("Failed to validate tool tip as checked. Original message =" + ttVal);
		}
	}

	/**
	 * Verifying tool tip colour
	 *
	 * @param _by      Locator to the WebElement to act on
	 * @param Expected Colour
	 */
	public boolean toolTipLineColor(WebElement _element, String color) throws Exception {
//			log.info("Attempting to validate the tooltip line color");
		String _backgroundColor = _element.getCssValue("background-color").trim();
		switch (color.toLowerCase().trim()) {
		case "red":
			return Arrays.asList(ColorConstants.RED).contains(_backgroundColor);
		case "amber":
			return Arrays.asList(ColorConstants.ORANGE).contains(_backgroundColor);
		case "green":
			return Arrays.asList(ColorConstants.GREEN).contains(_backgroundColor);
		}
		return false;
	}

	public String getProdName(String prodName) {
		String str = prodName;
		str = str.replaceAll("[^a-zA-Z0-9 ]", "");
		str = str.replaceFirst("TriangleTM", "Triangle");
		System.out.println("CPC is " + str);
		return str;
	}

	/**
	 * @throws ParseException
	 * @desc : This function helps in selecting the date field with value entered as
	 *       Today+/-n It takes value as Today+/-n
	 */
	public String getFormattedDate(String val) {
		// log.info("Attempting to get the formatted date");
		try {
			Date date = Calendar.getInstance().getTime();
			// log.info(String.format("Today's date: [%s]", date.toString()));
			if (val.equalsIgnoreCase("Today")) {
				val = new SimpleDateFormat("YYYY/MM/dd").format(date);
			} else if (val.split("/").length == 3) {
				String[] _split = val.split("/");
				int day = Integer.valueOf(_split[2]);
				int month = Integer.valueOf(_split[1]);
				int year = Integer.valueOf(_split[0]);
				Calendar calendar = Calendar.getInstance();
				calendar.set(year, month, day);
				val = new SimpleDateFormat("YYYY/MM/dd").format(calendar);
			} else if (val.toLowerCase().contains("today")) {
				String[] _values = new String[] {};
				int _multiplier = 1;
				if (val.contains("+")) {
					_values = val.split("\\+");
				} else if (val.contains("-")) {
					_values = val.split("\\-");
					_multiplier = -1;
				}

				int _y = Integer.parseInt(_values[1]);
				if (_y >= 0) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.DATE, (_y * _multiplier));
					val = new SimpleDateFormat("YYYY/MM/dd").format(calendar.getTime());
				}
			}
		} catch (Exception e) {
			// log.error("An error occurred while formatting the date.", e);
		}
		return val;
	}

	/**
	 * @throws ParseException
	 * @desc : This function helps in selecting the date field with value entered as
	 *       Today+/-n It takes value as Today+/-n
	 */
	public String getFormattedDate(String val, String format) {
		// log.info("Attempting to get the formatted date");
		try {
			Date date = Calendar.getInstance().getTime();
			// log.info(String.format("Today's date: [%s]", date.toString()));
			if (val.equalsIgnoreCase("Today")) {
				val = new SimpleDateFormat(format).format(date);
			} else if (val.split("/").length == 3) {
				String[] _split = val.split("/");
				int day = Integer.valueOf(_split[2]);
				int month = Integer.valueOf(_split[1]);
				int year = Integer.valueOf(_split[0]);
				System.out.println("Splitted values:" + month + "-" + day + "-" + year);
				Calendar calendar = Calendar.getInstance();
				calendar.set(year, month - 1, day);
				System.out.println("Calendar:" + calendar.getTime());
				val = new SimpleDateFormat(format).format(calendar.getTime());
				System.out.println("Formatted value: " + val);
			} else if (val.toLowerCase().contains("today")) {
				String[] _values = new String[] {};
				int _multiplier = 1;
				if (val.contains("+")) {
					_values = val.split("\\+");
				} else if (val.contains("-")) {
					_values = val.split("\\-");
					_multiplier = -1;
				}

				int _y = Integer.parseInt(_values[1]);
				if (_y >= 0) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.DATE, (_y * _multiplier));
					val = new SimpleDateFormat(format).format(calendar.getTime());
				}
			} else
				System.out.println("Incorrect Date Format");
		} catch (Exception e) {
			// log.error("An error occurred while formatting the date.", e);
		}
		return val;
	}

	/**
	 * fetches the Error message displayed by Element located by locName xpath value
	 * Verifies error message against Datadictionary Reference Verifies error
	 * verifies message text color
	 * 
	 * @param locName
	 * @param errorMessage
	 * @param colour
	 * @return
	 * @throws Exception
	 */
	public boolean verifyErrorMessage(WebElement locName, String errorMessage, String colour) throws Exception {
		String expectedmessage;
		String actualmessage;
		boolean status;
		try {
			expectedmessage = getInputText(errorMessage);
			actualmessage = getText(locName);
			System.out.println("Actual Message " + actualmessage);
			System.out.println("Expected Message " + expectedmessage);
			status = actualmessage.equalsIgnoreCase(expectedmessage);
			System.out.println("Starus " + status);
			String[] _compareColor = ColorConstants.getConstantValueForColor(colour);
			System.out.println("Colur Array " + _compareColor.length);
			System.out.println("Actual Colour " + locName.getCssValue("color").trim());
			if (Arrays.asList(_compareColor).contains(locName.getCssValue("color").trim())) {

//					log.info(String.format("Successfully validated - Error Message with color ", colour));
			} else {
				throw new ValidationException("Error Message is not of the expected color");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return status;
	}

	public String getXpath(WebElement element) {
		String str = element.toString();
		String[] listXpath = null;
		if (str.contains("xpath"))
			listXpath = str.split("xpath:");
		else
			Assert.fail("Invalid xpath");
		String xpath = listXpath[1].trim();
		return xpath.substring(0, xpath.length() - 1);
	}

	public Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	public String doAddDays(String baseDate) throws ParseException {
		String sourceDate = baseDate;
		SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
		Date myDate = format.parse(sourceDate);
		myDate = addDays(myDate, 1);
		String strMyDate = format.format(myDate);
		return strMyDate;
	}

	public Date getDay(String date, int days) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("mmddyyyy");
		Date refDate = stringToDate(date);
		System.out.println("date: " + dateFormat.format(refDate));
		Calendar c = Calendar.getInstance();
		c.setTime(refDate);
		c.add(Calendar.DATE, days);

		Date newDayDate = c.getTime();
		System.out.println("newDate: " + dateFormat.format(newDayDate));
		return newDayDate;
	}

	public Date stringToDate(String str) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("mmddyyyy");
		String dateString = str;
		Date date = dateFormat.parse(dateString);
		return date;
	}

	// All the methods with By

	public WebElement getElementWhenVisible(By _by) {
//	    	log.info(String.format("Attempting to find and return the WebElement with the following By value: [%s]",  _by.toString()));

		try {
			return driverWait.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(_by));
		} catch (Exception e) {
			return driverWait.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(_by));
		}
	}

	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	public boolean isElementVisible(By _by) {
		try {
			getElementWhenVisible(_by);
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}

	public List<WebElement> getAllVisibleElements(By _by, int minVisibleCount) throws InterruptedException {
//		    	log.info(String.format("Attempting to find and return all visible WebElement(s) with the following By value: [%s]",
//		                _by.toString()));
		long cutoff = new Date().getTime() + 10000;
		List<WebElement> elems = new ArrayList<WebElement>();
		int eCount = 0;
		do {
			eCount = 0;
			List<WebElement> els = driverManager.getWebDriver().findElements(_by);
			if (els != null) {
				eCount = els.size();
				for (WebElement e : els) {
					if (e.isDisplayed()) {
						elems.add(e);
					}
				}
				// Break if found visible elements
				// Check twice to ensure new elements don't become visible and send back
				// imcomplete list
				if (elems.size() > 0 && elems.size() >= minVisibleCount) {
					break;
				}
				elems.clear();
			}
			Thread.sleep(100);
		} while (new Date().getTime() < cutoff);

		if (elems.size() == 0) {
			if (eCount == 0) {
				throw new org.openqa.selenium.NoSuchElementException("_by=" + _by.toString());
			}
			throw new org.openqa.selenium.ElementNotVisibleException("_by=" + _by.toString());
		}

		return elems;
	}

	/**
	 * Waits for the element(s) at the given XPATH to be visible, and then returns a
	 * list of WebElement elements.
	 *
	 * @param _by locator for element to locate and check for visibility
	 * @return WebElement list if found
	 */
	public List<WebElement> getElementsWhenVisible(By _by) {
//		    	log.info(String.format("Attempting to find and return the WebElement(s) with the following By value: [%s]",  _by.toString()));
		return driverWait.getDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(_by));
	}

	public List<WebElement> getElementsWhenVisibleUsingWebElement(WebElement element) {
//    	log.info(String.format("Attempting to find and return the WebElement(s) with the following By value: [%s]",  _by.toString()));
		String[] a = element.toString().split("xpath:");
		String xpath = a[a.length - 1].trim();
		String modifiedXpath = xpath.substring(0, xpath.length() - 1);
		System.out.println("xapth from WebElement:" + modifiedXpath);
		return driverWait.getDriverWait()
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(modifiedXpath)));
	}

	/**
	 * Waits for an element to be visible. May be more than one element that matches
	 * xpath, will return first visible element.
	 *
	 * @param _by locator for element to locate and check for visibility
	 * @return WebElement if found
	 * @throws InterruptedException
	 */
	public WebElement getAnyElementWhenVisible(By _by) throws InterruptedException {
//		    	log.info(String.format("Attempting to find and return the WebElement with the following By value: [%s]",_by.toString()));

		long cutoff = new Date().getTime() + 10000;
		WebElement el = null;
		int eCount = 0;
		do {
			eCount = 0;
			try {
				List<WebElement> els = driverManager.getWebDriver().findElements(_by);
				if (els != null) {
					eCount = els.size();
					for (WebElement e : els) {
						if (e.isDisplayed()) {
							el = e;
							break;
						}
					}
				}
			} catch (StaleElementReferenceException e) {
				// Eat the exception, will correct next time through loop
			}
			Thread.sleep(100);
		} while (new Date().getTime() < cutoff);

		if (el == null) {
			if (eCount == 0) {
				throw new org.openqa.selenium.NoSuchElementException("_by=" + _by.toString());
			}
			throw new org.openqa.selenium.ElementNotVisibleException("_by=" + _by.toString());
		}

		return el;
	}

	/**
	 * Waits for the WebElement to be visible
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is visible within the default wait time
	 */
	public void waitForVisible(By _locator) {
//		    	log.info(String.format("Waiting for WebElement with locator: [%s] to be visible",_locator.toString()));

		driverWait.getDriverWait().until((_driver) -> _driver.findElement(_locator).isDisplayed());
	}

	public boolean waitForVisible_NoException(By _locator, int waitSeconds) {
//		    	log.info(String.format("Waiting for WebElement with locator: [%s] to be visible",_locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> _driver.findElement(_locator).isDisplayed());
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean waitForVisible_NoException(WebElement element, int waitSeconds) {
//    	log.info(String.format("Waiting for WebElement with locator: [%s] to be visible",_locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> element.isDisplayed());
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Waits for the WebElement to be invisible
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is visible within the default wait time
	 */
	public boolean waitForInvisible(By _locator) {
//		    	log.info(String.format("Waiting for WebElement with locator: [%s] to be NOT visible",_locator.toString()));

		try {
			// Use invisiblilityOfElementLocated instead of isDisplayed because isDisplayed
			// does not handle the case of the element no longer existing
			driverWait.getDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(_locator));
			return true;
			// return getWait().until((_driver) ->
			// !_driver.findElement(_locator).isDisplayed());
		} catch (TimeoutException e) {
			return false;
		}
	}
	public void waitForElementInVisible(WebElement element) {
        try {
        	driverWait.waitForElementInVisible(element);
        } catch (Exception ignored) {
        }
    }
	public boolean waitForEmptyText(WebElement _locator, int waitSeconds) {
//		    	log.info("Waiting for empty text locator=" + _locator.toString());
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return _locator.getText().length() == 0;
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean waitForNonEmptyText(WebElement _locator, int waitSeconds) {
//		    	log.info("Waiting for empty text locator=" + _locator.toString());
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return _locator.getText().length() != 0;
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

//	public boolean waitForText(By _locator, String expectedText, int waitSeconds, boolean textEquals) {
////		    	log.info("Waiting for text locator=" + _locator.toString() + " expectedText=" + expectedText);
//		try {
//			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {
//
//				@Override
//				public Boolean apply(WebDriver driver) {
//					return textEquals ? driver.findElement(_locator).getText().equals(expectedText)
//							: driver.findElement(_locator).getText().contains(expectedText);
//				}
//
//			});
//			return true;
//		} catch (TimeoutException e) {
//			return false;
//		}
//	}

	public boolean waitForText(WebElement ele, String expectedText, int waitSeconds, boolean textEquals) {
//    	log.info("Waiting for text locator=" + _locator.toString() + " expectedText=" + expectedText);
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return textEquals ? ele.getText().equals(expectedText) : ele.getText().contains(expectedText);
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Waits for the WebElement to be enabled
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is enabled within the default wait time
	 */
	public boolean waitForEnabled(By _locator) {
//		    	log.info(String.format("Waiting for WebElement with locator: [%s] to be enabled",_locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> _driver.findElement(_locator).isEnabled());
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean waitForDisabled(By _locator) {
//		    	log.info(String.format("Waiting for WebElement with locator: [%s] to be disabled", _locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> !_driver.findElement(_locator).isEnabled());
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Waits for the WebElement to be selected
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is selected within the default wait time
	 */
	public boolean waitForSelected(By _locator) {
		return waitForSelected(_locator, 15);
	}

	public boolean waitForSelected(By _locator, int timeoutSec) {
//		    	log.info(String.format("Waiting for WebElement with locator: [%s] to be selected",_locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> _driver.findElement(_locator).isSelected());
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Waits explicitly for the specified period of time
	 *
	 * @param _time Time to wait for
	 */
	public void waitForSeconds(int _time) {
		long currentTime = System.currentTimeMillis();
		int timeInSeconds = _time * 1000;
//		        log.info(String.format("Waiting for: [%d] seconds...", timeInSeconds));
		driverWait.getDriverWait().until((_driver) -> System.currentTimeMillis() > (currentTime + timeInSeconds));
	}

	/**
	 * Waits for a yet defined Function that returns a Boolean value.
	 *
	 * @param _func Functional interface
	 */
	public void waitFor(Function<WebDriver, Boolean> _func) {
		driverWait.getDriverWait().until(_func);
	}

	/**
	 * Inputs a String into a WebElement using Selenium's built-in sendKeys
	 * functionality. Clears the element prior to inputting the text.
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _input Text to send to the WebElement
	 */
	public void inputText(By _by, String _input) {
//		    	log.info(String.format("Inputting the following text: [%s] into the given WebElement: [%s]",_input, _by.toString()));

		WebElement element = getElementWhenVisible(_by);
		element.clear();
		element.sendKeys(_input);
	}

	public void inputTextByCharacter(By _by, String _input) throws InterruptedException {
//		    	log.info(String.format("Inputting the following text: [%s] into the given WebElement: [%s]",_input,_by.toString()));

		WebElement element = getElementWhenVisible(_by);
		element.clear();
		element.click();
		for (int i = 0; i < _input.length(); i++) {
			char c = _input.charAt(i);
			String s = new StringBuilder().append(c).toString();
			element.sendKeys(s);
			Thread.sleep(200);
		}
	}

	/**
	 * Appends a String into a WebElement using Selenium's built-in sendKeys
	 * functionality.
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _input Text to send to the WebElement
	 */
	public void appendText(By _by, String _input) {
//		    	log.info(String.format("Appending the following text: [%s] into the given WebElement: [%s]",_input,_by.toString()));

		WebElement element = getElementWhenVisible(_by);
		element.sendKeys(_input);
	}

	/**
	 * Clears the text of the given WebElement using Selenium's built-in clear()
	 * functionality
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void clearText(By _by) {
//		    	log.info(String.format("Clearing the text from the given WebElement: [%s]",_by.toString()));

		WebElement element = getElementWhenVisible(_by);
		element.clear();
	}

	/**
	 * Selects an option from the drop-down menu by its value
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByValue(By _by, String _value) {
//		    	log.info(String.format("Selecting the following option: [%s] from the given dropdown WebElement: [%s] by VALUE",_value, _by.toString()));

		Select select = new Select(getElementWhenVisible(_by));
		select.selectByValue(_value);
	}

	/**
	 * Selects an option from the drop-down menu by its value
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByText(By _by, String _value) {
//		    	log.info(String.format("Selecting the following option: [%s] from the given dropdown WebElement: [%s] by TEXT",_value,_by.toString()));

		Select select = new Select(getElementWhenVisible(_by));
		select.selectByVisibleText(_value);
	}

	/**
	 * Selects an option from the drop-down menu by its index
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByIndex(By _by, int _value) {
//		    	log.info(String.format("Selecting the following option at index: [%d] from the given dropdown WebElement: [%s]", _value, _by.toString()));

		Select select = new Select(getElementWhenVisible(_by));
		select.selectByIndex(_value);
	}

	/**
	 * Clicks the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 * @throws InterruptedException
	 */
	public void clickElement(By _by) throws InterruptedException {
//		    	log.info(String.format("Clicking the given WebElement: [%s]", _by.toString()));

		dismissHelper(); // added for mobile execution
		WebElement element = getElementWhenVisible(_by);
		try {

			// ScreenShot.takeScreenShot(Drivers.getInstance().getDashDriver(), "PRE
			// SCROLL");
			scrollIntoView(element);
			// ScreenShot.takeScreenShot(Drivers.getInstance().getDashDriver(), "POST SCROLL
			// PRE CLICK");
			element.click();

		} catch (ElementClickInterceptedException ex) {
//		        	log.info("Caught an ElementClickInterceptedException. Will try close info dialog and reclick", ex);
//		        	String fname = ScreenShot.takeScreenShot(driverManager.getWebDriver(), this.getClass().getName());
//					log.error("Caught exception screenShotName=" + fname);
			List<WebElement> els = driverManager.getWebDriver().findElements(By.xpath("//button[@class='con-x']"));
			if (els.size() > 0 && els.get(0).isDisplayed()) {
//		        		log.info("Clicking on possible dialog");
				els.get(0).click();
				Thread.sleep(500);
//		        		log.info("After Clicking on possible dialog");
			}
			try {
//		        		log.info("Reclicking the element");
				element.click();
//		        		log.info("After reclicking the element");
			} catch (Exception e) {
//		            	log.info("Caught an exception clicking an element, falling back to javascript click", e);
				Thread.sleep(500);
				element = getElementWhenVisible(_by);
				((JavascriptExecutor) driverManager.getWebDriver()).executeScript("arguments[0].click();", element);
//		            	log.info("After clicking the element with JS");

			}

		} catch (Exception ex) {
//		        	log.info("Caught an exception clicking an element, falling back to javascript click", ex);
			Thread.sleep(500);
			element = getElementWhenVisible(_by);
			((JavascriptExecutor) driverManager.getWebDriver()).executeScript("arguments[0].click();", element);
//		        	log.info("After clicking the element with JS2");

		}
	}

	public void clickElementJS(By _by) throws InterruptedException {
//		    	log.info(String.format("Clicking the given WebElement: [%s] with javascript",_by.toString()));

		WebElement element = getElementWhenVisible(_by);
		clickElementJS(element);
	}

	public void clickElement(By _visibleBy, By _clickBy) throws InterruptedException {
//		    	log.info(String.format("Clicking the given WebElement: [%s]",_visibleBy.toString()));

		WebElement element = getElementWhenVisible(_visibleBy);
		try {
			scrollIntoView(element);
			driverManager.getWebDriver().findElement(_clickBy).click();
		} catch (Exception ex) {
//		        	log.info("Caught exception trying to click element, will retry in 1 second incase it is transient");
			Thread.sleep(500);
			// Scroll down a bit in case another object like "Chat" is blocking the element
			((JavascriptExecutor) driverManager.getWebDriver()).executeScript("return window.scrollBy(0,100);");
			Thread.sleep(500);
			element = getElementWhenVisible(_visibleBy);
			driverManager.getWebDriver().findElement(_clickBy).click();
		}
	}

	/**
	 * Validate the text of the given WebElement against the supplied text
	 *
	 * @param _by   Locator to the WebElement to act on
	 * @param _text Text to compare
	 */
	public void validateText(By _by, String _text) throws ValidationException {
		WebElement element = getElementWhenVisible(_by);
		validateText(element, _text, true);
		/*
		 * String toCompare = element.getText(); if(!toCompare.equals(_text)) { throw
		 * new ValidationException(String.format(
		 * "Failed to validate the given text against the text of the WebElement.\n" +
		 * "--------------------------\n" + "Element:  \"%s\"\n" + "Expected: \"%s\"\n"
		 * + "Received: \"%s\"", element, _text, toCompare) ); }
		 */
	}

	/**
	 * Get the active webElement in a list of Elements based on Xpath Overload to
	 * allow removal of spaces, line breaks and tabs
	 *
	 * @param _text Text to compare
	 */
	public WebElement getActiveWebElement(By _by) throws ValidationException {
		dismissHelper();
		List<WebElement> allElements = driverManager.getWebDriver().findElements(_by);

		if (allElements.size() == 0) {
			throw new ValidationException(String.format("Failed to find any WebElements with given xpath."));
		}

		for (int i = 0; i < allElements.size(); i++) {
			if (allElements.get(i).isDisplayed()) {
				return allElements.get(i);
			}
		}

		throw new ValidationException(String.format("Failed to find active WebElement."));
	}

	public boolean compareText(By _by, String _text) {
		WebElement element = getElementWhenVisible(_by);
		String toCompare = element.getText();
		return toCompare.equals(_text);
	}

	/**
	 * Validate the value of the given WebElement against the supplied text
	 *
	 * @param _by   Locator to the WebElement to act on
	 * @param _text Text to compare
	 */
	public void validateValue(By _by, String _text) throws ValidationException {
		WebElement element = getElementWhenVisible(_by);
		String toCompare = element.getAttribute("value");
		_text = replaceStringTableElements(_text);
		validateText(toCompare, _text, true);
//		        if(!toCompare.equals(_text)) {
//		            throw new ValidationException(String.format(
//		                    "Failed to validate the given value against the value of the WebElement.\n" +
//		                            "--------------------------\n" +
//		                            "Element:  \"%s\"\n" +
//		                            "Expected: \"%s\"\n" +
//		                            "Received: \"%s\"", element, _text, toCompare)
//		            );
//		        }
	}

	public String retrieveValue(By _by) {
		WebElement el = getElementWhenVisible(_by);
		return el.getAttribute("value");
	}

	public List<String> retrieveValuesFromSelect(By _by) {
		Select select = new Select(getElementWhenVisible(_by));
		return retrieveValuesFromSelect(select);
	}

	public String retrieveValueFromSelect(By _by) {
		Select select = new Select(getElementWhenVisible(_by));
		return retrieveValueFromSelect(select);
	}

	public void validateSelectText(WebElement element, String expectedVal) throws ValidationException {
		String val = retrieveValueFromSelect(element);
		validateText(expectedVal, val);
	}

	public String retrieveValuePlaceholder(By _by) {
		WebElement el = getElementWhenVisible(_by);
		return el.getAttribute("placeholder");
	}

	public void validatePlaceholder(By _by, String placeholder) throws ValidationException {
		placeholder = searchDataDictionary(placeholder);
		validateString(retrieveValuePlaceholder(_by), placeholder);
	}

	/**
	 * Validate that the text of the given WebElement contains the supplied text
	 *
	 * @param _by   Locator to the WebElement to act on
	 * @param _text Text to find
	 */

	public void validateTextContains(By _by, String _text) throws ValidationException {
		WebElement element = getElementWhenVisible(_by);
		validateTextContains(element, _text);
	}

	/**
	 * Validate the visibility status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateVisible(By _by) throws ValidationException {
		try {
			waitForVisible(_by);
		} catch (Exception ex) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is visible.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", _by));
		}
	}

	/**
	 * Validate the invisibility status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateInvisible(By _by) throws ValidationException {
		if (!waitForInvisible(_by)) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is invisible.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", _by));
		}
	}

	public void validateEmptyText(WebElement locSuccessBanner, int waitSeconds) throws ValidationException {
		if (!waitForEmptyText(locSuccessBanner, waitSeconds)) {
			throw new ValidationException(
					"Failed validateEmptyText by=" + locSuccessBanner.toString() + " waitSeconds=" + waitSeconds);
		}
	}

	public void validateNonEmptyText(WebElement element, int waitSeconds) throws ValidationException {
		if (!waitForNonEmptyText(element, waitSeconds)) {
			throw new ValidationException(
					"Failed validateNonEmptyText by=" + element.toString() + " waitSeconds=" + waitSeconds);
		}
	}

	/**
	 * Validate the enabled status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateEnabled(By _by) throws ValidationException {
		if (!waitForEnabled(_by)) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is enabled.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", _by));
		}
	}

	/**
	 * Validate the disabled status of the given WebElement. Should be visible and
	 * disabled
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateDisabled(By _by) throws ValidationException {
		getElementWhenVisible(_by);
		boolean disabled = waitForDisabled(_by);
		if (disabled == true) {
			// Make sure it stays disabled
			getElementWhenVisible(_by);
			disabled = waitForDisabled(_by);
		}
		if (!disabled) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is disabled.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", _by));
		}
	}

	/**
	 * Validate the given attribute on the given WebElement
	 *
	 * @param _by       Locator to the WebElement to act on
	 * @param _attr     Attribute to find on the WebElement
	 * @param _expected Expected value of the element
	 */
	public void validateAttribute(By _by, String _attr, String _expected) throws ValidationException {
		WebElement element = getElementWhenVisible(_by);
		validateAttribute(element, _attr, _expected);
	}

	public String getText(By _by) {
		WebElement element = getElementWhenVisible(_by);
		String text = element.getText();
		return text;
	}

	public String getAttribute(By _by, String attributeName) {
		WebElement _element = getElementWhenVisible(_by);
		return _element.getAttribute(attributeName);
	}

	/**
	 * Verifying that tool tip is checked
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void toolTipChecked(By _by) throws Exception {
//					log.info(String.format("Attempting to validate that tooltip [%s] is checked", _by));
		WebElement we = getElementWhenVisible(_by);
		String ttVal = we.getCssValue("color").trim();
		if (Arrays.asList(ColorConstants.GREEN).contains(ttVal)) {
//			            log.info("Successfully validated tooltip as checked");
		} else {
			throw new Exception("failed to validate tool tip as checked. Original displayed color =" + ttVal);
		}
	}

	/**
	 * Verifying the password strength meter based on th passed length
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param length od the password
	 */
	public void passwordlength(By _by, String _lineLength) throws Exception {
//			        log.info(String.format("Attempting to validate tool-tip: [%s] strength meter length as: [%s]",_by ,_lineLength));
		WebElement we = getElementWhenVisible(_by);
		int lineLength = Integer.parseInt(we.getAttribute("aria-valuenow"));
		if (_lineLength.equalsIgnoreCase("Too Short") && lineLength == 20) {
//						log.info("Line is too short.");
		} else if (_lineLength.equalsIgnoreCase("Short") && lineLength == 45) {
//						log.info("Line is short.");
		} else if (_lineLength.equalsIgnoreCase("Medium") && lineLength == 60) {
//						log.info("Line is medium.");
		} else if (_lineLength.equalsIgnoreCase("Long") && lineLength == 75) {
//						log.info("Line is long.");
		} else if (_lineLength.equalsIgnoreCase("Full") && lineLength == 100) {
//						log.info("Line is full.");
		} else {
			throw new ValidationException("Line length not identified");
		}
	}

	/**
	 * Verifying that tool tip is not checked
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void toolTipNotChecked(By _by) throws Exception {
//					log.info(String.format("Attempting to validate that tooltip [%s] is NOT checked", _by));
		WebElement we = getElementWhenVisible(_by);
		String ttVal = we.getCssValue("color").trim();
		if (Arrays.asList(ColorConstants.GREY).contains(ttVal)) {
//			            log.info("Successfully validated tooltip as not checked");
		} else {
			throw new Exception("Failed to validate tool tip as checked. Original message =" + ttVal);
		}
	}

	/**
	 * Verifying tool tip colour
	 *
	 * @param _by      Locator to the WebElement to act on
	 * @param Expected Colour
	 */
	public boolean toolTipLineColor(By _by, String color) throws Exception {
//					log.info("Attempting to validate the tooltip line color");
		WebElement _element = getElementWhenVisible(_by);
		String _backgroundColor = _element.getCssValue("background-color").trim();
		switch (color.toLowerCase().trim()) {
		case "red":
			return Arrays.asList(ColorConstants.RED).contains(_backgroundColor);
		case "amber":
			return Arrays.asList(ColorConstants.ORANGE).contains(_backgroundColor);
		case "green":
			return Arrays.asList(ColorConstants.GREEN).contains(_backgroundColor);
		}
		return false;
	}

	public double calculateInterestBasedOnPrincipleAndRate(double principle, double rate) {
		return principle * (rate / 100);
	}

	public void launchFakeSinGeneratorApplication(String url) {
		driverManager.launchFakeSinGenerator(url);
	}

	public void clearElement(WebElement element) {
		// TODO Auto-generated method stub
		element.clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitForElementContainsAttributeValue(WebElement element, String attribute, String text)
			throws InterruptedException {
		int counter = 0;
		while (counter < 80) {
			try {
				String attributeText = element.getAttribute(attribute);
				System.out.println("Attribute Text : " + attributeText
						+ " is being compared with Text being passed from calling function : " + text);
				if (attributeText.contains(text)) {
					return;
				}
			} catch (NoSuchElementException e) {
				// TODO: handle exception
			}
			counter++;
			System.out.println("Waiting for 500 milliseconds on counter : " + counter);
			Thread.sleep(500);
		}
	}

	public String pageTitle() {
		String actualTitle = driverManager.getWebDriver().getTitle();
		driverWait.getDriverWait().until(ExpectedConditions.titleIs(actualTitle));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//	      System.out.println("\n" + actualTitle + "\n" + expectedTitle + "\n");
		return actualTitle;
	}

	public void clickDropDownFilterAndSelectValue(WebElement parentElement, String dropdownValue) throws Exception {
		System.out.println(parentElement + " " + dropdownValue);
		try {
			Thread.sleep(2000);
//	          scrollIntoView(parentElement);
//	          explicitWaitForMilliSeconds(1000);
			clickElement(parentElement);
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage() + dropdownValue + " cannot be selected");
		}
		int initialCount = 1;
		while (initialCount <= 20) {
			try {
				driverManager.getWebDriver().findElement(By.xpath("//span[text()='" + dropdownValue + "']")).click();
				break;
			} catch (NoSuchElementException | ElementNotInteractableException e) {
				// TODO: handle exception
				System.out.println(
						"Element Not Found. Waiting for 500 milliseconds on iteration count : " + initialCount);
				Thread.sleep(500);
				initialCount++;
			}
		}
		Thread.sleep(1000);

		/*
		 * WebElement infoMessage =
		 * parentElement.findElement(By.xpath("../../../span[2]"));
		 * waitForNonEmptyText(infoMessage);
		 */

//		WebElement infoMessage = parentElement.findElement(By.xpath("../../../span[2]"));
//		waitForNonEmptyText(infoMessage);
	}

	public String yearIncrement(String initialDate, int yearsToAdd) {
		// TODO Auto-generated method stub
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("JAN", "Jan");
		hashMap.put("FEB", "Feb");
		hashMap.put("MAR", "Mar");
		hashMap.put("APR", "Apr");
		hashMap.put("MAY", "May");
		hashMap.put("JUN", "Jun");
		hashMap.put("JUL", "Jul");
		hashMap.put("AUG", "Aug");
		hashMap.put("SEP", "Sep");
		hashMap.put("OCT", "Oct");
		hashMap.put("NOV", "Nov");
		hashMap.put("DEC", "Dec");
		String[] dateArray = initialDate.split("-")[0].split(" ");
		int year = Integer.parseInt(dateArray[2]) + yearsToAdd;
		String convertedDate = "";
		if (initialDate.contains("CamelCase"))
			convertedDate = dateArray[0] + " " + hashMap.get(dateArray[1]) + " " + year;
		else
			convertedDate = dateArray[0] + " " + dateArray[1] + " " + year;
		System.out.println("Converted Date in year converter : " + convertedDate);
		return convertedDate;
	}

	public String monthIncrement(String initialDate, int monthToAdd) {
		// String initialDate = "28 AUG 2022";
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("JAN", "01");
		hashMap.put("FEB", "02");
		hashMap.put("MAR", "03");
		hashMap.put("APR", "04");
		hashMap.put("MAY", "05");
		hashMap.put("JUN", "06");
		hashMap.put("JUL", "07");
		hashMap.put("AUG", "08");
		hashMap.put("SEP", "09");
		hashMap.put("OCT", "10");
		hashMap.put("NOV", "11");
		hashMap.put("DEC", "12");

		String[] dateArray = initialDate.split(" ");
		String newDateString = dateArray[2] + "-" + hashMap.get(dateArray[1]) + "-" + dateArray[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(newDateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		cal.add(Calendar.MONTH, monthToAdd);
		String[] calculatedDate = sdf.format(cal.getTime()).split("-");
		String month = "";
		for (Entry<String, String> entry : hashMap.entrySet()) {
			if (entry.getValue().equals(calculatedDate[1])) {
				month = entry.getKey();
				break;
			}
		}
		String convertedDate = calculatedDate[2] + " " + month + " " + calculatedDate[0];
		System.out.println("Converted Date : " + convertedDate);
		return convertedDate;
	}

	public String dateIncrement(String initialDate, int daysToAdd) {
		// TODO Auto-generated method stub
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("JAN", "01");
		hashMap.put("FEB", "02");
		hashMap.put("MAR", "03");
		hashMap.put("APR", "04");
		hashMap.put("MAY", "05");
		hashMap.put("JUN", "06");
		hashMap.put("JUL", "07");
		hashMap.put("AUG", "08");
		hashMap.put("SEP", "09");
		hashMap.put("OCT", "10");
		hashMap.put("NOV", "11");
		hashMap.put("DEC", "12");
		// String initialDate = "28 AUG 2022";
		String[] dateArray = initialDate.split(" ");
		String newDateString = dateArray[2] + "-" + hashMap.get(dateArray[1]) + "-" + dateArray[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(newDateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_MONTH, daysToAdd);
		String[] calculatedDate = sdf.format(cal.getTime()).split("-");
		String month = "";
		for (Entry<String, String> entry : hashMap.entrySet()) {
			if (entry.getValue().equals(calculatedDate[1])) {
				month = entry.getKey();
				break;
			}
		}
		String convertedDate = calculatedDate[2] + " " + month + " " + calculatedDate[0];
		System.out.println("Converted Date in date converter : " + convertedDate);
		return convertedDate;
	}

	public int daysDifference(String initialDate, String newDate) {
		int noOfDays = 0;
		Date initialDateObj = null;
		Date newDateObj = null;

		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("JAN", "01");
		hashMap.put("FEB", "02");
		hashMap.put("MAR", "03");
		hashMap.put("APR", "04");
		hashMap.put("MAY", "05");
		hashMap.put("JUN", "06");
		hashMap.put("JUL", "07");
		hashMap.put("AUG", "08");
		hashMap.put("SEP", "09");
		hashMap.put("OCT", "10");
		hashMap.put("NOV", "11");
		hashMap.put("DEC", "12");

		// String initialDate = "28 AUG 2022";
		String[] dateArray = initialDate.split(" ");
		String initialDateString = dateArray[2] + "-" + hashMap.get(dateArray[1]) + "-" + dateArray[0];

		dateArray = newDate.split(" ");
		String newDateString = dateArray[2] + "-" + hashMap.get(dateArray[1]) + "-" + dateArray[0];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			initialDateObj = sdf.parse(initialDateString);
			newDateObj = sdf.parse(newDateString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long difference = TimeUnit.DAYS.convert(Math.abs(newDateObj.getTime() - initialDateObj.getTime()),
				TimeUnit.MILLISECONDS);
		noOfDays = (int) difference;
		System.out.println("No of Days : " + noOfDays);
		return noOfDays;

	}

	public double findDifferenceBetween2Doubles(double double1, double double2) {
		if (double1 > double2)
			return double1 - double2;
		else if (double1 < double2)
			return double2 - double1;
		else
			return 0.00;
	}

	public boolean calculateDifferenceInterest(double calcutedInterest, String interest) {
		boolean flag = false;
		calcutedInterest = Double.parseDouble(interest) - calcutedInterest;
		System.out.println("calcutedInterest - interest " + calcutedInterest);
		if (calcutedInterest < 0.5)
			flag = true;
		return flag;
	}

	public String generateRandomDouble(String value) {
		double int_value = Math.floor(Double.parseDouble(value));
		return generateRandomDouble(int_value, int_value + 1);
	}

	public String generateRandomDouble(double double1, double double2) {
		double randomDouble = ThreadLocalRandom.current().nextDouble(double1, double2);
		DecimalFormat df = new DecimalFormat("0.00");
		String turncatedDouble = df.format(randomDouble);
		System.out.println("Truncated Double : " + turncatedDouble);
		return turncatedDouble;
	}

	public boolean dynamicElementwithTextPresent(String text) {
		try {
			driverManager.getWebDriver().findElement(By.xpath("//*[text()='" + text + "']")).isDisplayed();
			return false;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			e.printStackTrace();
			return true;
		}
	}

	public boolean dynamicElementwithPartialTextPresent(String text) {
		try {
			return driverManager.getWebDriver().findElement(By.xpath("//*[contains(text(),'" + text + "')]"))
					.isDisplayed();
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public String fetchOutstandingPaymentScheduleDynamically(int year) {
		String outstandingValueFetched = driverManager.getWebDriver()
				.findElement(By.xpath("(//table[@isprimped='Y'])[last()]/tbody/tr[" + (year + 1) + "]/td[10]/div/span"))
				.getText();
		System.out.println("Outstanding Value under Payment Schedule fetched from UI : " + outstandingValueFetched);
		return outstandingValueFetched;
	}

	public void validateFieldTextChanged(WebElement element, String text) {
		int counter = 0;
		while (counter < 240) {
			try {
				String uiText = getText(element);
				System.out.println("UI Text : " + uiText
						+ " is being compared with Text being passed from feature file : " + text);
				if (!uiText.equalsIgnoreCase(text)) {
					return;
				}
			} catch (NoSuchElementException | StaleElementReferenceException e) {
				// TODO: handle exception
			}
			counter++;
			System.out.println("Waiting for 500 milliseconds on counter : " + counter);
			explicitWaitForMilliSeconds(500);
		}
	}

	public void explicitWaitForMilliSeconds(int milli) {
		try {
			Thread.sleep(milli);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/******** Newly Added **********/

	public void switchToParentFrame() throws Exception {
		try {
			driverManager.getWebDriver().switchTo().parentFrame();
		} catch (Exception e) {
//			log.info("Parent frame NOT found");
			e.printStackTrace();
//			log.log(Level.ERROR, ex.getMessage(), ex);
			throw new Exception(e);
		}
	}

	public void switchByFrameId(int frameId) throws Exception {
		try {
			driverManager.getWebDriver().switchTo().frame(frameId);
//			log.info("Switch to frame id: " + frameId);
		} catch (Exception e) {
			e.printStackTrace();
//			log.log(Level.ERROR, e.getMessage(), e);
			throw new Exception(e);
		}
	}

	public void switchParentWindow() throws Exception {
		try {
			Set<String> handles = driverManager.getWebDriver().getWindowHandles();
			String firstWinHandle = driverManager.getWebDriver().getWindowHandle();

			String winHandle = handles.iterator().next();

			driverManager.getWebDriver().switchTo().window(firstWinHandle);
		} catch (Exception ex) {
			ex.printStackTrace();
//			log.log(Level.ERROR, ex.getMessage(), ex);
			throw new Exception(ex);
		}
	}

	public void switchChildWindow() throws Exception {
		try {
			Set<String> handles = driverManager.getWebDriver().getWindowHandles();
			String firstWinHandle = driverManager.getWebDriver().getWindowHandle();
			handles.remove(firstWinHandle);

			String winHandle = handles.iterator().next();

			if (winHandle != firstWinHandle) {

				// To retrieve the handle of second window, extracting the handle which does not
				// match to first window handle

				String secondWinHandle = winHandle; // Storing handle of second window handle

				// SwitchswitchWindow control to new window

				driverManager.getWebDriver().switchTo().window(secondWinHandle);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex);
		}
	}

	public void pageUp() throws Exception {
		try {
			/// Scroll up the page
			Actions actions = new Actions(driverManager.getWebDriver());
			actions.sendKeys(Keys.PAGE_UP).build().perform();
//			Robot rb = new Robot();
//			rb.keyPress(KeyEvent.VK_PAGE_UP);
//			rb.keyPress(KeyEvent.VK_PAGE_UP);
//			rb.keyRelease(KeyEvent.VK_PAGE_UP);
		} catch (Exception ex) {
			ex.printStackTrace();
//			log.log(Level.ERROR, ex.getMessage(), ex);
			throw new Exception(ex);
		}
	}

	public void pageDown() throws Exception {
		/// Scroll down the page
		try {
			Actions actions = new Actions(driverManager.getWebDriver());
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
//			Robot rb = new Robot();
//			rb.keyPress(KeyEvent.VK_PAGE_DOWN);
//			rb.keyPress(KeyEvent.VK_PAGE_DOWN);
//			rb.keyRelease(KeyEvent.VK_PAGE_DOWN);
		} catch (Exception ex) {
			ex.printStackTrace();
//			log.log(Level.ERROR, ex.getMessage(), ex);
			throw new Exception(ex);
		}
	}

	public String getCurrentDate() {
		Date date = new Date();
		String sDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
		return sDate;
	}

	public String convertStringDate(Date date) {
		String sDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return sDate;
	}

	public String addDays(int extraDate, String format) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat(format);
		String sDate = dateFormat.format(date);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, extraDate);
		Date todate1 = cal.getTime();
		String actualdate = dateFormat.format(todate1);
		return actualdate;
	}

	public String addHoursMints(int hoursToBeAdded, int minutesToBeAdded) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm:a");

		// Substract 2 hour from the current time
		cal.add(Calendar.HOUR, hoursToBeAdded);

		// Add 30 minutes to the calendar time
		cal.add(Calendar.MINUTE, minutesToBeAdded);

		return sdf.format(cal.getTime());
	}

	public void selectCheckBox(WebElement field, String input_YesOrNo) throws Exception {
		WebElement e = null;
//		log.info("Field Name:"+ fieldName +"       Object Key"+objectKey+"input Yes/No: "+input_YesOrNo);
		try {
			if ((input_YesOrNo != "") || (!input_YesOrNo.isEmpty())) {
				if (input_YesOrNo.equalsIgnoreCase("YES")) {
					if (isElementDisplayed(field)) {
						field.click();
//						log.info("Checkbox value is: " + input_YesOrNo);
						// Thread.sleep(2000);
					}
				}
			}
		} catch (Exception s) {
			s.printStackTrace();
			throw new Exception(s);
		}
	}

	public String dateConverter(String dateText, String todaysDate) {
		String fieldValue = todaysDate;
		String currentDate = dateText;
		System.out.println("Date Text : " + fieldValue + " || Todays Date : " + currentDate);
		if (fieldValue.equals("TodaysDate")) {
			fieldValue = yearIncrement(currentDate + "-CamelCase", 0);
			System.out.println("fieldValue " + fieldValue);
		} else if (fieldValue.contains("TodaysDate - ")) {
			if (fieldValue.split(" - ")[1].contains("Days")) {
				int daysValue = Integer.parseInt(fieldValue.split(" - ")[1].replaceAll(" Days", ""));
				System.out.println(daysValue);
				fieldValue = yearIncrement(dateIncrement(currentDate, -daysValue) + "-CamelCase", 0);
				System.out.println("fieldValue " + fieldValue);
			} else if (fieldValue.split(" - ")[1].contains("Month")) {
				int monthValue = Integer.parseInt(fieldValue.split(" - ")[1].split(" ")[0]);
				System.out.println(monthValue);
				fieldValue = yearIncrement(monthIncrement(currentDate, -monthValue) + "-CamelCase", 0);
				System.out.println("fieldValue " + fieldValue);
			} else if (fieldValue.split(" - ")[1].contains("Year")) {
				int yearValue = Integer.parseInt(fieldValue.split(" - ")[1].split(" ")[0]);
				System.out.println(yearValue);
				fieldValue = yearIncrement(currentDate + "-CamelCase", -yearValue);
				System.out.println("fieldValue " + fieldValue);
			}
		} else if (fieldValue.contains("TodaysDate + ")) {
			if (fieldValue.split(" \\+ ")[1].contains("Days")) {
				int daysValue = Integer.parseInt(fieldValue.split(" \\+ ")[1].replaceAll(" Days", ""));
				System.out.println(daysValue);
				fieldValue = yearIncrement(dateIncrement(currentDate, daysValue) + "-CamelCase", 0);
				System.out.println("fieldValue " + fieldValue);
			} else if (fieldValue.split(" \\+ ")[1].contains("Month")) {
				int monthValue = Integer.parseInt(fieldValue.split(" \\+ ")[1].split(" ")[0]);
				System.out.println(monthValue);
				fieldValue = yearIncrement(monthIncrement(currentDate, monthValue) + "-CamelCase", 0);
				System.out.println("fieldValue " + fieldValue);
			} else if (fieldValue.split(" \\+ ")[1].contains("Year")) {
				int yearValue = Integer.parseInt(fieldValue.split(" \\+ ")[1].split(" ")[0]);
				System.out.println(yearValue);
				fieldValue = yearIncrement(currentDate + "-CamelCase", yearValue);
				System.out.println("fieldValue " + fieldValue);
			}
		}
		return fieldValue;
	}

	public String getInputTextFromJson(String input, Language lang) {
		logger.info(String.format("Scrubbing input text: [%s]...", input));
		String value = "";
		try {
			if (input == null) {
				return value;
			} else if (input.equals("BLANK")) {
				return value;
			} else if (input.equalsIgnoreCase("<phone_blank>")) {
				return "(___) ___-____";
			} else if (input.equalsIgnoreCase("<space>")) {
				value = " ";
			} else if (input.contains("<rtd:")) {
				// Regex match here
				Pattern pattern = Pattern.compile("<rtd:.*?>");
				Matcher matcher = pattern.matcher(input);
				StringBuffer buffer = new StringBuffer();
				int _counter = 0;
				while (matcher.find()) {
					// matcher.appendReplacement(buffer,
					// RunTimeData.getValue(matcher.group(_counter)).replaceAll("\\$", "\\\\\\$"));
					_counter++;
				}
				matcher.appendTail(buffer);
				value = buffer.toString();
			} else if (input.startsWith("dd:normalized:")) {
				input = input.replace("normalized:", "");
				value = searchJsonDataDictionary(input, lang);
			} else if (input.startsWith("dd:")) {
				value = searchJsonDataDictionaryDontStripHTML(input, lang);
			} else {
				value = input.trim().replace("<SPACE>", " ").replace("<space>", " ").replace("<blank>", "")
						.replace("<BLANK>", "").replace("<HASH>", "#").replace("<hash>", "#").replace("<#>", "#")
						.replace("<VERTICAL>", "|").replace("<vertical>", "|");
			}
		} catch (Exception e) {
			logger.error("An error occurred while processing input text", e);
		}
		logger.info("Returning value: " + value);
		return value;
	}

	public String getInputTextFromJson(String input) {
		logger.info(String.format("Scrubbing input text: [%s]...", input));
		String value = "";
		try {
			if (input == null) {
				return value;
			} else if (input.equals("BLANK")) {
				return value;
			} else if (input.equalsIgnoreCase("<phone_blank>")) {
				return "(___) ___-____";
			} else if (input.equalsIgnoreCase("<space>")) {
				value = " ";
			} else if (input.contains("<rtd:")) {
				// Regex match here
				Pattern pattern = Pattern.compile("<rtd:.*?>");
				Matcher matcher = pattern.matcher(input);
				StringBuffer buffer = new StringBuffer();
				int _counter = 0;
				while (matcher.find()) {
					// matcher.appendReplacement(buffer,
					// RunTimeData.getValue(matcher.group(_counter)).replaceAll("\\$", "\\\\\\$"));
					_counter++;
				}
				matcher.appendTail(buffer);
				value = buffer.toString();
			} else if (input.startsWith("dd:normalized:")) {
				input = input.replace("normalized:", "");
				value = searchJsonDataDictionary(input);
			} else if (input.startsWith("dd:")) {
				value = searchJsonDataDictionaryDontStripHTML(input);
			} else {
				value = input.trim().replace("<SPACE>", " ").replace("<space>", " ").replace("<blank>", "")
						.replace("<BLANK>", "").replace("<HASH>", "#").replace("<hash>", "#").replace("<#>", "#")
						.replace("<VERTICAL>", "|").replace("<vertical>", "|");
			}
		} catch (Exception e) {
			logger.error("An error occurred while processing input text", e);
		}
		logger.info("Returning value: " + value);
		return value;
	}

	protected String searchJsonDataDictionary(String _input) {
		logger.info(String.format("Searching for an entry in the data dictionary for input: %s", _input));
		if (_input.startsWith("dd:")) {
			logger.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			String lang = TestLanguage.getLanguage().toString();
			return DataDictionary.getMessageFromJsonDataDictionary(lang, parts[1]);
		}
		return _input;
	}

	protected String searchJsonDataDictionaryDontStripHTML(String _input) {
		logger.info(
				String.format("Searching for an entry in the data dictionary dont strip html for input: %s", _input));
		if (_input.startsWith("dd:")) {
			logger.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			String lang = TestLanguage.getLanguage().toString();
			return DataDictionary.getMessageFromJsonDataDictionaryDontStripHTML(lang, parts[1]);
		}
		return _input;
	}

	public void clearTextByBackspace(WebElement ele) {
		logger.info(String.format("Clearing the text from the given WebElement: [%s]", ele.toString()));
		WebElement element = getElementWhenVisible(ele);
		while (!retrieveValue(element).equals("")) {
			element.sendKeys(Keys.BACK_SPACE);
		}

	}

	protected String searchJsonDataDictionary(String _input, Language lang) {
		logger.info(String.format("Searching for an entry in the data dictionary for input: %s", _input));
		if (_input.startsWith("dd:")) {
			logger.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			return DataDictionary.getMessageFromJsonDataDictionary(lang.toString(), parts[1]);
		}
		return _input;
	}

	protected String searchJsonDataDictionaryDontStripHTML(String _input, Language lang) {
		logger.info(
				String.format("Searching for an entry in the data dictionary dont strip html for input: %s", _input));
		if (_input.startsWith("dd:")) {
			logger.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			return DataDictionary.getMessageFromJsonDataDictionaryDontStripHTML(lang.toString(), parts[1]);
		}
		return _input;
	}

	public String retrieveValueWithXpath(WebElement ele) {
		String toCompare = ((JavascriptExecutor) driverManager.getWebDriver())
				.executeScript("return arguments[0].value", ele).toString();
		System.out.println("retrieveValue js method test: " + toCompare);
		return toCompare;
//    	return el.getAttribute("value");
	}

	public String convertWebelementToString(WebElement element) {
		String[] a = element.toString().split("xpath:");
		System.out.println("length :" + a.length);
		String xpath = a[a.length - 1].trim();
		String modifiedXpath = xpath.substring(0, xpath.length() - 1);
		System.out.println("Modified XPath: " + modifiedXpath);
		return modifiedXpath;
	}

	public boolean isTextisBold(WebElement ele) {
		boolean status = false;
		WebElement element = ele.findElement(By.tagName("h2"));
		String Value = element.getCssValue("font-weight");
		System.out.println("font-weight :" + Value);
		// "700" is equivalent to "bold" in CSS
		if (Value.equalsIgnoreCase("bold")) {
			System.out.println("Text is displayed in bold.");
			status = true;
		} else if (Value.contains("0")) {
			int parseInt = Integer.parseInt(Value);
			if (parseInt >= 700) {
				System.out.println("Text is displayed in bold.");
				status = true;
			}
		} else {
			System.out.println("Text is not displayed in bold.");
		}

		return status;

	}

	public void validateLinkisOutlined(WebElement element) throws ValidationException {
		String outlineColor = element.getCssValue("outline-color");
		System.out.println("outlineColor :" + outlineColor);
		validateColor(ColorConstants.BLACK, outlineColor);
	}
}
