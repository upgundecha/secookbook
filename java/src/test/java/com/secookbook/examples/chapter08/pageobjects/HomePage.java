package com.secookbook.examples.chapter08.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.Assert.*;

public class HomePage extends LoadableComponent<HomePage> {

	private WebDriver driver; 
	
	static String url = "http://demo.magentocommerce.com/";
	private static String title = "Madison Island";

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	public void load() {
		Browser.open(url);
	}

	@Override
	public void isLoaded() {
		assertEquals(title, driver.getTitle());
	}

	public Search Search() {
		Search search = new Search(driver);
		return search;
	}
}
