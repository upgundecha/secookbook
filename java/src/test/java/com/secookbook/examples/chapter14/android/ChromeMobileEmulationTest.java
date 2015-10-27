package com.secookbook.examples.chapter14.android;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;

import static org.junit.Assert.*;

public class ChromeMobileEmulationTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Apple iPhone 6");

		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		WebDriver driver = new ChromeDriver(capabilities);
		
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
