from behave import *


@given('the user is on Fund Transfer Page')
def step_user_is_on_fund_transfer_page(context):
    context.driver.get("http://cookbook.seleniumacademy.com/fundTransfer.html")

@when('he enters {name} as payee name')
def step_he_enters_payee_name(context, name):
    context.driver.find_element_by_id("payee").send_keys(name)

@when('he enters {amount} as amount')
def step_he_enters_amount(context, amount):
    context.driver.find_element_by_id("amount").send_keys(amount)

@when('he Submits request for Fund Transfer')
def step_he_enters_amount(context):
    context.driver.find_element_by_id("transfer").click()

@then('ensure the fund transfer is complete with {text} message')
def step_ensure_fund_transfer_is_complete(context, text):
    assert context.driver.find_element_by_id("message").text == text

@then('ensure a transaction failure message {text} is displayed')
def step_ensure_fund_transfer_is_complete(context, text):
    assert context.driver.find_element_by_id("message").text == text
