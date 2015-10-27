package com.secookbook.examples.chapter03;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RadioButtonTest {
	private WebDriver driver;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://cookbook.seleniumacademy.com/Config.html");
	}

	@Test
	public void testRadioButton() {
		// Get the Radio Button as WebElement using it's value attribute
		WebElement petrol = driver.findElement(By
				.xpath("//input[@value='Petrol']"));

		// Check if its already selected? otherwise select the Radio Button
		// by calling click() method
		if (!petrol.isSelected()) {
			petrol.click();
		}

		// Verify Radio Button is selected
		assertTrue(petrol.isSelected());

		// We can also get all the Radio buttons from a Radio Group in a list
		// using findElements() method along with Radio Group identifier
		List<WebElement> fuel_type = driver.findElements(By.name("fuel_type"));
		for (WebElement type : fuel_type) {
			// Search for Diesel Radio Button in the Radio Group and select it
			if (type.getAttribute("value").equals("Diesel")) {
				if (!type.isSelected()) {
					type.click();
				}
				assertTrue(type.isSelected());
				break;
			}
		}
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}