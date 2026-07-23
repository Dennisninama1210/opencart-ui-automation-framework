package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LogoutSuccessPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.RegistrationSuccessPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;

//@Listeners({ChainTestListener.class,TestAllureListener.class})
public class BaseTest {

	public DriverFactory df;
	public WebDriver driver;
	public LoginPage loginpage;
	public AccountPage accPage;
	public SearchResultsPage searchResultsPage;
	public ProductInfoPage productInfoPage;
	public ShoppingCartPage shoppingCartPage;
	public Properties prop;
	public RegisterPage registerPage;
	public RegistrationSuccessPage registrationSuccessPage;
	public LogoutSuccessPage logoutSuccessPage;
	

	@Parameters({"browser"})
	
	@BeforeTest

	public void setUp(@Optional("chrome") String browserName) {

		df = new DriverFactory();
		prop=df.initProp();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);

	}
	
	@AfterMethod
	
	public void attachScreenshot(ITestResult result) {
		
		if(!result.isSuccess()) {
			
			ChainTestListener.embed(DriverFactory.getScreenshotAsFile(), "image/png");
		}
	}
	
	@AfterTest
	public void tearDown() {
	
	driver.close();	
		
	}

}
