package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountPage;

public class AccountPageTest extends BaseTest {

	// BT -> BC -> Test

	@BeforeClass

	public void accSetUp() {
		accPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));

		// accpage=new AccountPage(driver);
	}

	@Test

	public void isLogOutLinkExist() {

		boolean flag = accPage.isLogOutLinkExist();
		Assert.assertTrue(flag);

	}

	@Test
	public void accPageHeadersTest() {
		List<String> accPageHeader = accPage.getAccPageHeader();
		Assert.assertEquals(accPageHeader.size(), 4);
	}

	@Test

	public void searchProduct() {
		accPage.doSearch("imac");
	}

}
