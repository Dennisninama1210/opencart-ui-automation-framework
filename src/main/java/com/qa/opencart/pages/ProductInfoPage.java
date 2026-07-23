package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.commonutlis.AppConstants;
import com.qa.opencart.commonutlis.ElementUtil;

public class ProductInfoPage {

	private final By header = By.tagName("h1");
	private final By images = By.xpath("//a[@class='thumbnail']");
	private final By addCartQty = By.id("input-quantity");
	private final By addCartButton = By.id("button-cart");
	private final By shoppingCartLink = By.linkText("shopping cart");
	private final By productMetadata = By.xpath("(//div[@id='content']//ul[@class=\"list-unstyled\"])[1]/li");
	private final By productPrice = By.xpath("(//div[@id='content']//ul[@class=\"list-unstyled\"])[2]/li");

	public Map<String, String> productInfo;

	private WebDriver driver;
	private ElementUtil elUtil;

	// public page class Constructor

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {

		String headerValue = elUtil.waitForElementVisibility(header, AppConstants.DEFAULT_SHORT_WAIT).getText();

		System.out.println("Product Info Page Header is : " + headerValue);

		return headerValue;
	}

	public int getProductImagesCount() {
		int imagesCount = elUtil.waitforElementsVisibility(images, AppConstants.DEFAULT_LONG_WAIT).size();

		System.out.println("Overall images of the selected product is : " + imagesCount);

		return imagesCount;
	}

	public ShoppingCartPage addCartProduct(String qyt) {

		WebElement el = elUtil.waitForElementVisibility(addCartQty, AppConstants.DEFAULT_SHORT_WAIT);
		el.clear();
		el.sendKeys(qyt);
		elUtil.doClick(addCartButton);
		WebElement cartLink = elUtil.waitForElementVisibility(shoppingCartLink, AppConstants.DEFAULT_SHORT_WAIT);
		cartLink.click();
		return new ShoppingCartPage(driver);
	}

	public void getProductMetadata() {

		List<WebElement> metaList = elUtil.waitforElementsVisibility(productMetadata, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Total size of meta data is :" + metaList.size());

		for (WebElement e : metaList) {
			String text = e.getText();

			String meta[] = text.split(":");

			String key = meta[0];
			String value = meta[1].trim();

			productInfo.put(key, value);
		}

	}

	public void getProductPriceData() {

		List<WebElement> priceList = elUtil.waitforElementsVisibility(productPrice, AppConstants.DEFAULT_SHORT_WAIT);

		System.out.println("Total size of price list is : " + priceList.size());

		String productPrice = priceList.get(0).getText();

		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();

		productInfo.put("Product Price", productPrice);

		productInfo.put("Excluding Tax Price", exTaxPrice);

	}
	
	public Map<String, String> getAllProductInfo() {

	    productInfo = new HashMap<>();

	    getProductMetadata();

	    getProductPriceData();

	    System.out.println("Product Info is : \n" + productInfo);

	    return productInfo;
	}

}
