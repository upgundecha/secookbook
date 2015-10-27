package com.secookbook.examples.chapter10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HTML5LocalStorage {

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"src/test/resources/drivers/chromedriver.exe");
	
		driver = new ChromeDriver();
		driver.get("http://cookbook.seleniumacademy.com/html5storage.html");
	}

	@Test
	public void testHTML5LocalStorage() throws Exception {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		jsExecutor.executeScript("localStorage.lastname = arguments[0];", "Dustin"); 

		// Get the current value of localStorage.lastname, this should be Smith
		String lastName = (String) jsExecutor
				.executeScript("return localStorage.lastname;");
		assertEquals("Smith", lastName);
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}