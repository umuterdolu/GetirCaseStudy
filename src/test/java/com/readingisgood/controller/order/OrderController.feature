Feature:

  @OrderControllerTest

  Scenario: Test take new Order
    Given I have an order and OrderController
    When  I try to take this order and I call OrderController
    Then  OrderController should be take order

  Scenario: Test query order by id
    Given I have an order id and OrderController
    When  I try to see details of order and I call OrderController
    Then  OrderController should be show order details

  Scenario: Test query order by date interval
    Given I have a startDate, endDate and OrderController
    When  I try to list orders between startDate-endDate and I call OrderController
    Then  OrderController should be list orders