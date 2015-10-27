package com.secookbook.examples.chapter04;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CookiesTest {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testCookies() {
		driver.get("http://demo.magentocommerce.com/");
		
		// Get the Your language dropdown as instance of Select class
		Select language = new Select(driver.findElement(By
				.id("select-language")));

		// Check default selected option is English
		assertEquals("English", language.getFirstSelectedOption().getText());

		// Store cookies should be none
		Cookie storeCookie = driver.manage().getCookieNamed("store");
		assertEquals(null, storeCookie);

		// Select an option using select_by_visible text
		language.selectByVisibleText("French");

		// Store cookie should be populated with selected country
		storeCookie = driver.manage().getCookieNamed("store");
		assertEquals("french", storeCookie.getValue());
	}
}
