package com.secookbook.examples.chapter06;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlertsTest {

	private static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://cookbook.seleniumacademy.com/Alerts.html");
		driver.manage().window().maximize();
	}

	@Test
	public void testSimpleAlert() {
		// Click Simple button to show an Alert box
		driver.findElement(By.id("simple")).click();
	
		// Optionally we can also wait for an Alert box using the WebDriverWait
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.alertIsPresent());
		
		// Get the Alert
		Alert alert = driver.switchTo().alert();

		// Get the text displayed on Alert
		String textOnAlert = alert.getText();

		// Check correct message is displayed to the user on Alert box
		assertEquals("Hello! I am an alert box!", textOnAlert);

		// Click OK button, by calling accept method
		alert.accept();
	}

	@Test
	public void testConfirmAccept() {
		// Click Confirm button to show Confirmation Alert box
		driver.findElement(By.id("confirm")).click();

		// Get the Alert
		Alert alert = driver.switchTo().alert();

		// Click OK button, by calling accept method
		alert.accept();

		// Check Page displays correct message
		WebElement message = driver.findElement(By.id("demo"));
		assertEquals("You Accepted Alert!", message.getText());
	}

	@Test
	public void testConfirmDismiss() {
		// Click Confirm button to show Confirmation Alert box
		driver.findElement(By.id("confirm")).click();

		// Get the Alert
		Alert alert = driver.switchTo().alert();

		// Click Cancel button, by calling dismiss method
		alert.dismiss();

		// Check Page displays correct message
		WebElement message = driver.findElement(By.id("demo"));
		assertEquals("You Dismissed Alert!", message.getText());
	}

	@Test
	public void testPrompt() {
		// Click Confirm button to show Prompt Alert box
		driver.findElement(By.id("prompt")).click();

		// Get the Alert
		Alert alert = driver.switchTo().alert();

		// Enter some value on Prompt Alert box
		alert.sendKeys("Foo");

		// Click OK button, by calling accept method
		alert.accept();

		// Check Page displays message with value entered in Prompt
		WebElement message = driver.findElement(By.id("prompt_demo"));
		assertEquals("Hello Foo! How are you today?", message.getText());
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
