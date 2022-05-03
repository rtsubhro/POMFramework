package com.pom.framework.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.framework.baseclasses.BasePageClass;
import com.pom.framework.baseclasses.TopMenuClass;

public class PortfolioLogoutPage extends BasePageClass {

	TopMenuClass topMenu;

	public PortfolioLogoutPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		// this.driver = driver;
		// topMenu = PageFactory.initElements(driver, TopMenuClass.class);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	@FindBy(xpath = "//*[@id=\"wrapper\"]/div[4]/a")
	WebElement loginAgainLink;

	public PortfolioLoginPage clickLoginAgainLink() {
		try {
			logger.log(Status.INFO, "Clicking Login Again Link on Portfolio Logout Page");
			loginAgainLink.click();
			// logger.log(Status.PASS, "Successfully Clicked Login Again Link on Portfolio
			// Logout Page");
			reportPass("Successfully Clicked Login Again Link on Portfolio Logout Page");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		/*
		 * try { Thread.sleep(30000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		//waitForPageLoad();

		// return PageFactory.initElements(driver, PortfolioLoginPage.class);
		PortfolioLoginPage portfolioLoginPage = new PortfolioLoginPage(driver, logger);
		PageFactory.initElements(driver, portfolioLoginPage);
		return portfolioLoginPage;
	}

	public TopMenuClass getTopMenu() {
		return this.topMenu;
	}

}
