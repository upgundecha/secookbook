package com.secookbook.examples.chapter08.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import static org.junit.Assert.*;

public class BmiCalcPage extends LoadableComponent<BmiCalcPage> {

	private WebElement heightCMS;
	private WebElement weightKg;
	private WebElement calculate;
	private WebElement bmi;
	private WebElement bmiCategory;

	private WebDriver driver;

	private String url = "http://cookbook.seleniumacademy.com/bmicalculator.html";
	private String title = "BMI Calculator";

	public BmiCalcPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		this.driver.get(url);
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue("Bmi Calculator page not loaded",
				driver.getTitle().equals(title));
	}

	public void calculateBmi(String height, String weight) {
		heightCMS.sendKeys(height);
		weightKg.sendKeys(weight);
		calculate.click();
	}

	public String getBmi() {
		return bmi.getAttribute("value");
	}

	public String getBmiCategory() {
		return bmiCategory.getAttribute("value");
	}
}
