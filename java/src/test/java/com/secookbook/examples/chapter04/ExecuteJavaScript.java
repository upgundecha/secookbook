package com.secookbook.examples.chapter04;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExecuteJavaScript {
	@Test
	public void testJavaScriptCalls() throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			String title = (String) js.executeScript("return document.title");
			assertEquals("Google", title);

			long links = (Long) js
					.executeScript("var links = document.getElementsByTagName('A'); return links.length");
			assertEquals(42, links);
		} finally {
			driver.quit();
		}
	}
}