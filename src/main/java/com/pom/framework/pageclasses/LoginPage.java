package com.pom.framework.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.pom.framework.baseclasses.BasePageClass;

public class LoginPage extends BasePageClass {

	/**
	 * WebElements of Login Page Associated Operations on Login Page
	 */

	public LoginPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
	}

	public RediffMailPage doLogin() {
		// do Login Operation code

		// if Login successful
		// return new RediffMailPage();
		//return PageFactory.initElements(driver, RediffMailPage.class);
		RediffMailPage rediffMailPage = new RediffMailPage(driver, logger);
		PageFactory.initElements(driver, rediffMailPage);
		return rediffMailPage;
		// else
		// return new LoginPage();
	}

}
