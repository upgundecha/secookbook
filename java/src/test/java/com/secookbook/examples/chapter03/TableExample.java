package com.secookbook.examples.chapter03;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TableExample {

	WebDriver driver;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("http://cookbook.seleniumacademy.com/Locators.html");
	}

	@Test
	public void testWebTable() {

		WebElement simpleTable = driver.findElement(By.id("items"));

		// Get all rows
		List<WebElement> rows = simpleTable.findElements(By.tagName("tr"));
		assertEquals(3, rows.size());

		// Print data from each row
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for (WebElement col : cols) {
				System.out.print(col.getText() + "\t");
			}
			System.out.println();
		}
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}