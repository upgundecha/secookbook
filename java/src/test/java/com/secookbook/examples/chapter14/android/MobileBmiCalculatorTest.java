package com.secookbook.examples.chapter14.android;

import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;

import static org.junit.Assert.*;

public class MobileBmiCalculatorTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		// Set the desired capabilities for Android Device
		DesiredCapabilities caps = DesiredCapabilities.android();
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("platformVersion", "4.4");
		caps.setCapability("platformName", "Android");
		caps.setCapability("browserName", "Chrome");

		// Create an instance of AndroidDriver for testing on Android platform
		// connect to the local Appium server running on a different machine
		// We will use WebElement type for testing the Web application
		driver = new AndroidDriver<WebElement>(new URL(
				"http://192.168.0.101:4723/wd/hub"), caps);

		// Open the BMI Calculator Mobile Application
		driver.get("http://cookbook.seleniumacademy.com/mobilebmicalculator.html");
	}

	@Test
	public void testMobileBmiCalculator() throws Exception {
		// Get the height field and set the value
		WebElement heightField = driver.findElement(By.name("heightCMS"));
		heightField.sendKeys("181");

		// Get the weight field and set the value
		WebElement weightField = driver.findElement(By.name("weightKg"));
		weightField.sendKeys("80");

		// Click on Calculate button
		WebElement calculateButton = driver.findElement(By.id("Calculate"));
		calculateButton.click();

		// Check the Bmi Result
		WebElement bmiLabel = driver.findElement(By.name("bmi"));
		assertEquals("24.4", bmiLabel.getAttribute("value"));

		// Check the Category Result
		WebElement bmiCategoryLabel = driver.findElement(By.name("bmi_category"));
		assertEquals("Normal", bmiCategoryLabel.getAttribute("value"));
	}

	@After
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}
}
