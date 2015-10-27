package com.secookbook.examples.chapter04;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationTest {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.google.com");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testNavigation() {
		driver.get("http://www.google.com");

		// Get the search textbox
		WebElement searchField = driver.findElement(By.name("q"));
		searchField.clear();

		// Enter search keyword and submit
		searchField.sendKeys("selenium webdriver");
		searchField.submit();

		WebElement resultLink = driver.findElement(By
				.linkText("Selenium WebDriver"));
		resultLink.click();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.titleIs("Selenium WebDriver"));
		
		assertEquals("Selenium WebDriver", driver.getTitle());

		driver.navigate().back();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.titleIs("selenium webdriver - Google Search"));
		
		assertEquals("selenium webdriver - Google Search", driver.getTitle());

		driver.navigate().forward();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.titleIs("Selenium WebDriver"));
		
		assertEquals("Selenium WebDriver", driver.getTitle());

		driver.navigate().refresh();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.titleIs("Selenium WebDriver"));

		assertEquals("Selenium WebDriver", driver.getTitle());
	}
}
