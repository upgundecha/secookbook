package com.secookbook.examples.chapter04;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContextMenuTest {
	
	WebDriver driver;
	
	@Before
	public void setUp() {
		 driver = new ChromeDriver();
		 driver.get("http://bit.ly/1CAV05I");
	}
	
	@Test
	public void testContextMenu() {
	  WebElement clickMeElement = 
			  driver.findElement(By.cssSelector("div.context-menu-one.box.menu-1"));
	  WebElement editMenuItem = 
			  driver.findElement(By.cssSelector("li.context-menu-item.icon-edit"));
	  
	  Actions builder = new Actions(driver);
	  builder.contextClick(clickMeElement)
	  	.moveToElement(editMenuItem)
	  	.click()
	  	.perform();
	  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	  assertEquals("clicked: edit", alert.getText());
	  alert.dismiss();
	}

	@Test
	public void testContextMenuWithKeys() {
	  WebElement clickMeElement = 
			  driver.findElement(By.cssSelector("div.context-menu-one.box.menu-1"));
	  
	  Actions builder = new Actions(driver);
	  builder.contextClick(clickMeElement)
	  	.sendKeys(Keys.chord(Keys.ALT, "e"))
	  	.perform();
	  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	  assertEquals("clicked: edit", alert.getText());
	  alert.dismiss();
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
