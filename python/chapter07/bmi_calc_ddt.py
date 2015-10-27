import unittest
from ddt import ddt, data, unpack
from selenium import webdriver


@ddt
class BmiCalcDDT(unittest.TestCase):
    def setUp(self):
        # create a new Firefox session
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(30)
        self.driver.maximize_window()

        # navigate to the BMI Calculator page
        self.driver.get("http://bit.ly/1zdNrFZ")

    # specify test data using @data decorator
    @data(("160", "45", "17.6", "Underweight"),
          ("168", "70", "24.8", "Normal"),
          ("181", "89", "27.2", "Overweight"))
    @unpack
    def test_bmi_calc(self, height, weight, bmi, category):
        driver = self.driver

        height_field = driver.find_element_by_name("heightCMS")
        height_field.clear()
        height_field.send_keys(height)

        weight_field = driver.find_element_by_name("weightKg")
        weight_field.clear()
        weight_field.send_keys(weight)

        calculate_button = driver.find_element_by_id("Calculate")
        calculate_button.click()

        bmi_label = driver.find_element_by_name("bmi")
        bmi_category_label = driver.find_element_by_name("bmi_category")

        self.assertEqual(bmi, bmi_label.get_attribute("value"))
        self.assertEqual(category, bmi_category_label.get_attribute("value"))

    def tearDown(self):
        # close the browser window
        self.driver.quit()

if __name__ == '__main__':
    unittest.main(verbosity=2)
