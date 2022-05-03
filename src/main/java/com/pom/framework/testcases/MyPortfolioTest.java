package com.pom.framework.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.framework.baseclasses.BasePageClass;
import com.pom.framework.baseclasses.BaseTestClass;
import com.pom.framework.pageclasses.LandingPage;
import com.pom.framework.pageclasses.MoneyPage;
import com.pom.framework.pageclasses.MyPortfolioPage;
import com.pom.framework.pageclasses.PortfolioLoginPage;
import com.pom.framework.pageclasses.PortfolioLogoutPage;
import com.pom.framework.utils.TestDataProvider;

public class MyPortfolioTest extends BaseTestClass {

	LandingPage landingPage;
	MoneyPage moneyPage;
	PortfolioLoginPage portfolioLoginPage;
	MyPortfolioPage myPortfolioPage;
	PortfolioLogoutPage portfolioLogoutPage;

	@Test(dataProvider = "getOpenPortfolioTestData", groups = { "Regression", "LoginTest" })
	public void openPortfolioTest(Hashtable<String, String> dataTable) {

		logger = report.createTest("Login Rediff Portfolio and Logout Test : " + dataTable.get("Comments"));

		loadProjectConfigProperties();
		invokeBrowser("browserName");
		logger.log(Status.INFO, "Invoked Browser : " + props.getProperty("browserName"));

		BasePageClass basePageClass = new BasePageClass(driver, logger);

		PageFactory.initElements(driver, basePageClass);

		landingPage = basePageClass.openApplication(props.getProperty("websiteURL"));

		moneyPage = landingPage.clickMoneyLink();

		portfolioLoginPage = moneyPage.clickSignInLink();

		myPortfolioPage = portfolioLoginPage.doLogin(dataTable.get("Email Address"), dataTable.get("Password"));

		myPortfolioPage.verifyMoneyBiz();

		myPortfolioPage.getTitle(dataTable.get("Page Title"));

		portfolioLogoutPage = myPortfolioPage.getTopMenu().clickSignOutLink();

		portfolioLoginPage = portfolioLogoutPage.clickLoginAgainLink();

	}

	@Test(enabled=false, dataProvider = "getAddPortfolioTestData", groups = { "Regression", "AddPortfolio" })
	public void addPortfolioTest(Hashtable<String, String> dataTable) {
		
		logger = report.createTest("Add Porfolio Test : " + dataTable.get("Comments"));
		
		loadProjectConfigProperties();
		invokeBrowser("browserName");
		logger.log(Status.INFO, "Invoked Browser : " + props.getProperty("browserName"));

		BasePageClass basePageClass = new BasePageClass(driver, logger);

		PageFactory.initElements(driver, basePageClass);

		landingPage = basePageClass.openApplication(props.getProperty("websiteURL"));

		moneyPage = landingPage.clickMoneyLink();

		portfolioLoginPage = moneyPage.clickSignInLink();

		myPortfolioPage = portfolioLoginPage.doLogin(dataTable.get("Email Address"), dataTable.get("Password"));

		myPortfolioPage.verifyMoneyBiz();
		waitForPageLoad();

		myPortfolioPage.clickCreatePortfolio();
		waitForPageLoad();

		myPortfolioPage.enterPortfolioName(dataTable.get("Portfolio Name"));

		myPortfolioPage = myPortfolioPage.submitPortfolio();
		waitForPageLoad();

		myPortfolioPage.isPortfolioExists(dataTable.get("Portfolio Name"));
		waitForPageLoad();
	}

	@Test(enabled=false, dataProvider = "getAddPortfolioTestData", groups = { "Regression", "DeletePortfolio" })
	public void deletePortfolio(Hashtable<String, String> dataTable) {
		logger = report.createTest("Delete Porfolio Test : " + dataTable.get("Comments"));

		loadProjectConfigProperties();
		invokeBrowser("browserName");
		logger.log(Status.INFO, "Invoked Browser : " + props.getProperty("browserName"));

		BasePageClass basePageClass = new BasePageClass(driver, logger);

		PageFactory.initElements(driver, basePageClass);

		landingPage = basePageClass.openApplication(props.getProperty("websiteURL"));

		moneyPage = landingPage.clickMoneyLink();

		portfolioLoginPage = moneyPage.clickSignInLink();

		myPortfolioPage = portfolioLoginPage.doLogin(dataTable.get("Email Address"), dataTable.get("Password"));

		myPortfolioPage.verifyMoneyBiz();
		waitForPageLoad();

		myPortfolioPage = myPortfolioPage.selectPortfolio(dataTable.get("Portfolio Name"));
		waitForPageLoad();

		myPortfolioPage = myPortfolioPage.deletePortFolio();
		waitForPageLoad();

		myPortfolioPage.isPortfolioDeleted(dataTable.get("Portfolio Name"));

	}

	@DataProvider
	public Object[][] getOpenPortfolioTestData() {
		return TestDataProvider.getTestData("Rediff_MoneyPortfolio_TestData_.xlsx", "Rediff Money Portfolio",
				"Open Portfolio Test");
	}

	@DataProvider
	public Object[][] getAddPortfolioTestData() {
		return TestDataProvider.getTestData("Rediff_MoneyPortfolio_TestData_.xlsx", "Rediff Money Portfolio",
				"Add Portfolio Test");
	}

}
