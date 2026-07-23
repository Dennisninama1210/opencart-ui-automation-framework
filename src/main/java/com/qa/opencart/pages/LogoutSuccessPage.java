package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.commonutlis.ElementUtil;

public class LogoutSuccessPage {

    private WebDriver driver;
    private ElementUtil elUtil;

	private final By register=By.xpath("(//a[text()='Register'])[2]");

    public LogoutSuccessPage(WebDriver driver) {
        this.driver = driver;
        elUtil = new ElementUtil(driver);
    }

    public RegisterPage clickRegister() {

        elUtil.doClick(register);

        return new RegisterPage(driver);

    }


}
