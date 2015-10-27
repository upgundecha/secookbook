package com.secookbook.examples.chapter08.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

import com.secookbook.examples.chapter08.pageobjects.BmiCalcPage;

public class BmiCalculatorTests {

	private WebDriver driver;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}

	@Test
	public void testBmiCalculation() {
		// Create an instance of Bmi Calculator Page class
		// and provide the driver
		BmiCalcPage bmiCalcPage = new BmiCalcPage(driver);

		// Open the Bmi Calculator Page
		bmiCalcPage.get();

		// Calculate the Bmi by supplying Height and Weight values
		bmiCalcPage.calculateBmi("181", "80");

		// Verify Bmi & Bmi Category values
		assertEquals("24.4", bmiCalcPage.getBmi());
		assertEquals("Normal", bmiCalcPage.getBmiCategory());
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
