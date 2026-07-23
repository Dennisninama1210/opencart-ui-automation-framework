package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.commonutlis.AppConstants;
import com.qa.opencart.commonutlis.ElementUtil;

public class RegistrationSuccessPage {
	
	private final By successMsg = By.cssSelector("div#content h1");

	private final By logoutLink = By.linkText("Logout");


	private WebDriver driver;
	private ElementUtil elUtil;

	// Public page class constructor :

	public RegistrationSuccessPage(WebDriver driver) {

		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	public String getSuccessMessage() {
		return elUtil.waitForElementVisibility(successMsg, AppConstants.DEFAULT_SHORT_WAIT).getText();
	}

	// Action: Click Logout
	public LogoutSuccessPage clickLogout() {
		elUtil.doClick(logoutLink);
		return new LogoutSuccessPage(driver);
	}

}

