package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.SearchResultsPage;

public class SearchProductTest extends BaseTest {

	@BeforeClass

	public void searchProdcutSetUp() {
		accPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));

	}

	@Test

	public void searchProductTest() {

		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProductSearch("MacBook Pro");
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, "MacBook Pro");

	}

}
