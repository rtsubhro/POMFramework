package com.pom.framework.baseclasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.framework.utils.CaptureScreenshot;
import com.pom.framework.utils.ExtentReportManager;

public class BaseTestClass {

	public WebDriver driver;

	public Properties props;

	public ExtentReports report = ExtentReportManager.getReportInstance();

	public ExtentTest logger;

	String chromeDriverExec = System.getProperty("user.dir") + "\\resources\\drivers\\chromedriver.exe";

	String geckoDriverExec = System.getProperty("user.dir") + "\\resources\\drivers\\geckodriver.exe";

	String edgeDriverExec = System.getProperty("user.dir") + "\\resources\\drivers\\msedgedriver.exe";

	String projectConfigPropertiesPath = System.getProperty("user.dir")
			+ "\\resources\\objectrepository\\projectConfig.properties";

	public void loadProjectConfigProperties() {
		if (props == null) {
			props = new Properties();
		}

		try {
			props.load(new FileInputStream(projectConfigPropertiesPath));
		} catch (IOException e) {
			// reportFail(e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Invokes the Browser
	 * 
	 * @param browserNameKey
	 */
	public void invokeBrowser(String browserNameKey) {

		String browserName = props.getProperty(browserNameKey);
		try {
			switch (browserName.toLowerCase()) {

			case "chrome":
				System.setProperty("webdriver.chrome.silentOutput", "true");
				System.setProperty("webdriver.chrome.driver", chromeDriverExec);
				ChromeOptions chromeOptions = new ChromeOptions();
				//chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
				chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				chromeOptions.addArguments("--disable-notifications");
				driver = new ChromeDriver(chromeOptions);
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", geckoDriverExec);
				driver = new FirefoxDriver();
				break;
			default:
				System.setProperty("webdriver.edge.driver", edgeDriverExec);
				driver = new EdgeDriver();
				break;
			}
		} catch (Exception e) {
			// reportFail(e.getMessage());
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	}

	/**
	 * Wait Function in Framework
	 */
	public void waitForPageLoad() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		int i = 0;

		while (i != 180) {

			String pageState = (String) jse.executeScript("return document.readyState;");

			if ("complete".equals(pageState)) {
				break;
			} else {
				waitLoad(1);
				i++;
			}
		}

		waitLoad(2);

		i = 0;

		while (i != 180) {
			boolean jsState = (boolean) jse.executeScript("return window.jQuery != undefined && jQuery.active == 0;");

			if (jsState) {
				break;
			} else {
				waitLoad(1);
				i++;
			}
		}
	}

	/**
	 * Wait Function in Framework
	 * 
	 * @param seconds
	 */
	public void waitLoad(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Select Date in Calendar
	 * 
	 * @param date
	 */
	public void selectDateinCalendar(String date) {
		Date currentDate = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		try {
			Date expectedDate = sdf.parse(date);

			String day = new SimpleDateFormat("dd").format(expectedDate);
			System.out.println("Day is : " + day);
			String month = new SimpleDateFormat("MMMM").format(expectedDate);
			System.out.println("Month is : " + month);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);
			System.out.println("Year is : " + year);

			String monthAndYear = month + " " + year;
			System.out.println("MonthAndYear is : " + monthAndYear);

			while (true) {
				String displayedDate = driver.findElement(By.xpath("//div[@class=\"dpTitleText\"]")).getText();

				if (monthAndYear.equals(displayedDate)) {
					WebElement dateInPicker = driver.findElement(By.xpath(
							"//div[@id=\"datepicker\"]/table[@class=\"dpTable\"]/tbody/tr[@class=\"dpTR\"]/td[@class=\"dpTD\" and text()="
									+ day + "]"));
					dateInPicker.click();

					break;
				} else if (expectedDate.compareTo(currentDate) > 0) {
					WebElement monthForwardArrow = driver.findElement(By.xpath(
							"//div[@id=\"datepicker\"]/table[@class=\"dpTable\"]/tbody/tr[@class=\"dpTitleTR\"]/td[@class=\"dpButtonTD\"][3]"));
					monthForwardArrow.click();
				} else {
					WebElement monthBackwardArrow = driver.findElement(By.xpath(
							"//div[@id=\"datepicker\"]/table[@class=\"dpTable\"]/tbody/tr[@class=\"dpTitleTR\"]/td[@class=\"dpButtonTD\"][2]"));
					monthBackwardArrow.click();
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void SetTestResult(ITestResult result) {

		String screenShotPath = CaptureScreenshot.takeScreenShot(driver,
				CaptureScreenshot.generateScreenshotFileName(result));

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, result.getName());
			logger.log(Status.FAIL, result.getThrowable());
			logger.fail("Screen Shot: " + logger.addScreenCaptureFromPath(screenShotPath));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, result.getName());
			logger.pass("Screen Shot: " + logger.addScreenCaptureFromPath(screenShotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.skip("Test Case : " + result.getName() + " has been skipped");
		}

		report.flush();
		driver.close();
	}

	@AfterTest
	public void quitBrowser() {
		driver.quit();
	}
}
