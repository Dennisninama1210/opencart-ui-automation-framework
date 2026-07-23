package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.commonutlis.AppConstants;
import com.qa.opencart.commonutlis.ElementUtil;

public class AccountPage {

	// POM : Page Object Model :

	// Every page will have three things :

	// 1. private By locators;
	// 2. public page class constructor
	// 3. page class action methods :

	// private By locators :

	private final By headers = By.tagName("h2"); // Multiple Headers
	private final By logoutLink = By.linkText("Logout"); // Logout Link
	private final By searchIcon = By.xpath("//div[@id='search']//button");
	private final By searchBar = By.name("search");

	private WebDriver driver;
	private ElementUtil elUtil;

	// public page class Constructor
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	public List<String> getAccPageHeader() {

		return elUtil.getElementsTextList(headers);
	}

	public boolean isLogOutLinkExist() {

		boolean flag = elUtil.isElementDisplayed(logoutLink);

		return flag;
	}

	public SearchResultsPage doSearch(String searchValue) {
		System.out.println("Product to be searched is : " + searchValue);

		WebElement el=elUtil.waitForElementPresence(searchBar, AppConstants.DEFAULT_SHORT_WAIT);
		el.clear();
		el.sendKeys(searchValue);
		elUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);

	}

}
