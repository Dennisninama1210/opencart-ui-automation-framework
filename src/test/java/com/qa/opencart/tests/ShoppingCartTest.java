package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ShoppingCartTest extends BaseTest {
	@BeforeClass
	public void shoppingCartSetUp() {
		accPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

//	@Test
//
//	public void shoppingCartHeaderTest() {
//		searchResultsPage = accPage.doSearch("macbook");
//		productInfoPage = searchResultsPage.selectProductSearch("MacBook Air");
//		shoppingCartPage = productInfoPage.addCartProduct();
//		String actHeader = shoppingCartPage.getShoppingCartHeader();
//		Assert.assertEquals(actHeader, "Shopping Cart  (25.00kg)");
//
//	}
//
	
       @Test

	public void updateQualityTest() {
		searchResultsPage = accPage.doSearch("iphone");
		productInfoPage = searchResultsPage.selectProductSearch("iPhone");
		shoppingCartPage = productInfoPage.addCartProduct("1");
		
		String actSuccessMsg =shoppingCartPage.updateQuantity("iPhone", "2");
		Assert.assertTrue(actSuccessMsg.contains("modified your shopping cart"));
	}

//	@Test
//
//	public void removeQualityTest() {
//		searchResultsPage = accPage.doSearch("macbook");
//		productInfoPage = searchResultsPage.selectProductSearch("MacBook Air");
//		shoppingCartPage = productInfoPage.addCartProduct("2");
//		shoppingCartPage.removeProduct("MacBook Air");
//	}

}
