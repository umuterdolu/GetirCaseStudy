Feature:

  @BookControllerTest

  Scenario: Test register new book
    Given I have a book and BookController
    When  I try to register this book to system and I call BookController
    Then  BookController should be register book to system

  Scenario: Test update book stock
    Given I have a book and BookController
    When  I try to update this book's stock and I call BookController
    Then  BookController should be update stock of book