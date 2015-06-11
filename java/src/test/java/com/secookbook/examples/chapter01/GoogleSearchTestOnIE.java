package com.secookbook.examples.chapter01;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;

import static org.junit.Assert.*;

public class GoogleSearchTestOnIE {

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.ie.driver",
				"src/test/resources/drivers/IEDriverServer.exe");

		DesiredCapabilities caps = new DesiredCapabilities().internetExplorer();

		caps.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);

		// launch Internet Explorer
		driver = new InternetExplorerDriver(caps);
		// maximize the browser window
		driver.manage().window().maximize();
		// navigate to Google
		driver.get("http://www.google.com");
	}

	@Test
	public void testGoogleSearch() {
		// find the text input element by its name
		WebElement element = driver.findElement(By.name("q"));

		// enter something to search for
		element.sendKeys("Selenium testing tools cookbook");

		// now submit the form. WebDriver will find
		// the form for us from the element
		element.submit();

		// Google's search is rendered dynamically with JavaScript.
		// wait for the page to load, timeout after 10 seconds
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase()
						.startsWith("selenium testing tools cookbook");
			}
		});

		assertEquals("Selenium testing tools cookbook - Google Search",
				driver.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		// close the browser
		driver.quit();
	}
}
