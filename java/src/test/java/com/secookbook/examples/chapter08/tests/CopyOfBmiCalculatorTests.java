package com.secookbook.examples.chapter08.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

import com.secookbook.examples.chapter08.pageobjects.BmiCalcPage;
import com.secookbook.examples.chapter08.pageobjects.CopyOfBmiCalcPage;

public class CopyOfBmiCalculatorTests {

	private WebDriver driver;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("http://cookbook.seleniumacademy.com/bmicalculator.html");
	}

	@Test
	public void testBmiCalculation() {
		// Create an instance of Bmi Calculator Page class
		// and provide the driver
		CopyOfBmiCalcPage bmiCalcPage = new CopyOfBmiCalcPage(driver);
		
		// Set Height
		bmiCalcPage.setHeight("181");
		
		// Set Weight
		bmiCalcPage.setWeight("80");
		
		// Click on Calculate button
		bmiCalcPage.calculateBmi();

		// Verify Bmi & Bmi Category values
		assertEquals("24.4", bmiCalcPage.getBmi());
		assertEquals("Normal", bmiCalcPage.getBmiCategory());
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
