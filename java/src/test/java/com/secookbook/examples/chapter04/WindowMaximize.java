package com.secookbook.examples.chapter04;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WindowMaximize {
	@Test
	public void testRowSelectionUsingControlKey() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		driver.quit();
	}
}
