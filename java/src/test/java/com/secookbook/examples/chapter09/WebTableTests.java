package com.secookbook.examples.chapter09;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WebTableTests {
	WebDriver driver;

	@Before
	public void setUp() {
		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();
		driver.get("http://cookbook.seleniumacademy.com/Locators.html");
	}

	@Test
	public void testWebTableTest() {
		// Get the table element as WebTable instance using CSS Selector
		WebTable table = new WebTable(driver.findElement(By
				.cssSelector("div.cart-info table")));

		// Verify that it has three rows
		assertEquals(3, table.getRowCount());
		// Verify that it has six columns
		assertEquals(5, table.getColumnCount());
		// Verify that specified value exists in second cell of third row
		assertEquals("iPhone", table.getCellData(3, 1));

		// Get in cell editor and enter some value
		WebElement cellEdit = table.getCellEditor(3, 3, 0);
		cellEdit.clear();
		cellEdit.sendKeys("2");
	}

	@After
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}