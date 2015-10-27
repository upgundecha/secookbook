package com.secookbook.examples.chapter08.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CopyOfBmiCalcPage  {

	private WebElement heightCMS;
	private WebElement weightKg;
	private WebElement Calculate;
	private WebElement bmi;
	
	@FindBy(id = "bmi_category")
	private WebElement bmiCategory;

	private WebDriver driver;

	public CopyOfBmiCalcPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setHeight(String height) {
		heightCMS.sendKeys(height);
	}
	
	public void setWeight(String weight) {
		weightKg.sendKeys(weight);
	}

	public void calculateBmi() {
		Calculate.click();
	}

	public String getBmi() {
		return bmi.getAttribute("value");
	}

	public String getBmiCategory() {
		return bmiCategory.getAttribute("value");
	}
}
