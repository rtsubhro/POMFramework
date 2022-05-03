package com.pom.framework.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.framework.baseclasses.BasePageClass;
import com.pom.framework.baseclasses.TopMenuClass;

public class PortfolioLoginPage extends BasePageClass {

	TopMenuClass topMenu;

	public PortfolioLoginPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		// this.driver = driver;
		// topMenu = PageFactory.initElements(driver, TopMenuClass.class);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	@FindBy(id = "useremail")
	WebElement userEmailTextbox;

	@FindBy(id = "userpass")
	WebElement passwordTextbox;

	@FindBy(id = "loginsubmit")
	WebElement loginSubmitBtn;

	public MyPortfolioPage doLogin(String userEmail, String password) {
		try {
			logger.log(Status.INFO, "Entering User Email : " + userEmail + " in Portfolio Login Page");
			userEmailTextbox.sendKeys(userEmail);
			// logger.log(Status.PASS, "Successfully entered User Email : " + userEmail + "
			// in Portfolio Login Page");
			reportPass("Successfully entered User Email : " + userEmail + " in Portfolio Login Page");
			logger.log(Status.INFO, "Entering Password : " + password + " in Portfolio Login Page");
			passwordTextbox.sendKeys(password);
			// logger.log(Status.PASS, "Successfully entered Password : " + password + " in
			// Portfolio Login Page");
			reportPass("Successfully entered Password : " + password + " in Portfolio Login Page");
			loginSubmitBtn.click();
			reportPass("Successfully clicked Login Submit button in Portfolio Login Page");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		/*
		 * try { Thread.sleep(30000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		waitForPageLoad();
		String currentPageTitle = driver.getTitle();
		if (currentPageTitle.equals("Indian stock markets: Login to Portfolio")) {
			// Assert.fail("Login Failed");
			reportFail("Login Failed for Money Portfolio");
		}

		// return PageFactory.initElements(driver, MyPortfolioPage.class);
		MyPortfolioPage myPortfolioPage = new MyPortfolioPage(driver, logger);
		PageFactory.initElements(driver, myPortfolioPage);
		return myPortfolioPage;
	}

	public TopMenuClass getTopMenu() {
		return this.topMenu;
	}

}
