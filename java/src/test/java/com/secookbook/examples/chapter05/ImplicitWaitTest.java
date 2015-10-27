package com.secookbook.examples.chapter05;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

public class ImplicitWaitTest {
	@Test
	public void testWithOutWait() {

		WebDriver driver = new FirefoxDriver();
		// Launch the sample Ajax application
		driver.get("http://cookbook.seleniumacademy.com/AjaxDemo.html");

		try {
			// Get link for Page 4 and click on it
			driver.findElement(By.linkText("Page 4")).click();

			// Get an element with id page4 and verify it's text
			// Test will fail with NoSuchElementException while finding the
			// element without an implicit wait
			WebElement message = driver.findElement(By.id("page4"));
			assertTrue(message.getText().contains("Nunc nibh tortor"));
		} finally {
			driver.quit();
		}
	}

	@Test
	public void testWithImplicitWait() {
		WebDriver driver = new FirefoxDriver();
		// Launch the sample Ajax application
		driver.get("http://cookbook.seleniumacademy.com/AjaxDemo.html");

		// Set the implicit wait time out to 10 Seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {
			// Get link for Page 4 and click on it
			driver.findElement(By.linkText("Page 4")).click();

			// Get an element with id page4 and verify it's text
			WebElement message = driver.findElement(By.id("page4"));
			assertTrue(message.getText().contains("Nunc nibh tortor"));
		} finally {
			driver.quit();
		}
	}
}
