package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.commonutlis.AppConstants;
import com.qa.opencart.commonutlis.ElementUtil;

public class SearchResultsPage {

	private final By searchResults = By.cssSelector("div.product-thumb");
	private final By resultsHeader = By.tagName("h1");

	private WebDriver driver;
	private ElementUtil elUtil;

	// public page class Constructor

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	public int getSearchResultsCount() {

		int count = elUtil.waitforElementsPresence(searchResults, AppConstants.DEFAULT_SHORT_WAIT).size();

	    System.out.println("Total Searched products are : " + count);
	    
	    return count;

	}
	
	public String  getResultHeaderValue() {
		
		String headerValue = elUtil.doElementGetText(resultsHeader);
		
	    System.out.println("Search Results header is : " + headerValue);
         
	    return headerValue;
		
	}
	
	
	public ProductInfoPage selectProductSearch(String productName) {
		
	    System.out.println("Product to be selected is  : " + productName);
	    
	    elUtil.doClick(By.linkText(productName));
	    
		return new ProductInfoPage(driver);

		
	}
	
}
