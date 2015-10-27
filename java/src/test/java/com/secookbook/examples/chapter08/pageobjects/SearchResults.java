package com.secookbook.examples.chapter08.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.Assert.*;

public class SearchResults extends LoadableComponent<SearchResults> {

	private WebDriver driver;
	private String query;

	public SearchResults(WebDriver driver, String query) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	public void isLoaded() {
		assertEquals("Search results for: '" + this.query + "'", driver.getTitle());
	}

	public List<String> getProducts() {
		List<String> products = new ArrayList<String>();
		List<WebElement> productList = driver.findElements(
				By.cssSelector("ul.products-grid > li"));
		
		for (WebElement item : productList)  {
			products.add(item.findElement(By.cssSelector("h2 > a")).getText());
		}
		return products;
	}

	public Search Search() {
		Search search = new Search(driver);
		return search;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}
}
