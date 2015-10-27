package com.secookbook.examples.chapter07;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

import com.opencsv.CSVReader;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

@RunWith(Parameterized.class)
public class CsvTestData {

	private static WebDriver driver;

	private String height;
	private String weight;
	private String bmi;
	private String bmiCategory;

	@Parameters
	public static List<String[]> testData() throws IOException {
		return getTestData("./src/test/resources/testdata/data.csv");
	}

	public CsvTestData(String height, String weight, String bmi,
			String bmiCategory) {
		this.height = height;
		this.weight = weight;
		this.bmi = bmi;
		this.bmiCategory = bmiCategory;
	}

	public static List<String[]> getTestData(String fileName)
			throws IOException {
		CSVReader reader = new CSVReader(new FileReader(fileName));
		List<String[]> myEntries = reader.readAll();
		reader.close();
		return myEntries;
	}

	@BeforeClass
	public static void setUp() throws Exception {
		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();
		driver.get("http://cookbook.seleniumacademy.com/bmicalculator.html");
	}

	@Test
	public void testBMICalculator() throws Exception {
		WebElement heightField = driver.findElement(By.name("heightCMS"));
		heightField.clear();
		heightField.sendKeys(height);

		WebElement weightField = driver.findElement(By.name("weightKg"));
		weightField.clear();
		weightField.sendKeys(weight);

		WebElement calculateButton = driver.findElement(By.id("Calculate"));
		calculateButton.click();

		WebElement bmiLabel = driver.findElement(By.name("bmi"));
		assertEquals(bmi, bmiLabel.getAttribute("value"));

		WebElement bmiCategoryLabel = driver.findElement(By
				.name("bmi_category"));
		assertEquals(bmiCategory, bmiCategoryLabel.getAttribute("value"));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}
}
