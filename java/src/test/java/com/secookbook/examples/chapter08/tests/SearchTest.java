package com.secookbook.examples.chapter08.tests;

import org.junit.Test;

import com.secookbook.examples.chapter08.pageobjects.Browser;
import com.secookbook.examples.chapter08.pageobjects.HomePage;
import com.secookbook.examples.chapter08.pageobjects.SearchResults;

import static org.junit.Assert.*;

public class SearchTest {

	@Test
	public void testProductSearch() {
		try {
			// Create an instance of Home page
			HomePage homePage = new HomePage(Browser.driver());

			// Navigate to the Home page
			homePage.get();

			// Search for 'phones', the searchInStore method will return
			// SerchResults
			SearchResults searchResult = homePage.Search().searchInStore(
					"phones");

			// Verify there are 2 products available with this search
			assertEquals(2, searchResult.getProducts().size());
			assertTrue(searchResult.getProducts().contains(
					"MADISON OVEREAR HEADPHONES"));
		} finally {
			Browser.close();
		}
	}
}
