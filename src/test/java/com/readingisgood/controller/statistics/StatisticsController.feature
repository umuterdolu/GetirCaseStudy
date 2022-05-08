Feature:

  @StatisticsControllerTest

  Scenario: Test Monthly Customer's Order Statistics
    Given I have a customer and StatisticsController
    When  I try to serve this customer's monthly statistics and I call StatisticsController
    Then  StatisticsController should be serve statistics