package com.secookbook.examples.chapter08.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search {

	private WebDriver driver;
	
	private WebElement search;

	@FindBy(css = "button.button")
	private WebElement searchButton;

	public Search(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SearchResults searchInStore(String query) {
		search.sendKeys(query);
		searchButton.click();
		return new SearchResults(driver, query);
	}
}
