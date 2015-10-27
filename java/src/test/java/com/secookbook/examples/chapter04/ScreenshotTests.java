package com.secookbook.examples.chapter04;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.secookbook.examples.chapter09.WebElementExtender;

public class ScreenshotTests {

	WebDriver driver;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://www.google.com");
	}

	@Test
	public void testTakesScreenshot() throws Exception {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("target/main_page.png"));
	}

	@Test
	public void testElementScreenshot() throws Exception {
		WebElement searchButton = driver.findElement(By.name("btnK"));
		FileUtils.copyFile(
				WebElementExtender.captureElementPicture(searchButton),
				new File("target/searchButton.png"));
	}

	@After
	public void teadDown() {
		driver.quit();
	}
}