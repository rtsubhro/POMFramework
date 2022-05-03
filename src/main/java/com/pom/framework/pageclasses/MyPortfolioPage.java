package com.pom.framework.pageclasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.framework.baseclasses.BasePageClass;
import com.pom.framework.baseclasses.TopMenuClass;

public class MyPortfolioPage extends BasePageClass {

	TopMenuClass topMenu;

	public MyPortfolioPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		// this.driver = driver;
		// topMenu = PageFactory.initElements(driver, TopMenuClass.class);
		topMenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topMenu);
	}

	public TopMenuClass getTopMenu() {
		return this.topMenu;
	}

	@FindBy(xpath = "//*[@id=\"headcontent\"]/div[1]/div[1]/a/span")
	WebElement moneyWiz_text;

	@FindBy(id = "createPortfolio")
	public WebElement createPortfolio_Btn;

	@FindBy(id = "create")
	public WebElement createportfolio_textbox;

	@FindBy(id = "createPortfolioButton")
	public WebElement submitCreatePortfolio_Btn;

	@FindBy(id = "portfolioid")
	public WebElement myPortfolioList;

	@FindBy(id = "deletePortfolio")
	public WebElement deletePortfolio_Btn;

	public MyPortfolioPage deletePortFolio() {
		try {
			deletePortfolio_Btn.click();
			acceptAlert();
			logger.log(Status.PASS, "Deleted the Portfolio");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		MyPortfolioPage myportfolio = new MyPortfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;
	}

	public MyPortfolioPage selectPortfolio(String Value) {
		selectDropDownValue(myPortfolioList, Value);
		MyPortfolioPage myportfolio = new MyPortfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;
	}

	public void isPortfolioExists(String portfolio) {
		boolean flag = false;
		try {
			List<WebElement> allOptions = getAllElementsOfDropDown(myPortfolioList);
			for (WebElement option : allOptions) {
				if (option.getText().equalsIgnoreCase(portfolio)) {
					flag = true;
				} else {
					flag = false;
				}
			}
			Assert.assertTrue(flag);
			logger.log(Status.PASS, "Given Portfolio : " + portfolio + " , is present in Portfolio List");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void isPortfolioDeleted(String portfolio) {
		boolean flag = false;
		try {
			List<WebElement> allOptions = getAllElementsOfDropDown(myPortfolioList);
			for (WebElement option : allOptions) {
				if (!option.getText().equalsIgnoreCase(portfolio)) {
					flag = true;
				} else {
					flag = false;
				}
			}
			Assert.assertTrue(flag);
			logger.log(Status.PASS, "Given Portfolio : " + portfolio + " , is not present in Portfolio List");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public MyPortfolioPage submitPortfolio() {
		try {
			submitCreatePortfolio_Btn.click();
			logger.log(Status.PASS, "Submitted the Portfolio");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		MyPortfolioPage myportfolio = new MyPortfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;

	}

	public void enterPortfolioName(String portfolioName) {
		try {
			createportfolio_textbox.clear();
			createportfolio_textbox.sendKeys(portfolioName);
			logger.log(Status.PASS, "Entered Portfolio Name : " + portfolioName);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void clickCreatePortfolio() {
		try {
			createPortfolio_Btn.click();
			logger.log(Status.PASS, "Clicked the Create Portfolio Button");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void verifyMoneyBiz() {
		moneyWiz_text.isDisplayed();
	}

}
