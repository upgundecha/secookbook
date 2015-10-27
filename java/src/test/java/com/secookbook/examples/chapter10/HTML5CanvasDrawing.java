package com.secookbook.examples.chapter10;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.secookbook.examples.chapter09.CompareUtil;
import com.secookbook.examples.chapter09.WebElementExtender;

import static org.junit.Assert.*;

public class HTML5CanvasDrawing {

	private WebDriver driver;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://cookbook.seleniumacademy.com/html5canvasdraw.html");
	}

	@Test
	public void testHTML5CanvasDrawing() throws Exception {
		// Get the HTML5 Canvas Element
		WebElement canvas = driver.findElement(By.id("imageTemp"));
		
		// Select the Pencil Tool
		Select drawTools = new Select(driver.findElement(By.id("dtool")));
		drawTools.selectByValue("pencil");

		// Create a Action chain to draw a shape on Canvas
		Actions builder = new Actions(driver);
		builder.clickAndHold(canvas).moveByOffset(10, 50).moveByOffset(50, 10)
				.moveByOffset(-10, -50).moveByOffset(-50, -10).release()
				.perform();

		// Get a screenshot of Canvas element after drawing and compare it to
		// the base version
		FileUtils.copyFile(WebElementExtender.captureElementPicture(canvas),
				new File("target/screenshots/drawing.png"));
		
		assertEquals(CompareUtil.Result.Matched, CompareUtil.CompareImage(
				"src/test/resources/testdata/base_drawing.png", "target/screenshots/drawing.png"));
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}