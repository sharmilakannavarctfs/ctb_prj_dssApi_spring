package com.ctfs.mobile.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ctfs.common.utils.ApplicationProperties;
@Component
public class ADB {

	@Autowired
	private ApplicationProperties applicationProperties;
	
	private final Logger log = LoggerFactory.getLogger(DriverManager.class);	
	
	public String getSdkPath() {
		return applicationProperties.getSdkPath();
	} 
	
	public String getAdbPath() {
		return getSdkPath() + "platform-tools" + File.separator + "adb";
	} 
	
	public String getEmulatorPath() {
		return getSdkPath() + File.separator +"tools"+ File.separator +"emulator";
	} 


	 public String command(String command) throws Exception {
		 log.debug("Formatting ADB Command: " + command);
	        if (command.startsWith("adb"))
	            command = command.replace("adb ", getSdkPath() + "/platform-tools/adb ");
	        else throw new Exception("This method is designed to run ADB commands only!");
	        log.debug("Formatted ADB Command: " + command);
	        String output = runCommand(command);
	       log.debug("Output of the ADB Command: " + output);
	        if (output == null) return "";
	        else return output.trim();
	    }
	 
	 public static String runCommand(String command) throws Exception {
	        String output = null;
	        try {
	            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
	            if (scanner.hasNext()) output = scanner.next();
	        } catch (IOException e) {
	            throw new Exception(e.getMessage());
	        }
	        return output;
	    }
	   	

	 	public void killServer() throws Exception {
	        command("adb kill-server");
	    }

	    public void startServer() throws Exception {
	        command("adb start-server");
	    }
	    
	    /**
	     * Get list of connected device
	     *
	     * @return devices
	     */
	    public List<Object> getConnectedDevices() throws Exception {
	        List<Object> devices = new ArrayList<>();
	        String output = command("adb devices");
	        for (String line : output.split("\n")) {
	            line = line.trim();
	            if (line.endsWith("device")) devices.add(line.replace("device", "").trim());
	        }
	        return devices;
	    }
	    
	    /**
	     * Get Foreground activity
	     *
	     * @return activity
	     */ 
	    public String getForegroundActivity() throws Exception {
	         return command("adb shell dumpsys window windows");
	     }
	    /**
	     * Get android version
	     *
	     * @return version
	     */
	    public String getAndroidVersionAsString() throws Exception {
	        String output = command("adb shell getprop ro.build.version.release");
	        if (output.length() == 3) output += ".0";
	        return output;
	    }
	    
	    public int getAndroidVersion() throws Exception {
	        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
	    }
	    
	    /**
	     * launch android emulator
	     *
	     * @param avdName emulator name
	     */
	    public void launchEmulator(String avdName) {
	    	log.info("Starting emulator for" + avdName + "....");
	        String[] aCommand = new String[]{getEmulatorPath(), "-avd", avdName};
	        try {
	            Process process = new ProcessBuilder(aCommand).start();
	            process.waitFor(180, TimeUnit.SECONDS);
	            log.info("Emulator launched successfully");
	        } catch (Exception e) {
	        	log.error(e.getMessage());
	        }
	    }

	    /**
	     * Close android emulator
	     */
	    public void closeEmulator() {
	    	log.info("Closing emulator");
	        String[] aCommand = new String[]{getEmulatorPath(), "emu", "kill"};
	        try {
	            Process process = new ProcessBuilder(aCommand).start();
	            process.waitFor(1, TimeUnit.SECONDS);
	            log.info("Emulator closed successfully");
	        } catch (Exception e) {
	        	log.error(e.getMessage());
	        }
	    }
	    
	    /**
	     * Get installed package
	     *
	     * @return packages
	     */
	    public List<Object> getInstalledPackages() throws Exception {
	        List<Object> packages = new ArrayList<>();
	        String[] output = command("adb shell pm list packages").split("\n");
	        for (String packageID : output) packages.add(packageID.replace("package:", "").trim());
	        return packages;
	    }

	    /**
	     * Open apps activity
	     *
	     * @param packageID  package
	     * @param activityID activity
	     */
	    public void openAppsActivity(String packageID, String activityID) throws Exception {
	        command("adb shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageID + "/" + activityID);
	        log.info("Open apps activity");
	    }

	    /**
	     * Clear apps data
	     *
	     * @param packageID package
	     */
	    public void clearAppsData(String packageID) throws Exception {
	        command("adb shell pm clear " + packageID);
	        log.info("Clear apps data " + packageID);
	    }

	    /**
	     * Force stop app
	     *
	     * @param packageID package
	     */
	    public void forceStopApp(String packageID) throws Exception {
	        command("adb shell am force-stop " + packageID);
	        log.info("Force stop app " + packageID);
	    }
	    /**
	     * Install app
	     *
	     * @param apkPath apk path
	     */
	    public void installApp(String apkPath) throws Exception {
	        command("adb install " + apkPath);
	        log.info("App installed from path " + apkPath);
	    }

	    /**
	     * Uninstall app
	     *
	     * @param packageID packageID
	     */
	    public void uninstallApp(String packageID) throws Exception {
	        command("adb uninstall " + packageID);
	        log.info("App uninstalled " + packageID);
	    }
	    public void rebootDevice() throws Exception {
	        command("adb reboot");
	    }

	    /**
	     * Get Device model
	     *
	     * @return device details
	     */
	    public String getDeviceModel() throws Exception {
	    	log.info("Get device model");
	        return command("adb shell getprop ro.product.model");
	    }

	    /**
	     * Get Device serial number
	     *
	     * @return serial number
	     */
	    public String getDeviceSerialNumber() throws Exception {
	    	log.info("Get device serial number");
	        return command("adb shell getprop ro.serialno");
	    }

	    /**
	     * Get Device carrier
	     *
	     * @return carrier
	     */
	    public String getDeviceCarrier() throws Exception {
	    	log.info("Get device carrier");
	        return command("adb shell getprop gsm.operator.alpha");
	    }

	    /**
	     * Get log process
	     *
	     * @return process
	     */
	    public List<Object> getLogcatProcesses() throws Exception {
	        String[] output = command("adb shell top -n 1").split("\n");
	        List<Object> processes = new ArrayList<>();
	        for (String line : output) {
	            processes.add(line.split(" ")[0]);
	            processes.removeAll(Arrays.asList("", null));
	        }
	        return processes;
	    }

}
