package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.commonutlis.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EP100: Design the Open cart Login App Page")
@Feature("F101: Design open cart login page")
@Story("S01: develop login core features")

public class LoginPageTest extends BaseTest {

	@Description("This is the Login Title Page Test")
	@Link("JIRA-1001")
	@Owner("Nishant Goel")
	@Severity(SeverityLevel.CRITICAL)

	@Test

	public void loginPageTitleTest() {

		String actTitle = loginpage.getLoginPageTitle();
		ChainTestListener.log("Login page Title is :" + actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test

	public void loginPageUrlTest() {

		String actUrl = loginpage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Test

	public void isForgotPwdLinkExistTest() {

		boolean flag = loginpage.isForgotPwdLinkExist();
		Assert.assertTrue(flag);
	}

	@Test

	public void isHeaderExistTest() {

		boolean flag = loginpage.isHeaderExist();
		Assert.assertTrue(flag);
	}

	@Test

	public void loginTest() {

		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		boolean flag = accPage.isLogOutLinkExist();
		System.out.println(flag);

	}

//	@Test
//	public void negativeloginTest() {
//
//		String actualMsg  = loginpage.negativeLogin("admin@gmail.com", "admin@12333");
//		Assert.assertEquals(actualMsg , "Warning: No match for E-Mail Address and/or Password.");
//
//	}

}
