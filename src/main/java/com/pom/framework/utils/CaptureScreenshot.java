package com.pom.framework.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class CaptureScreenshot {

	/************* Capture Screenshot **************/
	public static String takeScreenShot(WebDriver driver, String screenName) {

		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;

		File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);

		/*
		 * String destFilePath = System.getProperty("user.dir") +
		 * "/test-output/extent-reports/screenshots/" + DateUtils.getTimeStamp() +
		 * ".png";
		 */

		String destFilePath = System.getProperty("user.dir") + "/test-output/extent-reports/screenshots/" + screenName
				+ ".png";
		File destFile = new File(destFilePath);

		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// logger.addScreenCaptureFromPath(destFilePath);
		
		return destFilePath;
	}

	public static String generateScreenshotFileName(ITestResult result) {
		String fileName = result.getName() + "_" + DateUtils.getTimeStamp();

		return fileName;
	}

}
