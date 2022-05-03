package com.pom.framework.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.framework.baseclasses.BasePageClass;
import com.pom.framework.baseclasses.TopMenuClass;

public class MoneyPage extends BasePageClass {

	TopMenuClass topMenu;

	public MoneyPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		// this.driver = driver;
		// topMenu = PageFactory.initElements(driver, TopMenuClass.class);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	@FindBy(xpath = "//*[@id='signin_info']/a[1]")
	public WebElement signInLink;

	public PortfolioLoginPage clickSignInLink() {
		try {
			logger.log(Status.INFO, "Clicking Sign In Link from Money Page");
			signInLink.click();
			// logger.log(Status.PASS, "Successfully Clicked on Sign In Link from Money
			// Page");
			reportPass("Successfully Clicked on Sign In Link from Money Page");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
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
