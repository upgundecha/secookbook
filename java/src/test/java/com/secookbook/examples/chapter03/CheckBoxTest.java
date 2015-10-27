package com.secookbook.examples.chapter03;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckBoxTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://cookbook.seleniumacademy.com/Config.html");
    }
    
 	@Test
    public void testCheckBox() {
 		//Get the Checkbox as WebElement using it's value attribute
 		WebElement airbags = driver.findElement(By.xpath("//input[@value='Airbags']"));
 		
 		//Check if its already selected? otherwise select the Checkbox
 		//by calling click() method
 		if (!airbags.isSelected()) {
 			airbags.click();
 		}
 		
 		//Verify Checkbox is Selected
 		assertTrue(airbags.isSelected());

 		//Check Checkbox if selected? If yes, deselect it
 		//by calling click() method
 		if (airbags.isSelected()) {
 			airbags.click();
 		}
 		
 		//Verify Checkbox is Deselected
 		assertFalse(airbags.isSelected());
    }
 	
    @After
    public void tearDown() {
        driver.quit();
    }
}