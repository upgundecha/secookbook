package com.secookbook.examples.chapter04;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class RowSelectionTests {

	WebDriver driver;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://component-showcase.icesoft.org/component-showcase/showcase.iface");

		driver.findElement(By.linkText("Table")).click();
		driver.findElement(By.linkText("Row Selection")).click();
		driver.findElement(
				By.xpath("//label[@class='iceSelOneRb' and text()='Multiple']"))
				.click();
	}

	@Test
	public void testRowSelectionUsingControlKey() {

		List<WebElement> tableRows = driver.findElements(By
				.cssSelector("table.iceDatTbl tbody tr"));

		// Select second and fourth row from Table using Control Key.
		// Row Index start at 0
		Actions builder = new Actions(driver);
		builder.click(tableRows.get(1)).keyDown(Keys.COMMAND)
				.click(tableRows.get(3)).keyUp(Keys.COMMAND).perform();

		// Verify Selected Row Table shows two rows selected
		List<WebElement> selectedRows = driver
				.findElements(By
						.cssSelector("div.icePnlGrp.exampleBox > table.iceDatTbl tbody tr"));
		assertEquals(2, selectedRows.size());

	}

	@Test
	public void testRowSelectionUsingShiftKey() {

		List<WebElement> tableRows = driver.findElements(By
				.cssSelector("table.iceDatTbl tbody tr"));

		// Select first row to fourth row from Table using Shift Key
		// Row Index start at 0
		Actions builder = new Actions(driver);
		builder.click(tableRows.get(0)).keyDown(Keys.SHIFT)
				.click(tableRows.get(1)).click(tableRows.get(2))
				.click(tableRows.get(3)).keyUp(Keys.SHIFT).perform();

		List<WebElement> selectedRows = driver
				.findElements(By
						.cssSelector("div.icePnlGrp.exampleBox > table.iceDatTbl tbody tr"));
		assertEquals(4, selectedRows.size());
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
