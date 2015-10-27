class BmiCalcPage(object):
	def __init__(self, driver):
		self._driver = driver
		self._url = 'http://cookbook.seleniumacademy.com/bmicalculator.html'
		self._title = 'BMI Calculator'
	
	@property
	def is_loaded(self):
		return self._driver.title == self._title
	
	@property
	def bmi(self):
		bmi_field = self._driver.find_element_by_id('bmi')
		return bmi_field.get_attribute('value')
	
	@property
	def bmi_category(self):
		bmi_category_field = self._driver.find_element_by_id('bmi_category')
		return bmi_category_field.get_attribute('value')
	
	def open(self):
		self._driver.get(self._url)
	
	def calculate(self, height, weight):
		height_field = self._driver.find_element_by_id('heightCMS')
		weight_field = self._driver.find_element_by_id('weightKg')
		calc_button = self._driver.find_element_by_id('Calculate')
		
		height_field.send_keys(height)
		weight_field.send_keys(weight)
		calc_button.click()