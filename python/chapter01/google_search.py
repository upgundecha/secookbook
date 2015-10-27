import unittest
from selenium.webdriver.support import expected_conditions
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait


class GoogleSearch(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.google.com/"

    def test_google_search(self):
        driver = self.driver
        driver.get(self.base_url)

        element = driver.find_element_by_idname("q")
        element.clear()
        element.send_keys("Selenium testing tools cookbook")
        element.submit()

        WebDriverWait(driver, 30)\
            .until(expected_conditions.title_contains("Selenium testing tools cookbook"))
        self.assertEqual(driver.title, "Selenium testing tools cookbook - Google Search")

    def tearDown(self):
        self.driver.quit()

if __name__ == "__main__":
    unittest.main(verbosity=2, warnings="ignore")