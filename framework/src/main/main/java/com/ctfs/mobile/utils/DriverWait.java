package com.ctfs.mobile.utils;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctfs.common.utils.ApplicationProperties;


@Component
public class DriverWait {

    private static final ThreadLocal < Wait < RemoteWebDriver > > driverWaitThreadLocal = new ThreadLocal <> ( );

    private final DriverManager driverManager;


    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    public DriverWait (DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public Wait < RemoteWebDriver > getDriverWait ( ) {
        return driverWaitThreadLocal.get ( );
    }

    public ThreadLocal < Wait < RemoteWebDriver > > getDriverWaitThreadLocal ( ) {
        return driverWaitThreadLocal;
    }

    public  WebDriverWait getWebDriverWait(){
        return new WebDriverWait ( driverManager.getWiciDriver(), applicationProperties.getWaitTimeout () );
    }
}
