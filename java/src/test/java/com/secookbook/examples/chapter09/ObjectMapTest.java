package com.secookbook.examples.chapter09;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class ObjectMapTest {

	private WebDriver driver;
	private ObjectMap map;

	@Before
	public void setUp() throws Exception {
		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();
		driver.get("http://cookbook.seleniumacademy.com/bmicalculator.html");
	}

	@Test
	public void testBmiCalculator() {
		// Get the Object Map File
		map = new ObjectMap("src/test/resources/objectmap/objectmap.properties");

		// Get the Height element
		WebElement height = driver.findElement(map.getLocator("height_field"));
		;
		height.sendKeys("181");

		// Get the Weight element
		WebElement weight = driver.findElement(map.getLocator("weight_field"));
		weight.sendKeys("80");

		// Click on the Calculate button
		WebElement calculateButton = driver.findElement(map
				.getLocator("calculate_button"));
		calculateButton.click();

		// Verify the Bmi
		WebElement bmi = driver.findElement(map.getLocator("bmi_field"));
		assertEquals("24.4", bmi.getAttribute("value"));
	}

	@After
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}
}