Feature:

  @CustomerControllerTest

  Scenario: Test register new customer
    Given I have a customer and CustomerController
    When  I try to register this customer to system and I call CustomerController
    Then  CustomerController should be register customer to system

  Scenario: Test list orders of customer
    Given I have a customer and CustomerController
    When  I try to list this customer's orders and I call CustomerController
    Then  CustomerController should be list orders of customer