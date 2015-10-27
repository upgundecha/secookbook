package com.secookbook.examples.chapter04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventFiringTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @Test
    public void testEventFiringWebDriver() throws Exception {

        EventFiringWebDriver eventDriver =
        		new EventFiringWebDriver(driver);
        MyListener myListener = new MyListener();
        eventDriver.register(myListener);
        
        eventDriver.get("http://bit.ly/1DbdhsW");
        eventDriver.findElement(By.id("q"))
        	.sendKeys("Selenium Testing Tools Cookbook");
        eventDriver.findElement(By.id("btnG")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
