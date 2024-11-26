package com.ctfs.common.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ctfs.ui.utils.DriverManager;
import com.ctfs.ui.utils.DriverWait;

@Lazy
@Component
public class KeyboardUtil {
	
	@Autowired
	private DriverManager driverManager;
	
	@Autowired
	private DriverWait driverWait;
	
	public void pageUp() throws Exception {
		try {
			Actions actions = new Actions(driverManager.getWebDriver());
			actions.sendKeys(Keys.PAGE_UP).build().perform();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex);
		}
	}

	public void pageDown() throws Exception {
		/// Scroll down the page
		try {
			Actions actions = new Actions(driverManager.getWebDriver());
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex);
		}
	}
	
	public void rightArrowClick() throws Exception
	{
		/// Scroll up the page
		Actions actions = new Actions(driverManager.getWebDriver());
		actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
		driverWait.waitUntilJSReady();
//		driverWait.waitForThrobber();
	}

}
