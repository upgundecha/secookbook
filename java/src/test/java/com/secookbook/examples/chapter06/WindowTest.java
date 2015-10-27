package com.secookbook.examples.chapter06;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class WindowTest {

	public static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://cookbook.seleniumacademy.com/Config.html");
		driver.manage().window().maximize();
	}

	@Test
	public void testWindowUsingName() {
		// Store WindowHandle of parent browser window
		String parentWindowId = driver.getWindowHandle();

		// Clicking Help button will open Help Page in a new child window
		driver.findElement(By.id("helpbutton")).click();

		try {
			// Switch to the Help window using name
			driver.switchTo().window("HelpWindow");

			try {
				// Check the driver context is in Help window
				assertEquals("Help", driver.getTitle());
			} finally {
				// Close the Help window
				driver.close();
			}
		} finally {
			// Switch to the parent browser window
			driver.switchTo().window(parentWindowId);
		}
		// Check driver context is in parent browser window
		assertEquals("Build my Car - Configuration", driver.getTitle());
	}

	@Test
	public void testWindowUsingTitle() {
		// Store WindowHandle of parent browser window
		String parentWindowId = driver.getWindowHandle();

		// Clicking Visit Us Button will open Visit Us Page in a new child
		// window
		driver.findElement(By.id("visitbutton")).click();

		// Get Handles of all the open windows
		// iterate through list and check if tile of
		// each window matches with expected window title
		try {
			for (String windowId : driver.getWindowHandles()) {
				String title = driver.switchTo().window(windowId).getTitle();
				if (title.equals("Visit Us")) {
					assertEquals("Visit Us", driver.getTitle());
					// Close the Visit Us window
					driver.close();
					break;
				}
			}
		} finally {
			// Switch to the parent browser window
			driver.switchTo().window(parentWindowId);
		}

		// Check driver context is in parent browser window
		assertEquals("Build my Car - Configuration", driver.getTitle());
	}

	@Test
	public void testWindowUsingContents() {
		// Store WindowHandle of parent browser window
		String currentWindowId = driver.getWindowHandle();

		// Clicking Chat Button will open Chat Page in a new child window
		driver.findElement(By.id("chatbutton")).click();

		// There is no name or title provided for Chat Page window
		// We will iterate through all the open windows
		// and check the contents to find out if it's Chat window
		try {
			for (String windowId : driver.getWindowHandles()) {
				driver.switchTo().window(windowId);

				// We will use the page source to check the contents
				String pageSource = driver.getPageSource();

				if (pageSource.contains("Configuration - Online Chat")) {

					// Check the page for an element displaying a expected
					// message
					assertTrue(driver.findElement(By.tagName("p")).getText()
							.equals("Wait while we connect you to Chat..."));

					// Find the Close Button on Chat Window and close the window
					// by clicking Close Button
					driver.findElement(By.id("closebutton")).click();
					break;
				}
			}
		} finally {
			// Switch back to the parent browser window
			driver.switchTo().window(currentWindowId);
		}
		// Check driver context is in parent browser window
		assertEquals("Build my Car - Configuration", driver.getTitle());
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
