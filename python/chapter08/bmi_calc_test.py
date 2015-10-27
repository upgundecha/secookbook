import unittest
from selenium.webdriver.chrome.webdriver import WebDriver
from bmi_calc_page import bmicalcpage

class BmiCalcTest(unittest.TestCase):
    def testCalc(self):
      driver = WebDriver()
      bmi_calc = bmicalcpage(driver)
      bmi_calc.open()
      self.assertEqual(True, bmi_calc.is_loaded)
      bmi_calc.calculate('181','80')
      self.assertEqual('24.4', bmi_calc.bmi)
      self.assertEqual('Normal', bmi_calc.bmi_category)
      driver.close()

if __name__ == '__main__':
    unittest.main()
