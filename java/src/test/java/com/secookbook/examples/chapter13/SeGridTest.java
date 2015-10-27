package com.secookbook.examples.chapter13;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class SeGridTest {

	WebDriver driver = null;

	@Parameters({ "platform", "browser", "version", "url" })
	@BeforeTest(alwaysRun = true)
	public void setup(String platform, String browser, String version,
			String url) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();

		// Platforms
		if (platform.equalsIgnoreCase("Windows")) {
			caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		}

		if (platform.equalsIgnoreCase("MAC")) {
			caps.setPlatform(org.openqa.selenium.Platform.MAC);
		}

		// Browsers
		if (browser.equalsIgnoreCase("Internet Explorer")) {
			caps = DesiredCapabilities.internetExplorer();
		}

		if (browser.equalsIgnoreCase("Firefox")) {
			caps = DesiredCapabilities.firefox();
		}

		if (browser.equalsIgnoreCase("Chrome")) {
			caps = DesiredCapabilities.chrome();
		}

		if (browser.equalsIgnoreCase("Safari")) {
			caps = DesiredCapabilities.safari();
		}

		// Version
		caps.setVersion(version);

		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
				caps);

		// Open the BMI Calculator Application
		driver.get(url);

	}

	@Test(description = "Test Bmi Calculator")
	public void testBmiCalculator() throws InterruptedException {

		WebElement height = driver.findElement(By.name("heightCMS"));
		height.sendKeys("181");

		WebElement weight = driver.findElement(By.name("weightKg"));
		weight.sendKeys("80");

		WebElement calculateButton = driver.findElement(By.id("Calculate"));
		calculateButton.click();

		WebElement bmi = driver.findElement(By.name("bmi"));
		assertEquals(bmi.getAttribute("value"), "24.4");

		WebElement bmiCategory = driver.findElement(By.name("bmi_category"));
		assertEquals(bmiCategory.getAttribute("value"), "Normal");

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}