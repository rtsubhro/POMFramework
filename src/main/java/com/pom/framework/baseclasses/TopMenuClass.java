package com.pom.framework.baseclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.framework.pageclasses.PortfolioLogoutPage;

public class TopMenuClass extends BasePageClass {

	public TopMenuClass(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		// this.driver = driver;
	}

	@FindBy(xpath = "//*[@id=\"wrapper\"]/div[2]/ul/li[2]/a")
	WebElement myPortfolioLink;

	@FindBy(xpath = "//*[@id=\"signin_info\"]/a")
	WebElement signOutLink;

	public PortfolioLogoutPage clickSignOutLink() {
		try {
			logger.log(Status.INFO, "Clicking Sign Out Link from Top Menu");
			signOutLink.click();
			// logger.log(Status.PASS, "Successfully clicked Sign Out Link from Top Menu");
			reportPass("Successfully clicked Sign Out Link from Top Menu");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		/*
		 * try { Thread.sleep(30000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		//waitForPageLoad();

		// return PageFactory.initElements(driver, PortfolioLogoutPage.class);
		PortfolioLogoutPage logoutPage = new PortfolioLogoutPage(driver, logger);
		PageFactory.initElements(driver, logoutPage);
		return logoutPage;
	}

}
