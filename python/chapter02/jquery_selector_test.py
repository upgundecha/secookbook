from selenium import webdriver
from array import *
import time, unittest

class JQuerySelectorTest (unittest.TestCase):
	def setUp(self)	:
		self.driver = webdriver.Chrome()
		self.driver.get("http://dl.dropbox.com/u/55228056/Locators.html")
		
	def test_jquery_selector(self):
		driver = self.driver
		
		#Expected list of selected Checkbox
		exp_checked = ["user128_admin", "user220_browser"]
		act_checked = []

		elements = driver.execute_script("return jQuery.find(':checked')")

		#Verify two Checkbox are selected 
		self.assertEquals(2, len(elements))

		#Verify correct Checkbox are selected
		for element in elements:
			act_checked.append(element.get_attribute("id"))
			
		self.assertEquals(act_checked, exp_checked)

	def tearDown(self):
		self.driver.close()
		
if __name__ == "__main__":
	unittest.main()