package com.secookbook.examples.chapter13;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomjsTest {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
		caps.setJavascriptEnabled(true);
		caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"./src/test/resources/drivers/phantomjs.exe");
		driver = new PhantomJSDriver(caps);
		driver.get("http://bit.ly/1zdNrFZ");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testBmiCalc() {
		WebElement height = driver.findElement(By.name("heightCMS"));
		height.sendKeys("181");

		WebElement weight = driver.findElement(By.name("weightKg"));
		weight.sendKeys("80");

		WebElement calculateButton = driver.findElement(By.id("Calculate"));
		calculateButton.click();

		WebElement bmi = driver.findElement(By.name("bmi"));
		assertEquals(bmi.getAttribute("value"), "24.4");

		WebElement bmi_category = driver.findElement(By.name("bmi_category"));
		assertEquals(bmi_category.getAttribute("value"), "Normal");
	}
}
