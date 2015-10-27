package com.secookbook.examples.chapter10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HTML5SessionStorage {

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"src/test/resources/drivers/chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("http://cookbook.seleniumacademy.com/html5storage.html");
	}

	@Test
	public void testHTML5SessionStorage() throws Exception {
		WebElement clickButton = driver.findElement(By.id("click"));
		WebElement clicksField = driver.findElement(By.id("clicks"));

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		// Get current value of sessionStorage.clickcount, should be null
		String clickCount = (String) jsExecutor
				.executeScript("return sessionStorage.clickcount;");
		assertEquals(null, clickCount);
		assertEquals("0", clicksField.getAttribute("value"));

		// Click the Button, this will increase the sessionStorage.clickcount
		// value by 1
		clickButton.click();

		// Get current value of sessionStorage.clickcount, should be 1
		clickCount = (String) jsExecutor
				.executeScript("return sessionStorage.clickcount;");
		assertEquals("1", clickCount);
		assertEquals("1", clicksField.getAttribute("value"));
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}