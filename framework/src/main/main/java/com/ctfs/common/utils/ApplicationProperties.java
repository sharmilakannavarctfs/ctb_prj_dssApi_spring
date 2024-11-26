package com.ctfs.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Value("${api.url.value}")
    private String apiAppUrl;
    @Value("${web.url.value}")
    private String webAppUrl;
    @Value("${appMode}")
    private String appMode;
    @Value("${browser}")
    private String browser;
    @Value("${selenium.gridUrl}")
    private String seleniumGridUrl;
    @Value("${appium.gridUrl}")
    private String appiumGridUrl;
    @Value ( "${appium.app}" )
    private  String appName;
    @Value ( "${application.timeout}" )
    private int waitTimeout;
    @Value ( "${appium.deviceName}" )
    private String deviceName;
    @Value("${appium.remote}")
    private String isAppiumRemote;
    @Value("${appLang}")
    private String appLanguage; 
    @Value("${SDKPath}")
    private String sdkPath;
    @Value("${AppActivity}")
    private String appActivity;
    @Value("${AppPackage}")
    private String appPackage;
    
    public String getAppPackage(){return appPackage;}
    
    public String getAppActivity(){return appActivity;}
    
    public String getSdkPath(){return sdkPath;}

    public String getAppMode(){return appMode;}
    
    public String getAppLanguage(){return appLanguage;}
    
    public String getIsAppiumRemote(){return isAppiumRemote;}

    public String getDeviceName(){return deviceName;}
    public String getFullPathAppName(){return appName;}

    public int getWaitTimeout(){return waitTimeout;}

    public String getWeatherAppUrl() {return apiAppUrl;}
    public void setApiAppUrl(String weatherAppUrl) {
        this.apiAppUrl = weatherAppUrl;
    }
    public String getWebUrl() {return webAppUrl;}
    public void setWebUrl(String webUrl) {
        this.webAppUrl = webUrl;
    }
    public String getBrowser() {
        return browser;
    }
    public void setBrowser(String browser) {
        this.browser = browser;
    }
    public String getSeleniumGridUrl() {return seleniumGridUrl;}

    public String getAppiumGridUrl(){return appiumGridUrl;}
}