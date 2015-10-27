package com.secookbook.examples.chapter05;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class FluentWaitTest {

	@Test
	public void testFluentWait() {
		WebDriver driver = new ChromeDriver();
		// Launch the sample Ajax application
		driver.get("http://cookbook.seleniumacademy.com/AjaxDemo.html");

		try {
			driver.findElement(By.linkText("Page 4")).click();

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(10, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);

			WebElement message = wait
					.until(new Function<WebDriver, WebElement>() {
						public WebElement apply(WebDriver d) {
							return d.findElement(By.id("page4"));
						}
					});

			assertTrue(message.getText().contains("Nunc nibh tortor"));
		} finally {
			driver.quit();
		}
	}

	@Test
	public void testFluentWaitWithPredicate() {
		
		final WebDriver driver = new ChromeDriver();
		// Launch the sample Ajax application
		driver.get("http://cookbook.seleniumacademy.com/AjaxDemo.html");
	
		try {
			FluentWait<By> wait = new FluentWait<By>(By.linkText("Page 4"))
					.withTimeout(1000, TimeUnit.MILLISECONDS)
					.pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class);

			wait.until(new Predicate<By>() {
				public boolean apply(By by) {
					try {
						return driver.findElement(by).isDisplayed();
					} catch (NoSuchElementException ex) {
						return false;
					}
				}
			});
			driver.findElement(By.linkText("Page 4")).click();
		} finally {
			driver.quit();
		}
	}
}
