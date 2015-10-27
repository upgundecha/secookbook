package com.secookbook.examples.chapter09;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;

public class BmiCalculatorTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();
	}

	@Test
	public void testBmiCalculatorLayout() throws Exception {

		String scrFile = "c:\\screenshot.png";
		String baseScrFile = "c:\\baseScreenshot.png";

		// Open the BMI Calculator Page and get a Screen Shot of Page into a
		// File
		driver.get("http://cookbook.seleniumacademy.com/bmicalculator.html");
		File screenshotFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(scrFile));

		// Verify baseline image with actual image
		assertEquals(CompareUtil.Result.Matched,
				CompareUtil.CompareImage(baseScrFile, scrFile));

	}

	@After
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}
}
