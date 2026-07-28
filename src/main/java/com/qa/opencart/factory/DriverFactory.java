package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.commonutlis.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	private static final Logger log = LogManager.getLogger(DriverFactory.class);

	public OptionsManager optionsManager;

	public WebDriver initDriver(Properties prop) {

		String browser = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		boolean remoteExecutions = Boolean.parseBoolean(prop.getProperty("remote"));

		// System.out.println("Browser Name is : " + browser);
		log.info("Browser Name is : " + browser);

		switch (browser.trim().toLowerCase()) {
		case "chrome":

			if (remoteExecutions) {
				init_remoteDriver(browser);
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

				// driver = new ChromeDriver();
			}
			break;

		case "edge":

			// driver = new EdgeDriver();

			if (remoteExecutions) {
				init_remoteDriver(browser);
			} else {

				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;

		case "firefox":

			if (remoteExecutions) {
				init_remoteDriver(browser);
			} else {

				// driver = new FirefoxDriver();
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;

		default:

			// System.out.println(AppError.INVALID_BROWSER_NAME);
			log.warn(AppError.INVALID_BROWSER_NAME);
			throw new FrameworkException("==Invalid Browser==");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public Properties initProp() {

		prop = new Properties();
		FileInputStream fp = null;

		String envName = System.getProperty("env");

		try {
			if (envName == null) {
				log.info("No Env is given hence running the test case on default env :" + envName);
				fp = new FileInputStream("src/test/resources/config/config.properties");
			} else {

				switch (envName.toLowerCase().trim()) {
				case "dev":
					log.info("Env is given hence running the test case on:" + envName + " env");
					fp = new FileInputStream("src/test/resources/config/config_dev.properties");
					break;

				case "uat":
					log.info("Env is given hence running the test case on:" + envName + " env");
					fp = new FileInputStream("src/test/resources/config/config_uat.properties");

					break;

				case "prod":
					log.info("Env is given hence running the test case on :" + envName + " env");
					fp = new FileInputStream("src/test/resources/config/config_prod.properties");

					break;

				default:
					log.error("Wrong Env Name is Passed :" + envName);
					throw new FrameworkException("===INVALID END PASSED==");

				}
			}
			prop.load(fp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	private void init_remoteDriver(String broswer) {
		try {

			switch (broswer.trim().toLowerCase()) {
			case "chrome":

				log.info("Test Cases are Running on Remote WebDriver Chrome");

				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;

			case "firefox":

				log.info("Test Cases are Running on Remote WebDriver Firefox");

				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;

			case "edge":

				log.info("Test Cases are Running on Remote WebDriver Edge");

				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));

				break;

			default:
				log.error("Incorrect Browser Name Passed.Please pass correct browser");
				throw new FrameworkException("===Incorrect Browser====");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static File getScreenshotAsFile() {

		File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		return file;
	}

}
