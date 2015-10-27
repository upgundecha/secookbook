package com.secookbook.examples.chapter06;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class FramesTest {

	public static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://cookbook.seleniumacademy.com/Frames.html");
		driver.manage().window().maximize();
	}

	@Test
	public void testFrameWithIdOrName() {
		try {

			// Activate the frame on left side using it's id attribute
			driver.switchTo().frame("left");

			// Get an element from the frame on left side and verify it's
			// contents
			WebElement msg = driver.findElement(By.tagName("p"));
			assertEquals("This is Left Frame", msg.getText());
		} finally {
			// Activate the Page, this will move context from frame back to the
			// Page
			driver.switchTo().defaultContent();
		}

		try {
			// Activate the frame on right side using it's name attribute
			driver.switchTo().frame("right");

			// Get an element from the frame on right side and verify it's
			// contents
			WebElement msg = driver.findElement(By.tagName("p"));
			assertEquals("This is Right Frame", msg.getText());
		} finally {
			// Activate the Page, this will move context from frame back to the
			// Page
			driver.switchTo().defaultContent();
		}
	}

	@Test
	public void testFrameByIndex() {
		try {
			// Activate the frame in middle using it's index. Index starts at 0
			driver.switchTo().frame(1);

			// Get an element from the frame in the middle and verify it's
			// contents
			WebElement msg = driver.findElement(By.tagName("p"));
			assertEquals("This Frame doesn't have id or name", msg.getText());
		} finally {
			// Activate the Page, this will move context from frame back to the
			// Page
			driver.switchTo().defaultContent();
		}
	}

	@Test
	public void testFrameByContents() {
		// Get all frames on the Page, created with <frame> tag
		List<WebElement> frames = driver.findElements(By.tagName("frame"));

		// In this example frame in the middle is activated by checking the
		// contents
		// Activate frame and check if it has the desired content. If found
		// perform the operations
		// if not, then switch back to the Page and continue checking next frame
		try {
			for (WebElement frame : frames) {
				// switchTo().frame() also accepts frame elements apart from id,
				// name or index
				driver.switchTo().frame(frame);
				String title = driver.getTitle();
				if (title.equals("Frame B")) {
					WebElement msg = driver.findElement(By.tagName("p"));
					assertEquals("This is Left Frame", msg.getText());
					break;
				} else
					driver.switchTo().defaultContent();
			}
		} finally {
			// Activate the Page, this will move context from frame back to the
			// Page
			driver.switchTo().defaultContent();
		}
	}

	@Test
	public void testIFrame() {
		// Store the handle of current driver window
		String currentWindow = driver.getWindowHandle();
		
		// The frame on the right side has a nested iframe containing 'Twitter
		// Follow' Button
		// Activate the frame on right side using it's name attribute
		try {
			driver.switchTo().frame("right");

			// Get the iframe element
			WebElement twitterFrame = driver.findElement(By.tagName("iframe"));

			try {
				// Activate the iframe
				driver.switchTo().frame(twitterFrame);
				// Get and Click the follow button from iframe
				// a Popup Window will appear after click
				WebElement button = driver.findElement(By.id("follow-button"));
				button.click();

				try {
					// The Twitter Popup does not have name or title.
					// Script will get handles of all open windows and
					// desired window will be activated by checking it's Title

					for (String windowId : driver.getWindowHandles()) {
						driver.switchTo().window(windowId);
						if (driver.getTitle().equals(
								"Unmesh Gundecha (@upgundecha) on Twitter")) {
							assertTrue("Twitter Login Popup Window Found", true);
							driver.close();
							break;
						}

					}
				} finally {
					// Switch back to original driver window
					driver.switchTo().window(currentWindow);
				}
			} finally {
				// switch back to Page from the frame
				driver.switchTo().defaultContent();
			}

		} finally {
			// switch back to Page from the frame
			driver.switchTo().defaultContent();
		}
	}

	@AfterClass
	public static void tearDown() {
		// Close the Parent Popup Window
		driver.close();
		driver.quit();
	}
}
