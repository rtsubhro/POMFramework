package com.pom.framework.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.framework.baseclasses.BasePageClass;

public class LandingPage extends BasePageClass {

	/**
	 * All Web Elements of Landing Page and Associated Operations
	 */

	// Open Browser Code
	// WebElements
	// Operations on WebElements

	/************** Challenges in POM *********/
	// How to share WebDriver instance across Page Classes --> Resolved by
	// PageFactory
	// How to handle common operations/methods across Page Classes
	// How to handle different Page return types in operations
	// How to handle common WebElements across Page Classes

	/*
	 * public void openBrowser() { System.setProperty("webdriver.chrome.driver",
	 * System.getProperty("user.dir") + "\\resources\\drivers\\chromedriver.exe");
	 * driver = new ChromeDriver(); }
	 * 
	 * public void openURL() { driver.get("https://www.rediff.com"); }
	 */

	public LandingPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
	}

	@FindBy(xpath = "//*[contains(@class,'moneyicon')]")
	public WebElement moneyLink;

	public MoneyPage clickMoneyLink() {
		try {
			logger.log(Status.INFO, "Clicking Money Link Icon on Landing Page");
			moneyLink.click();
			// logger.log(Status.PASS, "Successfully clicked Money Link Icon on Landing
			// Page");
			reportPass("Successfully clicked Money Link Icon on Landing Page");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		/*
		 * try { Thread.sleep(5000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		//waitForPageLoad();
		// return PageFactory.initElements(driver, MoneyPage.class);
		MoneyPage moneyPage = new MoneyPage(driver, logger);
		PageFactory.initElements(driver, moneyPage);
		return moneyPage;
	}

	@FindBy(xpath = "//*[@id=\\\"signin_info\\\"]/a[1]")
	public WebElement signInLink;

	public LoginPage clickSignIn() {
		try {
			// Perform the click Sign In operation
			logger.log(Status.INFO, "Clicking Sign In Link from the Landing Page");
			signInLink.click();
			// logger.log(Status.PASS, "Successfully Clicked Sign In Link from the Landing
			// Page");
			reportPass("Successfully Clicked Sign In Link from the Landing Page");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		waitForPageLoad();
		// return PageFactory.initElements(driver, LoginPage.class);
		LoginPage loginPage = new LoginPage(driver, logger);
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}

}
