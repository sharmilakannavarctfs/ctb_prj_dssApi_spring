package com.ctfs.mobile.utils;

import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.GsmSignalStrength;
import io.appium.java_client.android.GsmVoiceState;
import io.appium.java_client.android.NetworkSpeed;
import io.appium.java_client.android.PowerACState;
import io.appium.java_client.android.SupportsSpecialEmulatorCommands;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.HasNetworkConnection;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.internal.HasIdentity;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.testng.Assert;


import com.ctfs.common.utils.ApplicationProperties;
import com.ctfs.common.utils.DateTimeUtil;
import com.ctfs.framework.ColorConstants;
import com.ctfs.framework.Constants.Language;
import com.google.common.collect.ImmutableMap;
import com.ctfs.framework.DataDictionary;
import com.ctfs.framework.IFormatFilter;
import com.ctfs.framework.TestLanguage;
import com.ctfs.framework.ValidationException;

import com.ctfs.common.utils.ApplicationProperties;

@Component
@SuppressWarnings("unused")
public class DriverHelper {

	private final static Logger log = LoggerFactory.getLogger(DriverManager.class);

	@Autowired
	private final DriverManager driverManager;

	private MobileElement element;
	private final DriverWait driverWait;

	@Autowired
	public DriverHelper(DriverManager driverManager, DriverWait driverWait) {
		this.driverManager = driverManager;
		this.driverWait = driverWait;
	}

	@Autowired
	private ApplicationProperties applicationProperties;

	@Autowired
	private Environment environment;
	
	

//    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = {RetryException.class})
//    public void clickOn (By locator) {
//    	if(!driverManager.isMobile())
//    	{
//    		System.out.println("WebDriver Instance"+ driverManager.getWiciDriver( ));
//    	} else {
//        if ( isElementDisplayed ( locator ) ) {
//            driverManager.getWiciDriver().findElement(locator).click ( );
//        }}
//    }

//    public void createChromeDriver () throws Exception {
//       // if ( driverManager. ) {
//            driverManager.createWiciWebDriver("chrome", false);
//     //   }
//    }

	public boolean isMobile() {
		return driverManager.isMobile();

	}

	public void launchApp() throws Exception {
		// if ( driverManager. ) {
		driverManager.getWebDriver().get("https://www.google.com/");
		// }
	}

	public void launchSaucelabApp() throws Exception {
		// if ( driverManager. ) {
		driverManager.getWebDriver().get("https://www.saucedemo.com/");
		// }

	}

	public void lounchWiciWebApp() throws Exception {
		if (applicationProperties.getAppMode() != null && applicationProperties.getAppMode().contains("Web")) {

			/// checking active profile type and redirecting application grid for Jenkins
			/// and WebURL for Prod
			if (Arrays.toString(this.environment.getActiveProfiles()).contains("jenkins")) {

				log.info("Remote Grid URL : " + "applicationProperties.getAppiumGridUrl ( )");
				driverManager.getWiciDriver().get(applicationProperties.getAppiumGridUrl());

			} else if ((Arrays.toString(this.environment.getActiveProfiles()).contains("prod"))) {

				log.info("Web URL : " + "applicationProperties.getWebUrl ( )");
				driverManager.getWiciDriver().get(applicationProperties.getWebUrl());
				Alert alert = driverManager.getWiciDriver().switchTo().alert();
				alert.dismiss();
				Thread.sleep(1000);
				alert.dismiss();
				Thread.sleep(1000);
				alert.dismiss();
				Thread.sleep(1000);
				alert.dismiss();
				Thread.sleep(1000);		
			} else {
				log.info("Mentioned Application Property value is wrong in Active profile in POM.xml");
			}
		}
	}

	/**
	 * Send Keys to the specified element, clears the element first
	 */
//    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = {RetryException.class})
//    public void sendKeys (By locator , String value) {
//        try { 
//        	if(!driverManager.isMobile())
//        	{
//        		System.out.println("WebDriver Instance sendKeys"+ driverManager.getWiciDriver( ));
//        	} else {
//            Thread.sleep ( 5000 );
//            if ( value != null ) {
//                element = (MobileElement) driverManager.getWiciDriver( ).findElement ( locator );
//                if ( value.length ( ) > 0 ) {
//                    element.clear ( );
//                    element.sendKeys ( value );
//                } else {
//                    element.clear ( );
//                }
//            }
//        }} catch (Exception e) {
//            System.out.println ( e.getLocalizedMessage ( ) );
//        }
//    }

//    public boolean isElementDisplayed(By element) {
//    	if(!driverManager.isMobile())
//    	{
//    		System.out.println("WebDriver Instance isElementDisplayed By");
//    	} else {
//        return driverWait.getWebDriverWait ( ).until ( ExpectedConditions.visibilityOfElementLocated(By.id(locUpdateSuccessAlert)) ).isDisplayed ( );
//    	}
//		return false;
//    }
//
//    /**
//     * Checks if the specified element is displayed
//     */
//    public boolean isElementDisplayed(WebElement element) {
//    	 boolean present = false;
//    	if(!driverManager.isMobile())
//    	{
//    		System.out.println("WebDriver Instance isElementDisplayed WebElement");
//    	} else {
//       
//        try {
//            present = element.isDisplayed ( );
//        } catch (Exception ignored) {
//        }
//    	}
//        return present;
//    }

//    public void performDigitalSignature(MobileElement element) throws InterruptedException {
//    	 Actions builder = new Actions(driverManager.getWiciDriver());
//    	 scrollIntoView(element);
//    	 Action signature= builder.moveToElement(element,100,50)
//    	    		//start points x axis and y axis.
//    	            .clickAndHold()
//    	              .moveByOffset(150, 50) // 2nd point (x1,y1)
//    	              .click()
//    	              .build();
//    	    signature.perform();
//   }
//    
//    public void performDigitalSignature(WebElement element) throws InterruptedException {
//    	Actions builder = new Actions(driverManager.getWiciDriver());
//    	scrollIntoView(element);
//	    Action signature= builder.moveToElement(element,100,50)
//	    		//start points x axis and y axis.
//	            .clickAndHold()
//	              .moveByOffset(150, 50) // 2nd point (x1,y1)
//	              .click()
//	              .build();
//	    signature.perform();
//    }

	/**
	 * 
	 * 
	 * 
	 * ****************************** Web Component methods starting
	 * *********************************
	 */

	/**
	 * Waits for the element at the given XPATH to be visible, and then returns a
	 * WebElement instance of the element.
	 *
	 * @param _by locator for element to locate and check for visibility
	 * @return WebElement if found
	 */
	@Retryable(maxAttempts = 3, backoff = @Backoff(delay = 500), include = { RetryException.class })
	protected WebElement getElementWhenVisible(By _by) {
		log.info(String.format("Attempting to find and return the WebElement with the following By value: [%s]",
				_by.toString()));
		return driverWait.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(_by));
	}

	protected boolean isElementVisible(By _by) {
		try {
			return getElementWhenVisible(_by).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isElementVisible(WebElement element) {
		try {
			System.out.println("WICI Driver" + driverManager.getWiciDriver());
			return element.isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Waits for the element(s) at the given XPATH to be visible, and then returns a
	 * list of visible WebElement elements.
	 *
	 * @param _by locator for element to locate and check for visibility
	 * @return WebElement list if found
	 * @throws Exception
	 */
	protected List<WebElement> getAllVisibleElements(By _by) throws Exception {
		return getAllVisibleElements(_by, -1);
	}

	protected List<WebElement> getAllVisibleElements(By _by, int minVisibleCount) throws Exception {
		log.info(String.format(
				"Attempting to find and return all visible WebElement(s) with the following By value: [%s]",
				_by.toString()));
		long cutoff = new Date().getTime() + 10000;
		List<WebElement> elems = new ArrayList<WebElement>();
		int eCount = 0;
		do {
			eCount = 0;
			List<WebElement> els = driverManager.getWiciDriver().findElements(_by);
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
	protected List<WebElement> getElementsWhenVisible(By _by) {
		log.info(String.format("Attempting to find and return the WebElement(s) with the following By value: [%s]",
				_by.toString()));
		return driverWait.getWebDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(_by));
	}

	/**
	 * Waits for an element to be visible. May be more than one element that matches
	 * xpath, will return first visible element.
	 *
	 * @param _by locator for element to locate and check for visibility
	 * @return WebElement if found
	 * @throws InterruptedException
	 */
	protected WebElement getAnyElementWhenVisible(By _by) throws InterruptedException {
		log.info(String.format("Attempting to find and return the WebElement with the following By value: [%s]",
				_by.toString()));
		long cutoff = new Date().getTime() + 10000;
		WebElement el = null;
		int eCount = 0;
		do {
			eCount = 0;
			try {
				List<WebElement> els = driverManager.getWiciDriver().findElements(_by);
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
	protected void waitForVisible(By _locator) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be visible", _locator.toString()));
		// Remove the timeout catch
		// Calling routine are not checking the return value and errors result since the
		// element is not
		// visible
//        try {
//            return getWait(waitSeconds).until((_driver) -> _driver.findElement(_locator).isDisplayed());
//        } catch (TimeoutException e) {
//            return false;
//        }
		driverWait.getWebDriverWait().until((_driver) -> _driver.findElement(_locator).isDisplayed());
	}

	/**
	 * Waits for the WebElement to be visible
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is visible within the default wait time
	 */
	protected void waitForVisible(WebElement element) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be visible"));
		// Remove the timeout catch
		// Calling routine are not checking the return value and errors result since the
		// element is not
		// visible
//        try {
//            return getWait(waitSeconds).until((_driver) -> _driver.findElement(_locator).isDisplayed());
//        } catch (TimeoutException e) {
//            return false;
//        }
		driverWait.getWebDriverWait().until((_driver) -> element.isDisplayed());
	}

	protected boolean waitForVisible_NoException(By _locator) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be visible", _locator.toString()));
		try {
			return driverWait.getWebDriverWait().until((_driver) -> _driver.findElement(_locator).isDisplayed());
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected boolean waitForVisible_NoException(WebElement element) {
		log.info(String.format("Waiting for Element to be visible"));
		try {
			return driverWait.getWebDriverWait().until((_driver) -> element.isDisplayed());
		} catch (TimeoutException e) {
			return false;
		}
	}

//    protected boolean waitForVisible(By _locator, int waitSeconds) {
//    	log.info(String.format("Waiting for WebElement with locator: [%s] to be visible",
//                _locator.toString()));
//        try {
//            return getWait(waitSeconds).until((_driver) -> _isDisplayed(_driver, _locator));
//        } catch (TimeoutException e) {
//            return false;
//        }
//    }
//    protected boolean _isDisplayed(WebDriver d, By _locator) {
//    	boolean b = d.findElement(_locator).isDisplayed();
//    	System.out.println("_isDisplayed=" + b);
//    	return b;
//    }

	/**
	 * Waits for the WebElement to be invisible
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is visible within the default wait time
	 */
	protected boolean waitForInvisible(By _locator) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be NOT visible", _locator.toString()));
		try {
			// Use invisiblilityOfElementLocated instead of isDisplayed because isDisplayed
			// does not handle the case of the element no longer existing
			driverWait.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(_locator));
			return true;
			// return getWait().until((_driver) ->
			// !_driver.findElement(_locator).isDisplayed());
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
	protected boolean waitForInvisible(WebElement element) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be NOT visible"));
		try {
			// Use invisiblilityOfElementLocated instead of isDisplayed because isDisplayed
			// does not handle the case of the element no longer existing
			driverWait.getWebDriverWait().until(ExpectedConditions.invisibilityOf(element));
			return true;
			// return getWait().until((_driver) ->
			// !_driver.findElement(_locator).isDisplayed());
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected boolean waitForEmptyText(By _locator) {
		log.info("Waiting for empty text locator=" + _locator.toString());
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return driver.findElement(_locator).getText().length() == 0;
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected boolean waitForEmptyText(WebElement element) {
		log.info("Waiting for empty text locator=");
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

	protected boolean waitForNonEmptyText(By _locator) {
		log.info("Waiting for empty text locator=" + _locator.toString());
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return driver.findElement(_locator).getText().length() != 0;
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected boolean waitForNonEmptyText(WebElement element) {
		log.info("Waiting for empty text locator=");
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

	protected boolean waitForText(By _locator, String expectedText, int waitSeconds, boolean textEquals) {
		log.info("Waiting for text locator=" + _locator.toString() + " expectedText=" + expectedText);
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return textEquals ? driver.findElement(_locator).getText().equals(expectedText)
							: driver.findElement(_locator).getText().contains(expectedText);
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected boolean waitForText(WebElement element, String expectedText, int waitSeconds, boolean textEquals) {
		log.info("Waiting for text locator= expectedText=" + expectedText);
		try {
			driverWait.getDriverWait().until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return textEquals ? element.getText().equals(expectedText)
							: ((RemoteWebElement) element).getText().contains(expectedText);
				}

			});
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected boolean waitForText(By _locator, String expectedText, int waitSeconds) {
		return waitForText(_locator, expectedText, waitSeconds, true);
	}

	/**
	 * Waits for the WebElement to be enabled
	 *
	 * @param _locator Locator to wait for
	 * @return True if the given WebElement is enabled within the default wait time
	 */
	protected boolean waitForEnabled(By _locator) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be enabled", _locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> _driver.findElement(_locator).isEnabled());
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
	protected boolean waitForEnabled(WebElement element) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be enabled"));

		try {
			return driverWait.getDriverWait().until((_driver) -> element.isEnabled());
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected boolean waitForDisabled(By _locator) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be disabled", _locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> !_driver.findElement(_locator).isEnabled());
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected boolean waitForDisabled(WebElement element) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be disabled"));

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
	protected boolean waitForSelected(By _locator) {
		return waitForSelected(_locator, 15);
	}

	protected boolean waitForSelected(By _locator, int timeoutSec) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be selected", _locator.toString()));

		try {
			return driverWait.getDriverWait().until((_driver) -> _driver.findElement(_locator).isSelected());
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected boolean waitForSelected(WebElement element) {
		log.info(String.format("Waiting for WebElement with locator: [%s] to be selected"));

		try {
			return driverWait.getDriverWait().until((_driver) -> element.isSelected());
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Waits explicitly for the specified period of time
	 *
	 * @param _time Time to wait for
	 */
	protected void waitForSeconds(int _time) {
		long currentTime = System.currentTimeMillis();
		int timeInSeconds = _time * 1000;
		log.info(String.format("Waiting for: [%d] seconds...", timeInSeconds));
		driverWait.getDriverWait().until((_driver) -> System.currentTimeMillis() > (currentTime + timeInSeconds));
	}
//    /**
//     * Waits for a yet defined Function that returns a Boolean value.
//     *
//     * @param _func Functional interface
//     */
//    protected void waitFor(Function<WebDriver, Boolean> _func) {
//        getWait().until(_func);
//    }

	/**
	 * Navigates to the specified URL
	 *
	 * @param _url URL to navigate to
	 */
	protected void navigateTo(String _url) {
		log.info(String.format("Navigating to the following URL: [%s]", _url));
		driverManager.getWiciDriver().navigate().to(_url);
	}

	/**
	 * Navigates backwards
	 */
	protected void navigateBack() {
		log.info("Navigating to the previous page..");
		driverManager.getWiciDriver().navigate().back();
	}

	/**
	 * Navigates forward
	 */
	protected void navigateForward() {
		log.info("Navigating to the next page..");
		driverManager.getWiciDriver().navigate().forward();
	}

	/**
	 * Switch tabs to the specified tab. Tab is chosen based on the title of the tab
	 *
	 * @param _tab Tab Title
	 */
	protected void switchTab(String _tab) {
		log.info(String.format("Switching to tab using title value: [%s]..", _tab));
		driverManager.getWiciDriver().switchTo().window(_tab);
	}

	/**
	 * Switch tabs to the specified tab. Tab is chosen based on the index of the tab
	 *
	 * @param _tab Tab Index
	 */
	protected void switchTab(int _tab) {
		log.info(String.format("Switching to tab using index value: [%d]..", _tab));
		driverManager.getWiciDriver().switchTo().window(Integer.toString(_tab));
	}

//    /**
//     * Switches to the last currently open tab in the current browser instance
//     */
//    protected void switchTabLast() {
//        logger.info("Switching to the last tab");
//        List<String> handles = new ArrayList<>(CommonUtil.getDriver().getWindowHandles());
//        CommonUtil.getDriver().switchTo().window(handles.get(handles.size() - 1));
//    }

//    /**
//     * Switch frames to the specified frame using a string value.
//     * Frames can be selected by using their unique id/name here.
//     *
//     * @param _frame Frame ID/Name
//     */
//    protected void switchFrame(String _frame) {
//        logger.info(String.format("Switching to frame using name/id value: [%s]..", _frame));
//        getDriver().switchTo().frame(_frame);
//    }

//    /**
//     * Switch frames to the specified frame using an integer index
//     *
//     * @param _frame Frame index
//     */
//    protected void switchFrame(int _frame) {
//        logger.info(String.format("Switching to frame using index value: [%d]..", _frame));
//        getDriver().switchTo().frame(_frame);
//    }

	/**
	 * Inputs a String into a WebElement using Selenium's built-in sendKeys
	 * functionality. Clears the element prior to inputting the text.
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _input Text to send to the WebElement
	 */
	public void inputText(By _by, String _input) {
		log.info(String.format("Inputting the following text: [%s] into the given WebElement: [%s]", _input,
				_by.toString()));

		WebElement element = getElementWhenVisible(_by);
		element.clear();
		element.sendKeys(_input);
	}

	/**
	 * Inputs a String into a WebElement using Selenium's built-in sendKeys
	 * functionality. Clears the element prior to inputting the text.
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _input Text to send to the WebElement
	 */
	public void inputText(WebElement element, String _input) {
		log.info(String.format("Inputting the following text: [%s] into the given WebElement: [%s]", _input, element));
		element.clear();
		element.sendKeys(_input);
	}
	
	public void enterText(WebElement element, Keys enter) {
		log.info(String.format("Inputting the following text: [%s] into the given WebElement: [%s]", enter, element));
		element.sendKeys(enter);
	}

	protected void inputTextByCharacter(By _by, String _input) throws InterruptedException {
		log.info(String.format("Inputting the following text: [%s] into the given WebElement: [%s]", _input,
				_by.toString()));

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

	protected void inputTextByCharacter(WebElement element, String _input) throws InterruptedException {
		log.info(String.format("Inputting the following text: [%s] into the given WebElement: [%s]", _input, element));

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
	protected void appendText(By _by, String _input) {
		log.info(String.format("Appending the following text: [%s] into the given WebElement: [%s]", _input,
				_by.toString()));

		WebElement element = getElementWhenVisible(_by);
		element.sendKeys(_input);
	}

	/**
	 * Appends a String into a WebElement using Selenium's built-in sendKeys
	 * functionality.
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _input Text to send to the WebElement
	 */
	protected void appendText(WebElement element, String _input) {
		log.info(String.format("Appending the following text: [%s] into the given WebElement: [%s]", _input, element));

		element.sendKeys(_input);
	}

	/**
	 * Clears the text of the given WebElement using Selenium's built-in clear()
	 * functionality
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	protected void clearText(By _by) {
		log.info(String.format("Clearing the text from the given WebElement: [%s]", _by.toString()));
		WebElement element = getElementWhenVisible(_by);
		element.clear();
	}

	/**
	 * Clears the text of the given WebElement using Selenium's built-in clear()
	 * functionality
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void clearText(WebElement element) {
		log.info(String.format("Clearing the text from the given WebElement: [%s]", element));
		element.clear();
	}

	/**
	 * Selects an option from the drop-down menu by its value
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByValue(By _by, String _value) {
		log.info(String.format("Selecting the following option: [%s] from the given dropdown WebElement: [%s] by VALUE",
				_value, _by.toString()));

		Select select = new Select(getElementWhenVisible(_by));
		select.selectByValue(_value);
	}

	/**
	 * Selects an option from the drop-down menu by its value
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByValue(WebElement element, String _value) {
		log.info(String.format("Selecting the following option: [%s] from the given dropdown WebElement: [%s] by VALUE",
				_value, element));

		Select select = new Select(element);
		select.selectByValue(_value);
	}

	/**
	 * Selects an option from the drop-down menu by its value
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByText(By _by, String _value) {
		log.info(String.format("Selecting the following option: [%s] from the given dropdown WebElement: [%s] by TEXT",
				_value, _by.toString()));

		Select select = new Select(getElementWhenVisible(_by));
		select.selectByVisibleText(_value);
	}

	/**
	 * Selects an option from the drop-down menu by its value
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByText(WebElement element, String _value) {
		log.info(String.format("Selecting the following option: [%s] from the given dropdown WebElement: [%s] by TEXT",
				_value, element));

		Select select = new Select(element);
		select.selectByVisibleText(_value);
	}

	/**
	 * Selects an option from the drop-down menu by its index
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	public void selectFromDropdownByIndex(By _by, int _value) {
		log.info(String.format("Selecting the following option at index: [%d] from the given dropdown WebElement: [%s]",
				_value, _by.toString()));

		Select select = new Select(getElementWhenVisible(_by));
		select.selectByIndex(_value);
	}

	/**
	 * Selects an option from the drop-down menu by its index
	 *
	 * @param _by    Locator to the WebElement to act on
	 * @param _value The value of the drop-down element to use as a locator
	 */
	protected void selectFromDropdownByIndex(WebElement element, int _value) {
		log.info(String.format("Selecting the following option at index: [%d] from the given dropdown WebElement: [%s]",
				_value, element));

		Select select = new Select(element);
		select.selectByIndex(_value);
	}

	public void scrollIntoView(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driverManager.getWiciDriver()).executeScript("arguments[0].scrollIntoView(true);",
				element);
		Thread.sleep(500);
		((JavascriptExecutor) driverManager.getWiciDriver()).executeScript("window.scrollBy(0,-100)");
		Thread.sleep(500);
	}

	/**
	 * Clicks the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 * @throws InterruptedException
	 */
	public void clickElement(By _by) throws InterruptedException {
		log.info(String.format("Clicking the given WebElement: [%s]", _by.toString()));

		dismissHelper(); // added for mobile execution
		WebElement element = getElementWhenVisible(_by);
		try {

			// ScreenShot.takeScreenShot(Drivers.getInstance().getDashDriver(), "PRE
			// SCROLL");
			if (!driverManager.isMobile()) {
				scrollIntoView(element);
			}

			// ScreenShot.takeScreenShot(Drivers.getInstance().getDashDriver(), "POST SCROLL
			// PRE CLICK");
			element.click();

		} catch (ElementClickInterceptedException ex) {
//        	log.info("Caught an ElementClickInterceptedException. Will try close info dialog and reclick", ex);
//        	String fname = ScreenShot.takeScreenShot(Drivers.getInstance().getDashDriver(), this.getClass().getName());
//			log.error("Caught exception screenShotName=" + fname);
			List<WebElement> els = driverManager.getWiciDriver().findElements(By.xpath("//button[@class='con-x']"));
			if (els.size() > 0 && els.get(0).isDisplayed()) {
				log.info("Clicking on possible dialog");
				els.get(0).click();
				Thread.sleep(500);
				log.info("After Clicking on possible dialog");
			}
			try {
				log.info("Reclicking the element");
				element.click();
				log.info("After reclicking the element");
			} catch (Exception e) {
				log.info("Caught an exception clicking an element, falling back to javascript click", e);
				Thread.sleep(500);
				element = getElementWhenVisible(_by);
				((JavascriptExecutor) driverManager.getWiciDriver()).executeScript("arguments[0].click();", element);
				log.info("After clicking the element with JS");

			}

		} catch (Exception ex) {
			log.info("Caught an exception clicking an element, falling back to javascript click", ex);
			Thread.sleep(500);
			element = getElementWhenVisible(_by);
			((JavascriptExecutor) driverManager.getWiciDriver()).executeScript("arguments[0].click();", element);
			log.info("After clicking the element with JS2");

		}
	}

	protected void clickElementJS(By _by) throws InterruptedException {
		log.info(String.format("Clicking the given WebElement: [%s] with javascript", _by.toString()));
		WebElement element = getElementWhenVisible(_by);
		clickElementJS(element);
	}

	public void clickElementJS(WebElement element) throws InterruptedException {
		log.info("Clicking the given WebElement with javascript");
		try {
			scrollIntoView(element);
			((JavascriptExecutor) driverManager.getWiciDriver()).executeScript("arguments[0].click();", element);

		} catch (ElementClickInterceptedException ex) {
			log.info("Caught an ElementClickInterceptedException. Will try close info dialog and reclick", ex);
			List<WebElement> els = driverManager.getWiciDriver().findElements(By.xpath("//button[@class='con-x']"));
			if (els.size() > 0 && els.get(0).isDisplayed()) {
				els.get(0).click();
				Thread.sleep(500);
			}
			((JavascriptExecutor) driverManager.getWiciDriver()).executeScript("arguments[0].click();", element);

		} catch (Exception ex) {
			log.info("Caught an exception clicking an element, falling back to javascript click", ex);
			Thread.sleep(500);
			((JavascriptExecutor) driverManager.getWiciDriver()).executeScript("arguments[0].click();", element);

		}
	}

	protected void clickElement(By _visibleBy, By _clickBy) throws InterruptedException {
		log.info(String.format("Clicking the given WebElement: [%s]", _visibleBy.toString()));
		WebElement element = getElementWhenVisible(_visibleBy);
		try {
			scrollIntoView(element);
			driverManager.getWiciDriver().findElement(_clickBy).click();
		} catch (Exception ex) {
			log.info("Caught exception trying to click element, will retry in 1 second incase it is transient");
			Thread.sleep(500);
			// Scroll down a bit in case another object like "Chat" is blocking the element
			((JavascriptExecutor) driverManager.getWiciDriver()).executeScript("return window.scrollBy(0,100);");
			Thread.sleep(500);
			element = getElementWhenVisible(_visibleBy);
			driverManager.getWiciDriver().findElement(_clickBy).click();
		}
	}

	protected boolean isFieldActive(String _locator) throws Exception {
		log.info(String.format("Checking if field with locator: [%s] is active", _locator));
		WebElement we = getElementWhenVisible(By.xpath(_locator));
		if (we.getAttribute("aria-checked") != null) {
			return we.getAttribute("aria-checked").equals("true");
		} else {
			String _attrVal = we.getAttribute("type");

			if (_attrVal == null || _attrVal.isEmpty()) {
				we = getElementWhenVisible(By.xpath(_locator + "/../span"));
				_attrVal = we.getAttribute("type");
			}
//			if (_attrVal.equals("radio")) {
//				//return (Boolean) ((JavascriptExecutor) CommonUtil.getDriver()).executeScript("return arguments[0].checked;", we);
//				return false;
//			} else {
			return we.isSelected();
//			}
		}

	}

	protected boolean isFieldActive(WebElement element) throws Exception {
		log.info(String.format("Checking if field with locator: [%s] is active", element));
		if (element.getAttribute("aria-checked") != null) {
			return element.getAttribute("aria-checked").equals("true");
		} else {
			String _attrVal = element.getAttribute("type");

			if (_attrVal == null || _attrVal.isEmpty()) {

				element = element.findElement(By.xpath("/../span"));
				_attrVal = element.getAttribute("type");
			}
//			if (_attrVal.equals("radio")) {
//				//return (Boolean) ((JavascriptExecutor) CommonUtil.getDriver()).executeScript("return arguments[0].checked;", we);
//				return false;
//			} else {
			return element.isSelected();
//			}
		}

	}

	public void clickElement(WebElement element) throws InterruptedException {
		log.info("Entering clickElement");
		if (!driverManager.isMobile()) {
			scrollIntoView(element);
		}
		dismissHelper(); // added for mobile execution
		element.click();
	}

	/**
	 * Validate the text of the given WebElement against the supplied text
	 *
	 * @param _by   Locator to the WebElement to act on
	 * @param _text Text to compare
	 */
	protected void validateText(By _by, String _text) throws ValidationException {
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
	 * Validate the text of the given WebElement against the supplied text Overload
	 * to allow removal of spaces, line breaks and tabs
	 *
	 * @param _text Text to compare
	 */
	public void validateText(WebElement element, String _text) throws ValidationException {
		validateText(element, _text, false);
	}

	/**
	 * Get the active webElement in a list of Elements based on Xpath Overload to
	 * allow removal of spaces, line breaks and tabs
	 *
	 * @param _text Text to compare
	 */
	protected WebElement getActiveWebElement(By _by) throws ValidationException {
		dismissHelper();
		List<WebElement> allElements = driverManager.getWiciDriver().findElements(_by);

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
	protected void validateText(WebElement element, String _text, boolean stripSpacesTabsNewLines)
			throws ValidationException {
		String toCompare = element.getText().trim();
		validateText(toCompare, _text, stripSpacesTabsNewLines);
	}

	protected void validateText(String toCompare, String _text, boolean stripSpacesTabsNewLines)
			throws ValidationException {
		if (stripSpacesTabsNewLines) {
			toCompare = stripSpacesTabsNewLines(toCompare);
			_text = stripSpacesTabsNewLines(_text);
		}

		String toCompare2 = toCompare.replaceAll("’", "'");
		String _text2 = _text.replaceAll("’", "'");
		if (!toCompare.equals(toCompare2)) {
			log.info("Updated toCompare=\"" + toCompare + "\" toCompare2=\"" + toCompare2 + "\"");
		}
		if (!_text.equals(_text2)) {
			log.info("Updated _text=\"" + _text + "\" _text2=\"" + _text2 + "\"");
		}
		log.info("validateText toCompare2=" + toCompare2 + " _text2=" + _text2);
		if (!toCompare2.equals(_text2)) {
			throw new ValidationException(
					String.format("Failed to validate the given text against the text of the WebElement.\n"
							+ "--------------------------\n" + "Element:  \"%s\"\n" + "Expected: \"%s\"\n"
							+ "Received: \"%s\"", toCompare, _text2, toCompare2));
		}
	}

	protected String stripSpacesTabsNewLines(String text) {
		try {
			text = text.replaceAll("[\\n\\t\\r ]", "");

			text = text.replaceAll("[\\p{Cf}]", "");

			text = text.replaceAll("[^\\x00-\\x7f]", "");

			text = text.replaceAll("[^a-zA-Z0-9\\s+]", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * Validate the text of the given WebElement against the supplied text array. If
	 * any match, validation passes. By default no spaces, line breaks of tabs are
	 * removed
	 *
	 * @param _text Text to compare
	 */
	protected void validateText(WebElement element, String[] _textA) throws ValidationException {
		String toCompare = element.getText();
		log.info("Entering validateText");
		for (String _text : _textA) {
			log.info("_textA element " + _text);
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

	protected void validateTextRegEx(WebElement element, String patternS) throws ValidationException {
		log.info("Entering validateTextRegEx patternS=" + patternS);
		Pattern pattern = Pattern.compile(patternS);
		Matcher matcher = pattern.matcher(element.getText());
		if (matcher.find() == false) {
			throw new ValidationException(
					"Unable to find text matching pattern=" + patternS + "\n in text=" + element.getText());
		}
	}

	protected boolean compareText(By _by, String _text) {
		WebElement element = getElementWhenVisible(_by);
		String toCompare = element.getText();
		return toCompare.equals(_text);
	}

	protected boolean compareText(WebElement element, String _text) {
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
		System.out.println("text val: " + _text);
		WebElement element = getElementWhenVisible(_by);

		System.out.println("eleme text: " + element.getText());
		String toCompare = element.getAttribute("value");

		System.out.println("eleme val: " + toCompare);
		_text = replaceStringTableElements(_text);

		System.out.println("text val 2: " + _text);
		validateText(toCompare, _text, true);
//        if(!toCompare.equals(_text)) {
//            throw new ValidationException(String.format(
//                    "Failed to validate the given value against the value of the WebElement.\n" +
//                            "--------------------------\n" +
//                            "Element:  \"%s\"\n" +
//                            "Expected: \"%s\"\n" +
//                            "Received: \"%s\"", element, _text, toCompare)
//            );
//        }
	}

	/**
	 * Validate the value of the given WebElement against the supplied text
	 *
	 * @param _by   Locator to the WebElement to act on
	 * @param _text Text to compare
	 */
	public void validateValue(WebElement element, String _text) throws ValidationException {
		System.out.println("text val: " + _text);

		System.out.println("eleme text: " + element.getText());
		String toCompare = element.getAttribute("value");

		System.out.println("eleme val: " + toCompare);
		_text = replaceStringTableElements(_text);

		System.out.println("text val 2: " + _text);
		validateText(toCompare, _text, true);
//        if(!toCompare.equals(_text)) {
//            throw new ValidationException(String.format(
//                    "Failed to validate the given value against the value of the WebElement.\n" +
//                            "--------------------------\n" +
//                            "Element:  \"%s\"\n" +
//                            "Expected: \"%s\"\n" +
//                            "Received: \"%s\"", element, _text, toCompare)
//            );
//        }
	}

	public String retrieveValue(By _by) {
		WebElement el = getElementWhenVisible(_by);
		return el.getAttribute("value");
	}

	public String retrieveValue(WebElement element) {
		return element.getAttribute("value");
	}

	protected List<String> retrieveValuesFromSelect(By _by) {
		Select select = new Select(getElementWhenVisible(_by));
		return retrieveValuesFromSelect(select);
	}

	public List<String> retrieveValuesFromSelect(WebElement element) {
		Select select = new Select(element);
		return retrieveValuesFromSelect(select);
	}

	protected List<String> retrieveValuesFromSelect(Select select) {
		List<WebElement> els = select.getOptions();
		List<String> vals = new ArrayList<String>();
		for (WebElement el : els) {
			vals.add(el.getText());
		}
		return vals;
	}

	protected String retrieveValueFromSelect(By _by) {
		Select select = new Select(getElementWhenVisible(_by));
		return retrieveValueFromSelect(select);
	}

	public String retrieveValueFromSelect(WebElement element) {
		Select select = new Select(element);
		return retrieveValueFromSelect(select);
	}

	protected String retrieveValueFromSelect(Select select) {
		WebElement el = select.getFirstSelectedOption();
		return el.getText();
	}

	public String retrieveAttrValueFromSelect(WebElement element) {
		Select select = new Select(element);
		return retrieveAttributeValueFromSelect(select);
	}

	protected String retrieveAttributeValueFromSelect(Select select) {
		WebElement el = select.getFirstSelectedOption();
		return el.getAttribute("value");
	}

	
	protected void validateSelectText(By _by, String expectedVal) throws ValidationException {
		String val = retrieveValueFromSelect(_by);
		validateText(expectedVal, val);
	}

	protected void validateSelectText(WebElement element, String expectedVal) throws ValidationException {
		String val = retrieveValueFromSelect(element);
		validateText(expectedVal, val);
	}

	protected String retrieveValuePlaceholder(By _by) {
		WebElement el = getElementWhenVisible(_by);
		return el.getAttribute("placeholder");
	}

	public String retrieveValuePlaceholder(WebElement element) {
		return element.getAttribute("placeholder");
	}

	public void validatePlaceholder(By _by, String placeholder) throws ValidationException {
		placeholder = searchDataDictionary(placeholder);
		validateString(retrieveValuePlaceholder(_by), placeholder);
	}

	public void validatePlaceholder(WebElement element, String placeholder) throws ValidationException {
		placeholder = searchDataDictionary(placeholder);
		validateString(retrieveValuePlaceholder(element), placeholder);
	}

	/**
	 * Validate that the text of the given WebElement contains the supplied text
	 *
	 * @param _by   Locator to the WebElement to act on
	 * @param _text Text to find
	 */

	protected void validateTextContains(By _by, String _text) throws ValidationException {
		WebElement element = getElementWhenVisible(_by);
		validateTextContains(element, _text);
	}

	/**
	 * Validate that the text of the given WebElement contains the supplied text
	 *
	 * @param _by   Locator to the WebElement to act on
	 * @param _text Text to find
	 */

	protected void validateTextContains(WebElement element, String _text) throws ValidationException {
		validateTextContains(element, _text);
	}

	protected void validateTextContains(String actualText, String expectedText) throws ValidationException {
		log.info("validateTextContains actualText=" + actualText + " expectedText=" + expectedText);
		if (!actualText.contains(expectedText)) {
			throw new ValidationException(
					String.format(
							"Failed to validate if the given text is contained in the text.\n"
									+ "--------------------------\n" + "Expected: \"%s\"\n" + "Received: \"%s\"",
							actualText, expectedText));
		}
	}

	protected void validateText(String text1, String text2, IFormatFilter filter1, IFormatFilter filter2)
			throws ValidationException {
		if (filter1 != null) {
			text1 = filter1.filter(text1);
		}
		if (filter2 != null) {
			text2 = filter2.filter(text2);
		}
		String t1 = text1.replaceAll("’", "'");
		t1.replaceAll("$", "");
		String t2 = text2.replaceAll("’", "'");
		t2.replaceAll("$", "");
		if (!t1.equals(text1)) {
			log.info("Updated text1=\"" + text1 + "\" t1=\"" + t1 + "\"");
		}
		if (!t2.equals(text2)) {
			log.info("Updated text2=\"" + text2 + "\" t2=\"" + t2 + "\"");
		}
		log.info("Validate text t1=" + t1 + " t2=" + t2);
		if (!text1.equals(text2)) {
			throw new ValidationException(
					String.format(
							"Failed to validate if the given text is contained in the text of the WebElement.\n"
									+ "--------------------------\n" + "Expected: \"%s\"\n" + "Received: \"%s\"",
							t1, t2));
		}
	}

	protected void validateText(String text1, String text2) throws ValidationException {
		validateText(text1, text2, null, null);
	}

	protected void validateTooltip(String locator, String message) throws ValidationException {
		log.info(String.format("Attempting to validate the tool-tip has value: [%s]", message));
		By _by = By.xpath(locator);
		if (isElementVisible(_by)) {
			WebElement we = getElementWhenVisible(_by);
			System.out.println("web element trimmed text: " + we.getAttribute("class"));
			String[] ttMsg = we.getText().trim().split("\n");
			if (message.equalsIgnoreCase(ttMsg[1])) {
				log.info("Successfully validated tooltip message");
			} else {
				log.error("Expected value:" + message);
				log.error("Actual value:" + ttMsg[1]);
				throw new ValidationException("Failed to validate - Tool tip message does not match.");
			}
		} else {
			throw new ValidationException("Failed to validate - Tool Tip message is not visible - Check your locator");
		}
	}

	protected void validateTooltip(WebElement element, String message) throws ValidationException {
		log.info(String.format("Attempting to validate the tool-tip has value: [%s]", message));
		if (isElementVisible(element)) {
			System.out.println("web element trimmed text: " + element.getAttribute("class"));
			String[] ttMsg = element.getText().trim().split("\n");
			if (message.equalsIgnoreCase(ttMsg[1])) {
				log.info("Successfully validated tooltip message");
			} else {
				log.error("Expected value:" + message);
				log.error("Actual value:" + ttMsg[1]);
				throw new ValidationException("Failed to validate - Tool tip message does not match.");
			}
		} else {
			throw new ValidationException("Failed to validate - Tool Tip message is not visible - Check your locator");
		}
	}

	public void validateBackgroundFieldColor(String _colorOption, WebElement element) throws ValidationException {
		log.info("validate background Color");
		String[] _compareColor = ColorConstants.getConstantValueForColor(_colorOption);
		validateBackgroundColor(_compareColor, element);			
		log.info("validation passed");
	}
	
	public void validateBackgroundColor(String[] _colorOption, WebElement element) throws ValidationException {
		log.info("Entering validateBackgroundColor");
		if (Arrays.asList(_colorOption).contains(element.getCssValue("background-color").trim().replace(";", "").trim())) {
			log.info("validation passed");
		} else {
			throw new ValidationException(
					"Failed validating the background color color=" + element.getCssValue("background-color"));
		}
	}

	protected void validateBackgroundColor(String[] _colorOption, String elementBackColor) throws ValidationException {
		log.info("Entering validateBackgroundColor");
		if (Arrays.asList(_colorOption).contains(elementBackColor)) {
			log.info("validation passed");
		} else {
			throw new ValidationException("Failed validating the background color color=" + elementBackColor);
		}
	}

	public void validateColor(String _colorOption, WebElement element) throws ValidationException {
		log.info("validate Color");
		String[] _compareColor = ColorConstants.getConstantValueForColor(_colorOption);
		validateColor(_compareColor, element);			
		log.info("validation passed");
	}
	
	protected void validateColor(String[] _colorOption, WebElement element) throws ValidationException {
		log.info("Entering validateColor");
		validateColor(_colorOption, element.getCssValue("color").trim());
	}

	public void validateColor(String[] _colorOption, String color) throws ValidationException {
		log.info("Entering validateColor color=" + color);

		System.out.println("color=" + color);
		System.out.println("_colorOption=" + Arrays.deepToString(_colorOption));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Arrays.asList(_colorOption).contains(color)) {
			log.info("validation passed");
		} else {
			throw new ValidationException(
					"Failed validating the color color=" + color + " colorOption=" + Arrays.deepToString(_colorOption));
		}
	}

	protected boolean isColor(String[] _colorOption, String color) {
		return Arrays.asList(_colorOption).contains(color);
	}

	/**
	 * Validate the visibility status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	protected void validateVisible(By _by) throws ValidationException {
		try {
			waitForVisible(_by);
		} catch (Exception ex) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is visible.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", _by));
		}
	}

	/**
	 * Validate the visibility status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateVisible(WebElement element) throws ValidationException {
		try {
			waitForVisible(element);
		} catch (Exception ex) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is visible.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", element));
		}
	}

	/**
	 * Validate the invisibility status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	protected void validateInvisible(By _by) throws ValidationException {
		if (!waitForInvisible(_by)) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is invisible.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", _by));
		}
	}

	/**
	 * Validate the invisibility status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateInvisible(WebElement element) throws ValidationException {
		if (!waitForInvisible(element)) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is invisible.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", element));
		}
	}

	protected void validateEmptyText(By _by) throws ValidationException {
		if (!waitForEmptyText(_by)) {
			throw new ValidationException("Failed validateEmptyText by=" + _by.toString());
		}
	}

	public void validateEmptyText(WebElement element) throws ValidationException {
		if (!waitForEmptyText(element)) {
			throw new ValidationException("Failed validateEmptyText by=" + element);
		}
	}

	protected void validateNonEmptyText(By _by) throws ValidationException {
		if (!waitForNonEmptyText(_by)) {
			throw new ValidationException("Failed validateNonEmptyText by=" + _by.toString());
		}
	}

	protected void validateNonEmptyText(WebElement element) throws ValidationException {
		if (!waitForNonEmptyText(element)) {
			throw new ValidationException("Failed validateNonEmptyText by=" + element);
		}
	}

	/**
	 * Validate the enabled status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	protected void validateEnabled(By _by) throws ValidationException {
		if (!waitForEnabled(_by)) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is enabled.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", _by));
		}
	}

	/**
	 * Validate the enabled status of the given WebElement
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void validateEnabled(WebElement element) throws ValidationException {
		if (!waitForEnabled(element)) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is enabled.\n"
					+ "--------------------------\n" + "Element:  \"%s\"", element));
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
	 * Validate the disabled status of the given WebElement. Should be visible and
	 * disabled
	 *
	 * @param _by Locator to the WebElement to act on
	 */
//	public void validateDisabled(WebElement element) throws ValidationException {
//		boolean disabled = waitForDisabled(element);
//		if (disabled == true) {
//			// Make sure it stays disabled
//			disabled = waitForDisabled(element);
//		}
//		if (!disabled) {
//			throw new ValidationException(String.format("Failed to validate if the WebElement is disabled.\n"
//					+ "--------------------------\n" + "Element:  \"%s\"", element));
//		}
//	}

	/**
	 * Validate the given attribute on the given WebElement
	 *
	 * @param _by       Locator to the WebElement to act on
	 * @param _attr     Attribute to find on the WebElement
	 * @param _expected Expected value of the element
	 */
	protected void validateAttribute(By _by, String _attr, String _expected) throws ValidationException {
		WebElement element = getElementWhenVisible(_by);
		validateAttribute(element, _attr, _expected);
	}

	protected void validateAttribute(WebElement element, String _attr, String _expected) throws ValidationException {
		String toCompare = element.getAttribute(_attr).trim();
		log.info("validateAttribute _attr=" + _attr + " _expected=" + _expected);
		;
		if (!toCompare.equals(_expected)) {
			throw new ValidationException(String.format(
					"Failed to validate WebElement attribute against expected value.\n" + "--------------------------\n"
							+ "Element:  \"%s\"\n" + "Attribute: \"%s\"\n" + "Expected: \"%s\"\n" + "Received: \"%s\"",
					element, _attr, _expected, toCompare));
		}
	}

	protected void validateAttributeContains(WebElement element, String _attr, String _expected)
			throws ValidationException {
		String toCompare = element.getAttribute(_attr);
		log.info("validateAttributeContains _attr=" + _attr + " _expected=" + _expected + " toCompare=" + toCompare);
		if (!toCompare.contains(_expected)) {
			throw new ValidationException(
					String.format("Failed to validate WebElement attribute contains against expected value.\n"
							+ "--------------------------\n" + "Element:  \"%s\"\n" + "Expected Contains: \"%s\"\n"
							+ "Received: \"%s\"", element, _attr, toCompare));
		}
	}

	protected void validateAttributeContains(WebElement element, String _attr, String[] _expecteds)
			throws ValidationException {
		String toCompare = element.getAttribute(_attr);
		log.info("validateAttributeContains _attr=" + _attr + " toCompare=" + toCompare);
		boolean foundIt = false;

		for (String _expected : _expecteds) {
			log.info("validateAttributeContains _expected=" + _expected);
			if (toCompare.contains(_expected)) {
				foundIt = true;
				log.info("validateAttributeContains found match");
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

//    // Currently only works with XPATH
//    protected void validateFieldError(String _xpath, String _expected) throws ValidationException {
//        String[]      errorMessages  = getFieldErrors(_xpath);
//        StringBuilder logOutput      = new StringBuilder();
//
//        if(errorMessages.length == 0 && !_expected.isEmpty()) {
//            throw new ValidationException("Failed to validate error messages on the given field. No errors were found.");
//        }
//
//        for (String error: errorMessages) {
//            if(error.trim().equalsIgnoreCase(_expected)) {
//                //logger.info("Successfully validated the error text: {}", _expected);
//            	log.info(String.format("Successfully validated the error text: %s", _expected));
//                return;
//            } else {
//                logOutput.append(String.format("\"%s\",\n", error));
//            }
//        }
//
//        throw new ValidationException(String.format(
//                "Failed to validate errors attribute against expected value.\n" +
//                        "--------------------------\n" +
//                        "Element:  \"%s\"\n" +
//                        "Expected: \"%s\"\n" +
//                        "Received: \"%s\"", _xpath, _expected, logOutput)
//        );
//    }
	protected void validateFieldErrorNoRedField(String _xpath, String _expected) throws ValidationException {
		log.info("Entering validateFieldErrorNoRedField _xpath=" + _xpath + " _expected=" + _expected);
		By locator = By.xpath(_xpath + "div//small");
		WebElement el = getElementWhenVisible(locator);
		validateText(el.getText(), _expected);
		validateColor(ColorConstants.RED, el);
		log.info("Exiting validateFieldErrorNoRedField");
	}

	protected void validateFieldErrorNoRedField(WebElement element, String _expected) throws ValidationException {
		log.info("Entering validateFieldErrorNoRedField _xpath=" + element + " _expected=" + _expected);
		validateText(element.getText(), _expected);
		validateColor(ColorConstants.RED, element);
		log.info("Exiting validateFieldErrorNoRedField");
	}

	protected void validateFieldError(String _xpath, String _expected) throws ValidationException {
		log.info("Entering validateFieldError _xpath=" + _xpath + " _expected=" + _expected);
		By locator = By.xpath(_xpath + "/parent::div//small");
		WebElement el = getElementWhenVisible(locator);

		// validateText(el.getText(), _expected); getParentTextNode
		validateText(getParentTextNode(el), _expected);
		validateColor(ColorConstants.RED, el);

		// Validate the parent is PINK
		el = getElementWhenVisible(By.xpath(_xpath));
		validateBackgroundColor(ColorConstants.PINK, el);

		log.info("Exiting validateFieldError");
	}

	protected void validateFieldError(WebElement element, String _expected) throws ValidationException {
		log.info("Entering validateFieldError _xpath=" + element + " _expected=" + _expected);
		// validateText(el.getText(), _expected); getParentTextNode
		validateText(getParentTextNode(element), _expected);
		validateColor(ColorConstants.RED, element);

		// Validate the parent is PINK
		// validateBackgroundColor(ColorConstants.PINK, el);

		log.info("Exiting validateFieldError");
	}

	protected void validateNoFieldError(String _xpath) throws ValidationException {
		log.info("Entering validateNoFieldError _xpath=" + _xpath);
		By locator = By.xpath(_xpath + "/parent::div//small");
		boolean gotError = true;
		try {
			WebElement el = getElementWhenVisible(locator);
			log.info("Found error element text=" + el.getText());
		} catch (TimeoutException ex) {
			log.info("No error element was visible");
			gotError = true;
		}
		validateBoolean(gotError, true);

		log.info("Exiting validateNoFieldError");
	}

	protected void validateNoFieldError(WebElement element) throws ValidationException {
		log.info("Entering validateNoFieldError _xpath=" + element);
		boolean gotError = true;
		try {
			log.info("Found error element text=" + element.getText());
		} catch (TimeoutException ex) {
			log.info("No error element was visible");
			gotError = true;
		}
		validateBoolean(gotError, true);

		log.info("Exiting validateNoFieldError");
	}
//    /**
//     * Validate this page against another Page' Class<?> object
//     *
//     * @param _page Page class to compare against
//     * @return True if this.getClass() matches the given page class, otherwise False
//     */
//    protected boolean validatePage(Class<? extends IPage> _page) {
//        return _page.equals(this.getClass());
//    }

	/**
	 * Additional helper validation methods
	 */
	protected void validateLong(long _value, long _expected) throws ValidationException {

		if (_value == _expected) {
			log.info("validateLong passed _value=" + _value + " _expected=" + _expected);
		} else {
			throw new ValidationException("Failed to validateLong  _value=" + _value + " _expected=" + _expected);
		}
	}

	protected void validateBoolean(boolean _value, boolean _expected) throws ValidationException {

		if (_value == _expected) {
			log.info("validateBoolean passed _value=" + _value + " _expected=" + _expected);
		} else {
			throw new ValidationException("Failed to validateBoolean  _value=" + _value + " _expected=" + _expected);
		}
	}

	protected void validateNotNull(WebElement o) throws ValidationException {

		if (o != null) {
			log.info("validateNotNull passed o==" + o);
		} else {
			throw new ValidationException("Failed to validateNotNull");
		}
	}

	protected void validateString(String _value, String _expected) throws ValidationException {
		if (_value.contentEquals(_expected)) {
			log.info("validateString passed _value=" + _value + " _expected=" + _expected);
		} else {
			throw new ValidationException("Failed to validateString  _value=" + _value + " _expected=" + _expected);
		}
	}

	protected void validateBetween(double _value, double _lowerBound, double _upperBound) throws ValidationException {

		if (_value >= _lowerBound && _value <= _upperBound) {
			log.info("validateInt passed _value=" + _value + " _lowerBound=" + _lowerBound + " _upperBound="
					+ _upperBound);
		} else {
			throw new ValidationException("Failed to validateInt  _value=" + _value + " _lowerBound=" + _lowerBound
					+ " _upperBound=" + _upperBound);
		}
	}

	protected void validateCurrencyFormat(String currency) throws ValidationException {
		Pattern pat1 = Pattern.compile("^\\$(?:0|[1-9]\\d{0,2}(?:\\,\\d{3})*).\\d{2}$");
		Pattern pat2 = Pattern.compile("^(?:0|[1-9]\\d{0,2}(?:\\ \\d{3})*),\\d{2} \\$$");

		// Negatives
		Pattern pat3 = Pattern.compile("^\\-\\$(?:0|[1-9]\\d{0,2}(?:\\,\\d{3})*).\\d{2}$");
		Pattern pat4 = Pattern.compile("^\\-(?:0|[1-9]\\d{0,2}(?:\\ \\d{3})*),\\d{2} \\$$");

		Matcher match1 = pat1.matcher(currency);
		Matcher match2 = pat2.matcher(currency);
		Matcher match3 = pat3.matcher(currency);
		Matcher match4 = pat4.matcher(currency);
		System.out.println("validateCurrencyFormat currency=" + currency);
		if ((match1.find() || match2.find() || match3.find() || match4.find()) == false) {
			throw new ValidationException("Invalid currency format " + currency);
		}
	}

	protected void validateDateFormat(String dateS) throws ValidationException {
		SimpleDateFormat format = TestLanguage.getLanguage() == Language.English ? new SimpleDateFormat("MMMM dd, yyyy")
				: new SimpleDateFormat("dd MMMM yyyy", Locale.CANADA_FRENCH);
		log.info("validateDateFormat dateS=" + dateS);
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
		List<WebElement> elements = driverWait.getWebDriverWait()
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		String[] toReturn = new String[elements.size()];
		for (int i = 0; i < elements.size(); i++) {
			toReturn[i] = elements.get(i).getText();
		}
		return toReturn;
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
	private String[] getFieldErrors(WebElement element) {
		List<WebElement> els = element.findElements(By.xpath("/parent::div//small"));
		List<WebElement> elements = driverWait.getWebDriverWait()
				.until(ExpectedConditions.visibilityOfAllElements(els));
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
		log.info(String.format("Searching for an entry in the data dictionary for input: %s", _input));
		if (_input.startsWith("dd:")) {
			log.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			String lang = TestLanguage.getLanguage().toString();
			return DataDictionary.getMessageFromDataDictionary(lang, parts[1]);
		}
		return _input;
	}

	protected String searchDataDictionaryDontStripHTML(String _input) {
		log.info(String.format("Searching for an entry in the data dictionary dont strip html for input: %s", _input));
		if (_input.startsWith("dd:")) {
			log.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			String lang = TestLanguage.getLanguage().toString();
			return DataDictionary.getMessageFromDataDictionaryDontStripHTML(lang, parts[1]);
		}
		return _input;
	}

	protected String searchDataDictionary(String _input, Language lang) {
		log.info(String.format("Searching for an entry in the data dictionary for input: %s", _input));
		if (_input.startsWith("dd:")) {
			log.info("Object is prepended with \"dd:\" - Returning associated value..");
			String[] parts = _input.split("dd:");
			// todo inline this here
			return DataDictionary.getMessageFromDataDictionary(lang.toString().substring(0, 1), parts[1]);
		}
		return _input;
	}

	private static String locUpdateSuccessAlert = ".//div[@id='successErrorAlert']/div";
	private static String locUpdateSuccessAlertButton = "//button[@id='success-error-banner']/../div";
	private static String locUpdateSuccessAlertText = "//span[@ng-bind-html='alert.msg']";

	public void validateUpdatedSuccessAlert(String msgKey) throws ValidationException, InterruptedException {
		log.info("Entering validateUpdatedSuccessAlert");

		waitForVisible(By.xpath(locUpdateSuccessAlert));
		WebElement el = getElementWhenVisible(By.xpath(locUpdateSuccessAlertButton));
		validateBackgroundColor(ColorConstants.GREEN, el);
		validateText(By.xpath(locUpdateSuccessAlertText), searchDataDictionary(msgKey));
		if (driverManager.isMobile()) {
			clickElementJS(By.xpath("//button[@id='success-error-banner']"));
		} else {
			clickElement(By.xpath("//button[@id='success-error-banner']"));
		}

		log.info("Exiting validateUpdatedSuccessAlert");
	}

	public void validateUpdatedFailureAlert(String msgKey) throws ValidationException, InterruptedException {
		log.info("Entering validateUpdatedFailureAlert");

		WebElement el = getElementWhenVisible(By.xpath(locUpdateSuccessAlert));
		validateBackgroundColor(ColorConstants.RED, el);
		validateText(By.xpath(locUpdateSuccessAlertText), searchDataDictionary(msgKey));
		clickElement(By.xpath("//*[@id=\"success-error-banner\"]/span[1]"));

		log.info("Exiting validateUpdatedFailureAlert");
	}

	protected boolean todayAfter_yyyyMMdd(String dateS) {
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
	protected String getParentTextNode(WebElement e) {
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
	public String getText(By _by) {
		WebElement element = getElementWhenVisible(_by);
		String text = element.getText();
		return text;
	}

	public String getTextByElement(WebElement element) {
		String text = element.getText();
		return text;
	}

	public void dismissHelper() {
		// Dismiss helper
		List<WebElement> helper = driverManager.getWiciDriver()
				.findElements(By.xpath("//*[@id=\"con-notification-title\"]"));

		log.info("Dismiss helper. Popover list size: " + helper.size());
		if (helper.size() > 0) { // if (helper.size()>0 &&
									// helper.get(0).getAttribute("aria-hidden").equalsIgnoreCase("false")) {
			log.info("Found popover");
			WebElement button = driverManager.getWiciDriver()
					.findElement(By.xpath("//*[@id=\"con-notification\"]/button"));
			try {
				button.click();
			} catch (Exception ex) {
				log.info("Unable to click con-notification button");
			}
		}
	}

	// Used to wait for page loading spinners for pages that load text early Eg.
	// Settings/Payments titles
	public void waitForSpinnersInvisible() {
		if (driverManager.isMobile()) {
			dismissHelper();
		}
		List<WebElement> spinners = driverManager.getWiciDriver().findElements(By.xpath("//div[@class='spinner']"));
		// List<WebElement> spinners =
		// getAllVisibleElements(By.xpath("//div[@class='spinner']"));

		log.info("Wait for spinners invisible. list size: " + spinners.size());
		try {
			driverWait.getWebDriverWait().until(ExpectedConditions.invisibilityOfAllElements(spinners));
		} catch (Exception e) {
			log.info("Wait exception e: " + e);
			System.out.println("Wait exception e: " + e);
		}
	}

	// Overloaded Method by passing attribute name as Parameter
	/**
	 * Get the Attribute of the passed Attribute name
	 *
	 * @param _by  Locator to the WebElement to act on
	 * @param Name of the Attribute for which we need value
	 */
	public String getAttribute(By _by, String attributeName) {
		WebElement _element = getElementWhenVisible(_by);
		return _element.getAttribute(attributeName);
	}

	/**
	 * Get the Attribute of the passed Attribute name
	 *
	 * @param _by  Locator to the WebElement to act on
	 * @param Name of the Attribute for which we need value
	 */
	public String getAttribute(WebElement _element, String attributeName) {
		return _element.getAttribute(attributeName);
	}

	/**
	 * Getting the text based on passed text/based on Data dictionary Label
	 *
	 * @param Input Text for which we need to get Value
	 */
	public String getInputText(String input) {
		log.info(String.format("Scrubbing input text: [%s]...", input));
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
			log.error("An error occurred while processing input text", e);
		}
		log.info("Returning value: " + value);
		return value;
	}

	/**
	 * Verifying that tool tip is checked
	 *
	 * @param _by Locator to the WebElement to act on
	 */
	public void toolTipChecked(By _by) throws Exception {
		log.info(String.format("Attempting to validate that tooltip [%s] is checked", _by));
		WebElement we = getElementWhenVisible(_by);
		String ttVal = we.getCssValue("color").trim();
		if (Arrays.asList(ColorConstants.GREEN).contains(ttVal)) {
			log.info("Successfully validated tooltip as checked");
		} else {
			throw new Exception("failed to validate tool tip as checked. Original displayed color =" + ttVal);
		}
	}

	/**
	 * Verifying that tool tip is checked
	 *
	 * @param the WebElement to act on
	 */
	public void toolTipChecked(WebElement element) throws Exception {
		log.info(String.format("Attempting to validate that tooltip [%s] is checked", element));
		String ttVal = element.getCssValue("color").trim();
		if (Arrays.asList(ColorConstants.GREEN).contains(ttVal)) {
			log.info("Successfully validated tooltip as checked");
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
		log.info(String.format("Attempting to validate tool-tip: [%s] strength meter length as: [%s]", _by,
				_lineLength));
		WebElement we = getElementWhenVisible(_by);
		int lineLength = Integer.parseInt(we.getAttribute("aria-valuenow"));
		if (_lineLength.equalsIgnoreCase("Too Short") && lineLength == 20) {
			log.info("Line is too short.");
		} else if (_lineLength.equalsIgnoreCase("Short") && lineLength == 45) {
			log.info("Line is short.");
		} else if (_lineLength.equalsIgnoreCase("Medium") && lineLength == 60) {
			log.info("Line is medium.");
		} else if (_lineLength.equalsIgnoreCase("Long") && lineLength == 75) {
			log.info("Line is long.");
		} else if (_lineLength.equalsIgnoreCase("Full") && lineLength == 100) {
			log.info("Line is full.");
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
		log.info(String.format("Attempting to validate that tooltip [%s] is NOT checked", _by));
		WebElement we = getElementWhenVisible(_by);
		String ttVal = we.getCssValue("color").trim();
		if (Arrays.asList(ColorConstants.GREY).contains(ttVal)) {
			log.info("Successfully validated tooltip as not checked");
		} else {
			throw new Exception("Failed to validate tool tip as checked. Original message =" + ttVal);
		}
	}

	/**
	 * Verifying that tool tip is not checked
	 *
	 * @param the WebElement to act on
	 */
	public void toolTipNotChecked(WebElement element) throws Exception {
		log.info(String.format("Attempting to validate that tooltip [%s] is NOT checked", element));
		String ttVal = element.getCssValue("color").trim();
		if (Arrays.asList(ColorConstants.GREY).contains(ttVal)) {
			log.info("Successfully validated tooltip as not checked");
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
		log.info("Attempting to validate the tooltip line color");
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

	/**
	 * Verifying tool tip colour
	 *
	 * @param the      WebElement to act on
	 * @param Expected Colour
	 */
	public boolean toolTipLineColor(WebElement _element, String color) throws Exception {
		log.info("Attempting to validate the tooltip line color");
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
				Calendar calendar = Calendar.getInstance();
				calendar.set(year, month, day);
				val = new SimpleDateFormat(format).format(calendar);
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
			}
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
	public boolean verifyErrorMessage(String locName, String errorMessage, String colour) throws Exception {
		String expectedmessage;
		String actualmessage;
		boolean status;
		try {
			expectedmessage = getInputText(errorMessage);
			System.out.println("expMes:" + expectedmessage);
			Thread.sleep(5000);
			WebElement we = getElementWhenVisible(By.xpath(locName));
			actualmessage = getAttribute(By.xpath(locName), "innerText");
			System.out.println("actMes:" + actualmessage);
			if (actualmessage.contains("rounded exclamation icon for errorsrounded exclamation icon for errors\n")) {
				System.out.println("IN iF block");
				String replace = actualmessage
						.replace("rounded exclamation icon for errorsrounded exclamation icon for errors\n", "");
				System.out.println("Actual Message " + replace);
				System.out.println("Expected Message " + expectedmessage);
				status = replace.equalsIgnoreCase(expectedmessage);
				System.out.println("Starus " + status);
			} else {
				System.out.println("IN else block");
				System.out.println("Actual Message " + actualmessage);
				System.out.println("Expected Message " + expectedmessage);
				status = actualmessage.equalsIgnoreCase(expectedmessage);
				System.out.println("Status " + status);
			}
			System.out.println("out of If & else block");
			String[] _compareColor = ColorConstants.getConstantValueForColor(colour);
			System.out.println("Colur Array " + _compareColor.length);
			System.out.println("Actual Colour " + we.getCssValue("color").trim());
			if (Arrays.asList(_compareColor).contains(we.getCssValue("color").trim())) {

				log.info(String.format("Successfully validated - Error Message with color ", colour));
			} else {
				throw new ValidationException("Error Message is not of the expected color");
			}
			System.out.println("Bottom of try block");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println("After catch block");
		return status;
	}

	/**
	 * fetches the Error message displayed by Element Verifies error message against
	 * Datadictionary Reference Verifies error verifies message text color
	 * 
	 * @param locName
	 * @param errorMessage
	 * @param colour
	 * @return
	 * @throws Exception
	 */
	public boolean verifyErrorMessage(WebElement element, String errorMessage, String colour) throws Exception {
		String expectedmessage;
		String actualmessage;
		boolean status;
		try {
			expectedmessage = getInputText(errorMessage);
			System.out.println("expMes:" + expectedmessage);
			Thread.sleep(5000);
			actualmessage = getAttribute(element, "innerText");
			System.out.println("actMes:" + actualmessage);
			if (actualmessage.contains("rounded exclamation icon for errorsrounded exclamation icon for errors\n")) {
				System.out.println("IN iF block");
				String replace = actualmessage
						.replace("rounded exclamation icon for errorsrounded exclamation icon for errors\n", "");
				System.out.println("Actual Message " + replace);
				System.out.println("Expected Message " + expectedmessage);
				status = replace.equalsIgnoreCase(expectedmessage);
				System.out.println("Starus " + status);
			} else {
				System.out.println("IN else block");
				System.out.println("Actual Message " + actualmessage);
				System.out.println("Expected Message " + expectedmessage);
				status = actualmessage.equalsIgnoreCase(expectedmessage);
				System.out.println("Status " + status);
			}
			System.out.println("out of If & else block");
			String[] _compareColor = ColorConstants.getConstantValueForColor(colour);
			System.out.println("Colur Array " + _compareColor.length);
			System.out.println("Actual Colour " + element.getCssValue("color").trim());
			if (Arrays.asList(_compareColor).contains(element.getCssValue("color").trim())) {

				log.info(String.format("Successfully validated - Error Message with color ", colour));
			} else {
				throw new ValidationException("Error Message is not of the expected color");
			}
			System.out.println("Bottom of try block");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println("After catch block");
		return status;
	}

	public void moveToElement(WebElement element) throws Exception {
		try {
			Actions builder = new Actions(driverManager.getWiciDriver());
			builder.moveToElement((element)).click().build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to move element");
		}

	}

	public String getAppLanguage() throws Exception {
		return applicationProperties.getAppLanguage();
	}

	public void validateIsBlank(WebElement element) throws ValidationException {
		log.info("Entering validateIsBlank");
		if (("").equals(element.getAttribute("value"))) {
			log.info("validation passed");
		} else {
			throw new ValidationException(
					"Failed validating blank field");
		}
	}
	
	public void performDigitalSignature(WebElement element) throws InterruptedException {
		 if (isMobile()) {
			 TouchAction builder = new TouchAction(driverManager.getAppiumDriver());
			//Need to write code to perform digital signature 

		 }else {  
			 Actions builder = new Actions(driverManager.getWiciDriver());
		 	Action signature= builder.moveToElement(element,100,50)
  	    		//start points x axis and y axis.
  	            .clickAndHold()
  	              .moveByOffset(-150, -50) // 2nd point (x1,y1)
  	              .click()
  	              .build();
  	    signature.perform();
		 }
 }
	
	public void validateDisabled(WebElement element) throws ValidationException {
		//boolean disabled = waitForDisabled(element);
		boolean disabled=element.isEnabled();
		if (disabled == false) {
			// Make sure it stays disabled
			disabled =element.isEnabled();
		}
		if (disabled) {
			throw new ValidationException(String.format("Failed to validate if the WebElement is enabled\n"
					+ "--------------------------\n" + "Element:  \"%s\"", element));
		}
//		if (disabled == true) {
//			// Make sure it stays disabled
//			disabled = waitForDisabled(element);
//		}
//		if (!disabled) {
//			throw new ValidationException(String.format("Failed to validate if the WebElement is disabled.\n"
//					+ "--------------------------\n" + "Element:  \"%s\"", element));
//		}
	}


	public boolean validateElementSelected(WebElement element) {
		if (element.isSelected()) {
			log.info(element + ": element is Selected");
			return true;			
		 }else {
//			 throw new ValidationException(
//						"Element is not selected");
			 return false;
		 }
	}


	public void validateOutlineColor(String _colorOption, WebElement element) throws ValidationException {
		log.info("validate borderColor");
		String[] _compareColor = ColorConstants.getConstantValueForColor(_colorOption);
		validateBorder(_compareColor, element);			
		log.info("validation passed");
	}
	
	protected void validateBorder(String[] _colorOption, WebElement element) throws ValidationException {
		log.info("Entering validateColor");
		System.out.println("color is "+element.getCssValue("border"));
		validateColor(_colorOption, element.getCssValue("border").trim().substring(10).replace(";", "").trim());
	}
	
	public void validateImage(WebElement element,String expectedImageURL) throws ValidationException {
		log.info("validating image");
		System.out.println("Background Image: "+element.getCssValue("background-image").trim());
		 String actualImageURL=element.getCssValue("background-image").trim().substring(5);
		 if(actualImageURL.equals(expectedImageURL)) {
			 log.info("validation passed"); 
		 }else{
			 throw new ValidationException(
						"Image is not displayed");
		 }
	}
	
	public void validateElementNotSelected(WebElement element) throws ValidationException {
		if (!element.isSelected()) {
			log.info(element + ": element is not Selected");
		 }else {
			 throw new ValidationException(
						"Element is selected");
		 }
	}
	
	public void validateDDImage(WebElement element,String expectedImageURL) throws ValidationException {
		log.info("validating image");
		System.out.println("Background Image: "+element.getCssValue("background-image").trim());
		 String actualImageURL=element.getCssValue("background-image").trim().substring(5).replace(")","").replace("\"", "").trim();
		 System.out.println("actual img URL: "+actualImageURL);
		 if(actualImageURL.equals(expectedImageURL)) {
			 log.info("validation passed"); 
		 }else{
			 throw new ValidationException(
						"Image is not displayed");
		 }
	}
	
	/**
	 * 
	 * 
	 * 
	 * ****************************** Mobile Component methods starting
	 * *********************************
	 */
	
	 public void captureScreen(String name) throws Exception {
	        File file = ((TakesScreenshot) driverManager.getWiciDriver()).getScreenshotAs(OutputType.FILE.FILE);
	        String dest = System.getProperty("user.dir") + "\\Screenshots\\" + name + ".png";
	        FileUtils.copyFile(file, new File(dest));
	    }
	  /**
	     * Fluent wait
	     *
	     * @param element element
	     * @param timeout timeoutInMilli
	     */
	  public void fluentWait(WebElement element, int timeout) {
	        Wait wait = new AppiumFluentWait(driverManager.getWiciDriver())
	                .withTimeout(Duration.ofSeconds(timeout))
	                .pollingEvery(Duration.ofMillis(5))
	                .ignoring(NoSuchElementException.class);
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
	  /**
	     * Click on element
	     *
	     * @param element element
	     */
	  public void click(WebElement element) {
	        try {
	            fluentWait(element, 10);
	            element.click();
	            log.info("Clicked on element: " + element);
	        } catch (ElementNotVisibleException e) {
	        	log.error("Element not visible", e);
	        }
	    }
	  /**
	     * AndroidScrollCLick
	     *
	     * @param scrollableListId scrollableId
	     * @param selectionText    selectionText
	     */
	  public void ScrollClick_android(String scrollableListId, String selectionText) {
	        ((AndroidDriver) driverManager.getWiciDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)."
	                + "resourceId(\"" + scrollableListId + "\"))"
	                + ".setAsHorizontalList().scrollIntoView(new UiSelector().text(\"" + selectionText + "\"))").click();
	    }
	  /**
	     * Enter value in text field
	     *
	     * @param element element
	     * @param value   value
	     */
	    public void enter(WebElement element, String value) {
	        try {
	            fluentWait(element, 10);
	            element.click();
	            element.sendKeys(value);
	            log.info("Entered value: " + value + "on element: " + element);
	        } catch (ElementNotVisibleException e) {
	        	log.error("Element not visible", e);
	        }
	    }
	    /**
	     * Get page source
	     *
	     * @return pageSource
	     */
	    
	    public String getPageSource() {
	        return driverManager.getWiciDriver().getPageSource();
	    }
	    

	    /**
	     * NetworkSPeed Android
	     * This method is only available for emulator
	     * @param networkSpeed networkSpeed
	     */
	    public void networkSpeed_android(String networkSpeed) {
	        switch (networkSpeed) {
	            case "FULL":
	                ((SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setNetworkSpeed(NetworkSpeed.FULL);
	                break;
	            case "GPRS":
	                ( (SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setNetworkSpeed(NetworkSpeed.GPRS);
	                break;
	            case "HSDPA":
	                ( (SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setNetworkSpeed(NetworkSpeed.HSDPA);
	                break;
	            case "LTE":
	                ( (SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setNetworkSpeed(NetworkSpeed.LTE);
	                break;
	            default:
	            	log.info("network speed not available");
	                break;
	        }
	    }
	    
	    /**
	     * SignalStrength Android
	     *This method is only available for emulator
	     * @param signalStrength signalStrength
	     */
	    public void signalStrength_android(String signalStrength) {
	        switch (signalStrength) {
	            case "GREAT":
	                ((SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setGsmSignalStrength(GsmSignalStrength.GREAT);
	                break;
	            case "MODERATE":
	                ((SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setGsmSignalStrength(GsmSignalStrength.MODERATE);
	                break;
	            case "NONE":
	                ( (SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setGsmSignalStrength(GsmSignalStrength.NONE_OR_UNKNOWN);
	                break;
	            default:
	            	log.info("Signal Strength not available");
	                break;
	        }
	    }
	    /**
	     * SignalStrength Android
	     *This method is only available for emulator
	     * @param voiceState voiceState
	     */
	    public void voiceState_android(String voiceState) {
	        switch (voiceState) {
	            case "UNREGISTERED":
	                ( (SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setGsmVoice(GsmVoiceState.UNREGISTERED);
	                break;
	            case "ROAMING":
	                ((SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setGsmVoice(GsmVoiceState.ROAMING);
	                break;
	            case "SEARCHING":
	                ( (SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setGsmVoice(GsmVoiceState.SEARCHING);
	                break;
	            default:
	            	log.info("Voice state not available");
	                break;
	        }
	    }
	    
	    /**
	     * SignalStrength Android
	     *This method is only available for emulator
	     * @param powerState powerState
	     */
	    public void powerState_android(String powerState) {
	        switch (powerState) {
	            case "ON":
	                ( (SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setPowerAC(PowerACState.ON);
	                break;
	            case "OFF":
	                ((SupportsSpecialEmulatorCommands) driverManager.getWiciDriver()).setPowerAC(PowerACState.OFF);
	                break;
	            default:
	            	log.info("power state not available");
	                break;
	        }
	    }
	    
	    /**
	     * Connection State
	     *
	     * @param connectionState connectionState
	     * @param enabled         boolean
	     */
	    public void connectionState_android(String connectionState, boolean enabled) {
	        switch (connectionState) {
	            case "AIRPLANE":
	                if (enabled) {
	                    ( (HasNetworkConnection) driverManager.getWiciDriver()).setConnection(new ConnectionState(ConnectionState.AIRPLANE_MODE_MASK)).isAirplaneModeEnabled();
	                }
	                ((HasNetworkConnection) driverManager.getWiciDriver()).setConnection(new ConnectionState(ConnectionState.AIRPLANE_MODE_MASK));
	                break;
	            case "DATA":
	                if (enabled) {
	                    ( (HasNetworkConnection) driverManager.getWiciDriver()).setConnection(new ConnectionState(ConnectionState.DATA_MASK)).isDataEnabled();
	                }
	                ( (HasNetworkConnection) driverManager.getWiciDriver()).setConnection(new ConnectionState(ConnectionState.DATA_MASK));
	                break;
	            case "WIFI":
	                if (enabled) {
	                    ( (HasNetworkConnection) driverManager.getWiciDriver()).setConnection(new ConnectionState(ConnectionState.WIFI_MASK)).isWiFiEnabled();
	                }
	                ( (HasNetworkConnection) driverManager.getWiciDriver()).setConnection(new ConnectionState(ConnectionState.WIFI_MASK));
	                break;
	            default:
	            	log.info("Connection state not available");
	                break;
	        }
	    }
	   
	    
	    /**
	     * Press Back
	     */
	    public void pressBack_android() {
	  	    	((PressesKey)(driverManager.getWiciDriver())).pressKey(new KeyEvent(AndroidKey.BACK));
	        log.info("Press Back");
	    }
	    
	    /**
	     * Swipe Down
	     */
	    public void swipeDown() {
	    	driverManager.getWiciDriver().executeScript("scroll", ImmutableMap.of("direction", "down"));
	    	log.info("Swipe Down");
	    }
	    
	    /**
	     * Swipe Up
	     */
	    public void swipeUP() {
	    	driverManager.getWiciDriver().executeScript("scroll", ImmutableMap.of("direction", "up"));
	    	log.info("Swipe Up");
	    }
	    
	    /**
	     * Accept Alert
	     */
	    public void acceptAlert() {
	    	driverManager.getWiciDriver().executeScript("acceptAlert");
	    	log.info("Accept Alert");
	    }
	    
	    /**
	     * Dismiss Alert
	     */
	    public void dismissAlert() {
	    	driverManager.getWiciDriver().executeScript("dismissAlert");
	    	log.info("Dismiss Alert");
	    }
	    
	    /**
	     * Longpress key
	     *
	     * @param username element
	     */
	    public void longPress(WebElement username) {
	        try {
	            fluentWait(username, 10); 
	            new Actions(driverManager.getWiciDriver()).clickAndHold(username).perform();
	            log.error("Log Press Element");
	        } catch (ElementNotVisibleException e) {
	            log.error("Element not visible", e);
	        }
	    }
	    
	    /**
	     * Hide keyboard
	     */
	    public void hideKeyboard() {
	    	((HidesKeyboard) driverManager.getWiciDriver()).hideKeyboard();
	    }
	    
	    public void clickBackButton() {
	    	driverManager.getWiciDriver().navigate().back(); //Closes keyboard
	    }
	    
	    /**
	     * Tap click
	     *
	     * @param x x axis
	     * @param y y axis
	     */
	    public void tapClick(int x, int y) {
	        TouchAction action = new TouchAction((PerformsTouchActions) driverManager.getWiciDriver());
	        action.tap(PointOption.point(x, y));
	        action.perform();
	    }
	    private void touchActions(int a1, int b1, int a2, int b2, int time) {
	        TouchAction touchAction = new TouchAction( (PerformsTouchActions) driverManager.getWiciDriver());
	        touchAction.press(PointOption.point(a1, b1)).
	                waitAction(WaitOptions.waitOptions(Duration.ofMillis(time))).
	                moveTo(PointOption.point(a2, b2)).release();
	        touchAction.perform();
	    }
	    
	    public void swipe(String direction, int count, int time) {
	        Dimension size =  driverManager.getWiciDriver().manage()
	                .window().getSize();
	        try {
	            switch (direction) {
	                case "left":
	                case "LEFT":
	                    for (int i = 0; i < count; i++) {
	                        int startx = (int) (size.width * 0.8);
	                        int endx = (int) (size.width * 0.20);
	                        int starty = size.height / 2;
	                        touchActions(startx, starty, endx, starty, time);
	                        log.info("Swipe Left");
	                    }
	                    break;
	                case "right":
	                case "RIGHT":
	                    for (int j = 0; j < count; j++) {
	                        int endx = (int) (size.width * 0.8);
	                        int startx = (int) (size.width * 0.20);
	                        int starty = size.height / 2;
	                        touchActions(startx, starty, endx, starty, time);
	                        log.info("Swipe Right");
	                    }
	                    break;
	                case "up":
	                case "UP":
	                    for (int j = 0; j < count; j++) {
	                        int starty = (int) (size.height * 0.80);
	                        int endy = (int) (size.height * 0.20);
	                        int startx = size.width / 2;
	                        touchActions(startx, starty, startx, endy, time);
	                        log.info("Swipe Up");
	                    }
	                    break;
	                case "down":
	                case "DOWN":
	                    for (int j = 0; j < count; j++) {
	                        int starty = (int) (size.height * 0.80);
	                        int endy = (int) (size.height * 0.20);
	                        int startx = size.width / 2;
	                        touchActions(startx, endy, startx, starty, time);
	                        log.info("Swipe Down");
	                    }
	                    break;
	                default:
	                    log.info("Direction not found");
	                    break;
	            }
	        } catch (Exception e) {
	            log.error("Not able to perform swipe operation", e);
	        }
	    }
	    public void rotateScreen(String rotation) {
	        if (rotation.equalsIgnoreCase("landscape")) {
	        	((Rotatable) driverManager.getWiciDriver()).rotate(ScreenOrientation.LANDSCAPE);
	        	log.info("Screen rotated in landscape");
	        } else {
	        	((Rotatable) driverManager.getWiciDriver()).rotate(ScreenOrientation.PORTRAIT);
	            log.info("Screen rotated in portrait");
	        }
	    }
	    
	    
	    /**
	     * tap to element for 250sec
	     *
	     * @param androidElement element
	     */
	    public void tapByElement(WebElement element) {
	        new TouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .tap(TapOptions.tapOptions().withElement(ElementOption.element(element)))
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
	    }
	    
	    /**
	     * Tap by coordinates
	     *
	     * @param x x
	     * @param y y
	     */
	    public void tapByCoordinates(int x, int y) {
	        new TouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .tap(PointOption.point(x, y))
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
	    }

	    /**
	     * Press by element
	     *
	     * @param element element
	     * @param seconds time
	     */
	    public void pressByElement(WebElement element, long seconds) {
	        new TouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .press(ElementOption.element(element))
	                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
	                .release()
	                .perform();
	    }

	    /**
	     * LongPress by element
	     *
	     * @param element element
	     * @param seconds time
	     */
	    public void longPressByElement(MobileElement element, long seconds) {
	        new TouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .longPress(ElementOption.element(element))
	                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
	                .release()
	                .perform();
	    }

	    /**
	     * Press by co-ordinates
	     *
	     * @param x       x
	     * @param y       y
	     * @param seconds time
	     */
	    public void pressByCoordinates(int x, int y, long seconds) {
	        new TouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .press(PointOption.point(x, y))
	                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
	                .release()
	                .perform();
	    }
	    
	    /**
	     * Horizontal swipe by percentage
	     *
	     * @param startPercentage  start
	     * @param endPercentage    end
	     * @param anchorPercentage anchor
	     */
	    public void horizontalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
	        Dimension size = driverManager.getWiciDriver().manage().window().getSize();
	        int anchor = (int) (size.height * anchorPercentage);
	        int startPoint = (int) (size.width * startPercentage);
	        int endPoint = (int) (size.width * endPercentage);
	        new TouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .press(PointOption.point(startPoint, anchor))
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
	                .moveTo(PointOption.point(endPoint, anchor))
	                .release().perform();
	    }

	    /**
	     * Veritical swipe by percentage
	     *
	     * @param startPercentage  start
	     * @param endPercentage    end
	     * @param anchorPercentage anchor
	     */
	    public void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
	        Dimension size = driverManager.getWiciDriver().manage().window().getSize();
	        int anchor = (int) (size.width * anchorPercentage);
	        int startPoint = (int) (size.height * startPercentage);
	        int endPoint = (int) (size.height * endPercentage);

	        new TouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .press(PointOption.point(anchor, startPoint))
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
	                .moveTo(PointOption.point(anchor, endPoint))
	                .release().perform();
	    }

	    /**
	     * Swipe by elements
	     *
	     * @param startElement start
	     * @param endElement   end
	     */
	    public void swipeByElements(WebElement startElement, WebElement endElement) {
	        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
	        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

	        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
	        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);

	        new TouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .press(PointOption.point(startX, startY))
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
	                .moveTo(PointOption.point(endX, endY))
	                .release().perform();
	    }

	    /**
	     * Multi touch by element
	     *
	     * @param androidElement element
	     */
	    public void multiTouchByElement(WebElement element) {
	        TouchAction press = new TouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .press(ElementOption.element(element))
	                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
	                .release();

	        new MultiTouchAction((PerformsTouchActions) driverManager.getWiciDriver())
	                .add(press)
	                .perform();
	    }


	    /**
	     * Get coordinate by id
	     *
	     * @param byId Byid
	     * @return point
	     */
	    public Point getCoordinates(WebElement element) {
	        Point location = element.getLocation();
	        System.out.println(location);
	        return location;
	    }
	    
	    /** .
	     * Download file is used to store 
	     *  in specific location
	     * 
	     * .. */
	    public static String download(String downloadURL) throws IOException
		{
		    URL website = new URL(downloadURL);
		    String fileName = getFileName(downloadURL);

		    try (InputStream inputStream = website.openStream())
		    {
		    	String currentDir = System.getProperty("user.dir")+ File.separator +"src"+ File.separator + "test"+ File.separator + "resources"+ File.separator + "apk" ;
		        Files.copy(inputStream, Paths.get(currentDir + File.separator +fileName), StandardCopyOption.REPLACE_EXISTING);
		        return currentDir + File.separator +fileName;
		    }
		}
	    
	    /** .
	     * getFileName method is to download file 
	     * from specific location
	     * .. */
		public static String getFileName(String downloadURL) throws UnsupportedEncodingException
		{
		    String baseName = FilenameUtils.getBaseName(downloadURL);
		    String extension = FilenameUtils.getExtension(downloadURL);
		    String fileName = baseName + "." + extension;

		    int questionMarkIndex = fileName.indexOf("?");
		    if (questionMarkIndex != -1)
		    {
		        fileName = fileName.substring(0, questionMarkIndex);
		    }

		    fileName = fileName.replaceAll("-", "");
		    return URLDecoder.decode(fileName, "UTF-8");
		}
		
		public void validateImageSrcWithDD (WebElement element,String expectImageURL) throws Exception {
			String actualUrlUI = element.getAttribute("src");
			System.out.println("actual img URL: "+actualUrlUI);
			
			 if(actualUrlUI.equals(expectImageURL)) {
					System.out.println("actual img URL: "+actualUrlUI+ "is validated with "+expectImageURL
							+"/n validation passed");

			 }else{
				 throw new ValidationException(
							"Image is not displayed");
			 }
	
		}	
}
