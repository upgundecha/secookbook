import csv, unittest
from ddt import ddt, data, unpack
from selenium import webdriver

def get_data(file_name):
    # create an empty list to store rows
    rows = []
    # open the CSV file
    data_file = open(file_name, "rb")
    # create a CSV Reader from CSV file
    reader = csv.reader(data_file)
    # skip the headers
    next(reader, None)
    # add rows from reader to list
    for row in reader:
        rows.append(row)
    return rows


@ddt
class SearchCsvDDT(unittest.TestCase):
    def setUp(self):
        # create a new Firefox session
        self.driver = webdriver.Firefox()
        self.driver.implicitly_wait(30)
        self.driver.maximize_window()

        # navigate to the BMI Calculator page
        self.driver.get("http://bit.ly/1zdNrFZ")

    # get the data from specified csv file by calling the get_data function
    @data(*get_data("testdata.csv"))
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

        self.assertEqual(str(bmi), bmi_label.get_attribute("value"))
        self.assertEqual(str(category), bmi_category_label.get_attribute("value"))

    def tearDown(self):
        # close the browser window
        self.driver.quit()

if __name__ == '__main__':
    unittest.main(verbosity=2)
