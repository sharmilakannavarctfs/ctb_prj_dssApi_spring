package com.ctfs.common.externalutils;


import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ctfs.common.utils.Constants;

@Component
public class ExternalDriverManager {
	private static final ThreadLocal < Boolean > isMobile =new ThreadLocal<>();
    private static final ThreadLocal < WebDriver > webDriverThreadLocal = new ThreadLocal <> ( );
    private final Logger log = LoggerFactory.getLogger ( ExternalDriverManager.class );
    
    
    public void createExternalWebDriver (String browser, boolean headless) throws Exception {
  		switch (browser) {
              case ("chrome"):
            	  ChromeOptions cropts = new ChromeOptions ( );
              ChromeDriverService service = new ChromeDriverService.Builder()
                      .usingDriverExecutable(new File(Constants.DRIVER_DIRECTORY+"/chromedriver.exe"))
                      .usingAnyFreePort()
                      .build();
                  cropts = buildChromeOptions(false, headless);
                  webDriverThreadLocal.set ( new ChromeDriver (service, cropts ) );
                  System.out.println("Setting mobile =false");
                  isMobile.set(false);
                  break;
  		}}
    
    
    private ChromeOptions buildChromeOptions(boolean mobile, boolean headless) throws Exception {
        log.info("Building Chrome options..");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", System.getProperty("downloadDirectory"));
        //chromePrefs.put("profile.default_content_settings.popups", 1);

       ChromeOptions _options = new ChromeOptions();
        if(mobile) {
            log.info("Building mobile simulator capabilities..");
            throw new RuntimeException("Mobile browser not yet supported");
//            _options = buildSimulatorCapabilities();
        }
        //_options.setExperimentalOption("prefs", chromePrefs);
        //_options.setPageLoadStrategy(PageLoadStrategy.NONE);
        _options.setAcceptInsecureCerts(true);
        _options.addArguments("--acceptSslCerts");
        _options.addArguments("--ignore-certificate-errors");
        _options.addArguments("--start-maximized");
        _options.addArguments("--enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        _options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        _options.addArguments("--disable-features=VizDisplayCompositor");
        _options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        _options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        //_options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        //_options.addArguments("--disable-gpu");
        _options.setAcceptInsecureCerts(true);
        if (headless) {
            _options.addArguments("--headless");
            _options.addArguments("--disable-gpu");
            _options.addArguments("window-size=1296, 696");
        }
        log.info("Returning Chrome options");
        return _options;
    } 
    
   
   
    public synchronized   WebDriver getExternalWebDriver ( ) {
        return webDriverThreadLocal.get ( );
    }
   
    public synchronized Boolean isMobile() {
    	return isMobile.get();
    }
   
    public JavascriptExecutor getJSExecutor ( ) {
        return (JavascriptExecutor) getExternalWebDriver();
    }
    
}
