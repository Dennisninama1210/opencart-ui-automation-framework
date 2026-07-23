package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass

	public void prodcutInfoSetUp() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductsData() {

		Object obj[][] = new Object[3][2];
		obj[0][0] = "macbook";
		obj[0][1] = "MacBook Pro";

		obj[1][0] = "samsung";
		obj[1][1] = "Samsung SyncMaster 941BW";

		obj[2][0] = "canon";
		obj[2][1] = "Canon EOS 5D";

		return obj;
	}

	@Test(dataProvider = "getProductsData")

	public void productHeaderTest(String searchValue, String productName) {

		searchResultsPage = accPage.doSearch(searchValue);
		productInfoPage = searchResultsPage.selectProductSearch(productName);
		String actualProductHeader = productInfoPage.getProductHeader();

		Assert.assertEquals(actualProductHeader, productName);
	}

	// old method run for one case
//		@Test
	//
//		public void productHeaderTestExCompleteMethod() {
	//
//			searchResultsPage = accPage.doSearch("macbook");
//			productInfoPage = searchResultsPage.selectProductSearch("MacBook Air");
//			String actualProductHeader = productInfoPage.getProductHeader();
//			Assert.assertEquals(actualProductHeader, "MacBook Air");
//		}

	@DataProvider
	public Object[][] getProductsImages() {

		Object obj[][] = { { "macbook", "MacBook Pro", 4 }, { "samsung", "Samsung SyncMaster 941BW", 1 },
				{ "canon", "Canon EOS 5D", 3 } };

		return obj;

	}

	@Test(dataProvider = "getProductsImages")

	public void productImagesCounts(String searchValue, String productName, int imageCount) {

		searchResultsPage = accPage.doSearch(searchValue);

		productInfoPage = searchResultsPage.selectProductSearch(productName);

		int actualImagesCount = productInfoPage.getProductImagesCount();

		Assert.assertEquals(actualImagesCount, imageCount);

	}

//	old method for only run the once case 
//	@Test
//
//	public void productImagesCounts() {
//
//		searchResultsPage = accPage.doSearch("canon");
//		productInfoPage = searchResultsPage.selectProductSearch("Canon EOS 5D");
//		int actualImagesCount = productInfoPage.getProductImagesCount();
//		Assert.assertEquals(actualImagesCount, 3);
//
//	}

//	@Test
//
//	public void addCartProduct() {
//
//		searchResultsPage = accPage.doSearch("macbook");
//		productInfoPage = searchResultsPage.selectProductSearch("MacBook Air");
//		productInfoPage.addCartProduct("1");	
//	}

	@Test

	public void productInfoTest() {

		//SoftAssert so = new SoftAssert();

		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProductSearch("MacBook Pro");
		Map<String, String> allProductInfo = productInfoPage.getAllProductInfo();

//		so.assertEquals(allProductInfo.get("Brand"), "Apple");
//		System.out.println("1");
//
//		so.assertEquals(allProductInfo.get("Availability"), "Out Of Stock7");
//		System.out.println("2");
//
//		so.assertEquals(allProductInfo.get("Product Price"), "$2,000.00");
//		System.out.println("3");
//
//		so.assertEquals(allProductInfo.get("Product Code"), "Product 18");
//		System.out.println("4");
//
//		so.assertEquals(allProductInfo.get("Reward Points"), "800");
//		System.out.println("5");
//
//		so.assertAll();	
	    Assert.assertEquals(allProductInfo.get("Brand"), "Apple");
	    System.out.println("1. Brand Assertion Completed");

	    Assert.assertEquals(allProductInfo.get("Availability"), "Out Of Stock7");
	    System.out.println("2. Availability Assertion Completed");

	    Assert.assertEquals(allProductInfo.get("Product Price"), "$2,000.00");
	    System.out.println("3. Product Price Assertion Completed");

	    Assert.assertEquals(allProductInfo.get("Product Code"), "Product 18");
	    System.out.println("4. Product Code Assertion Completed");

	    Assert.assertEquals(allProductInfo.get("Excluding Tax Price"), "$2,000.00");
	    System.out.println("5. Excluding Tax Price Assertion Completed");

	    Assert.assertEquals(allProductInfo.get("Reward Points"), "800");
	    System.out.println("6. Reward Points Assertion Completed");

	    System.out.println("End of Test Method");


	}

}
