package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.commonutlis.AppConstants;
import com.qa.opencart.commonutlis.CsvUtil;
import com.qa.opencart.commonutlis.ExcelUtil;
import com.qa.opencart.commonutlis.StringUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void goToRegisterPage() {
		registerPage = loginpage.navigateToRegisterPage();
	}

//	@DataProvider
//
//	public Object[][] getUserRegistration() {
//		Object obj[][] = { { "rahul", "Mehta", "9898989890", "Admin@123", "Admin@123", "Yes" },
//
//				{ "John", "Smith", "9876543210", "Test123", "Test123", "No" },
//
//				{ "sohn", "mith", "9876543210", "Test123", "Test123", "No" },
//
//				{ "pohn", "ymith", "9876543210", "Test123", "Test123", "No" },
//
//				{ "ohn", "Smih", "9876543210", "Test123", "Test123", "No" } };
//
//		return obj;
//	}
//
//	@Test(dataProvider = "getUserRegistration")
//
//	public void userRegistrationTest(String fName, String lName, String telephoneNo, String pass, String confirmPass,
//			String subscribe) {
//
//		registrationSuccessPage=registerPage.userRegistration(fName, lName, StringUtil.generateEmailId(), telephoneNo, pass,
//				confirmPass, subscribe);
//		 
//	    Assert.assertEquals(
//	            registrationSuccessPage.getSuccessMessage(),
//	            AppConstants.USER_REGISTER_SUCCESS_MESSG);
//	    
//	    logoutSuccessPage = registrationSuccessPage.clickLogout();
//	    registerPage = logoutSuccessPage.clickRegister();
//

//	}

//	@Test
//	public void userRegistrationTest() {
//
//		boolean flag = registerPage.userRegistration("Nish", "Mehta", StringUtil.generateEmailId(), "9898989898",
//				"Admin@123", "Admin@123", "Yes");
//		Assert.assertTrue(flag);
//	}

	@DataProvider
	public Object[][] getUserDataFromSheet() {

		Object[][] obj = ExcelUtil.getTestData(AppConstants.USER_REGISTRATION_SHEET);

		return obj;
	}

	@Test(dataProvider = "getUserDataFromSheet")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {

		registrationSuccessPage = registerPage.userRegistration(firstName, lastName, StringUtil.generateEmailId(),
				telephone, password, password, subscribe);

		Assert.assertEquals(registrationSuccessPage.getSuccessMessage(), AppConstants.USER_REGISTER_SUCCESS_MESSG);

		logoutSuccessPage = registrationSuccessPage.clickLogout();
		registerPage = logoutSuccessPage.clickRegister();
	}

	@DataProvider
	public Object[][] getUserDataFromCsvFile() {

		Object[][] obj = CsvUtil.csvData(AppConstants.USER_REGISTRATION_SHEET_CSV);

		return obj;
	}

	@Test(dataProvider = "getUserDataFromCsvFile")
	public void userRegistrationTestCsv(String firstName, String lastName, String telephone, String password,
			String subscribe) {

		registrationSuccessPage = registerPage.userRegistration(firstName, lastName, StringUtil.generateEmailId(),
				telephone, password, password, subscribe);

		Assert.assertEquals(registrationSuccessPage.getSuccessMessage(), AppConstants.USER_REGISTER_SUCCESS_MESSG);

		logoutSuccessPage = registrationSuccessPage.clickLogout();
		registerPage = logoutSuccessPage.clickRegister();
	}

}
