package com.secookbook.examples.chapter04;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import static org.junit.Assert.*;
import org.junit.Test;

public class DoubleClickTest {
	@Test
	public void testDoubleClick() throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.get("http://cookbook.seleniumacademy.com/DoubleClickDemo.html");

		try {
			WebElement message = driver.findElement(By.id("message"));

			// Verify color is Blue
			assertEquals("rgba(0, 0, 255, 1)",
					message.getCssValue("background-color"));

			Actions builder = new Actions(driver);
			builder.doubleClick(message).perform();

			// Verify Color is Yellow
			assertEquals("rgba(255, 255, 0, 1)",
					message.getCssValue("background-color"));
		} finally {
			driver.quit();
		}
	}
}