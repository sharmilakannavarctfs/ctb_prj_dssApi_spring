package com.ctfs.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Value("${api.url.value}")
    private String apiAppUrl;
    @Value("${dss.api.url}")
    private String dssApiUrl;
    @Value("${dss.api.url.dev}")
    private String dssApiUrlDev;
	@Value("${dss.api.url.qa}")
    private String dssApiUrlQa;
    
	@Value("${web.url.value}")
    private String webAppUrl;
    @Value("${web.pega.url}")
    private String webPegaUrl;
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
    
    @Value("${dash.url.value}")
    private String dashUrl;
    @Value("${RunMode}")
    private String runMode;
    @Value("${BypassCache}")
    private String BypassCache;
    @Value("${Environment}")
    private String environment;
    
    public String getBypassCache() {
		return BypassCache;
	}

	public void setBypassCache(String bypassCache) {
		this.BypassCache = bypassCache;
	}
	public String getEnvironment() {
		return environment;
	}
	
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public String getDashUrl() {
		return dashUrl;
	}
	
	public void setDashUrl(String dashUrl) {
		this.dashUrl = dashUrl;
	}
	public String getWebPegaUrl() {
		return webPegaUrl;
	}

	public void setWebPegaUrl(String dashUrl) {
		this.webPegaUrl = webPegaUrl;
	}

	public String getRunMode() {
		return runMode;
	}

	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}

    
//    public String getDssApiUrl() {
//		return dssApiUrl;
//	}
	
	public String getDssApiUrl() {
		if(getEnvironment().equals("DEV"))
		return dssApiUrlDev;
		else if(getEnvironment().equals("QA"))
			return dssApiUrlQa;
		return null;
	}

	public void setDssApiUrl(String dssApiUrl) {
		this.dssApiUrl = dssApiUrl;
	}
	
	public String getDssApiUrlDev() {
		return dssApiUrlDev;
	}

	public void setDssApiUrlDev(String dssApiUrlDev) {
		this.dssApiUrlDev = dssApiUrlDev;
	}

	public String getDssApiUrlQa() {
		return dssApiUrlQa;
	}

	public void setDssApiUrlQa(String dssApiUrlQa) {
		this.dssApiUrlQa = dssApiUrlQa;
	}

	public String getAppPackage(){return appPackage;}
    
    public String getAppActivity(){return appActivity;}
    
    public String getSdkPath(){return sdkPath;}

    public String getAppMode(){return appMode;}
    
    public String getAppLanguage(){return appLanguage;}
    
    public String getIsAppiumRemote(){return isAppiumRemote;}

    public String getDeviceName(){return deviceName;}
    public String getFullPathAppName(){return appName;}

    public String getApiAppUrl() {return apiAppUrl;}
   
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