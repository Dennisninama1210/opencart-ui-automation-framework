package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.commonutlis.AppConstants;
import com.qa.opencart.commonutlis.ElementUtil;

public class ShoppingCartPage {

	private final By header = By.tagName("h1");

	// *** CHANGED: Quantity locator is now dynamic ***
	private By quantity(String productName) {
		return By.xpath("//a[text()='"+ productName +"']/ancestor::tr[1]//input");
	}

	// *** CHANGED: Update button locator is now dynamic ***
	private By updateBtn(String productName) {
		return By.xpath("//a[text()='" + productName + "']/ancestor::tr//button[@data-original-title='Update']");
	}
	
	private final By successMsg = By.xpath("//div[contains(@class,'alert-success')]");

	// *** CHANGED: Remove button locator is now dynamic ***
	private By removeBtn(String productName) {
		return By.xpath("//a[text()='" + productName + "']/ancestor::tr//button[@data-original-title='Remove']");
	}

	private WebDriver driver;
	private ElementUtil elUtil;

	// Constructor
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	public String getShoppingCartHeader() {

		String headerValue = elUtil.waitForElementPresence(header, AppConstants.DEFAULT_SHORT_WAIT).getText();

		System.out.println("Shopping Cart Page Header is : " + headerValue);

		return headerValue;
	}

	// *** CHANGED: productName parameter added ***
	public String updateQuantity(String productName, String qty) {

		elUtil.doClear(quantity(productName));
		elUtil.doSendKeys(quantity(productName), qty);
		elUtil.doClick(updateBtn(productName));
		String actualMsg = elUtil.waitForElementVisibility(successMsg, AppConstants.DEFAULT_SHORT_WAIT).getText();
		
	    System.out.println("Shopping Cart Success Message is: " + actualMsg);

	    return actualMsg;
	}

	// *** CHANGED: productName parameter added ***
	public void removeProduct(String productName) {

		elUtil.doClick(removeBtn(productName));
	}

}