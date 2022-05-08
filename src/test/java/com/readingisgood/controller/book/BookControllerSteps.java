package com.readingisgood.controller.book;

import com.readingisgood.controller.BookController;
import com.readingisgood.dto.BookRequestDto;
import com.readingisgood.service.book.BookService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author Umut Ismet Erdolu
 */
public class BookControllerSteps {

    private BookController bookController;
    private BookService bookService;
    private BookRequestDto bookRequestDto;
    private String response = "";


    private void initialize() {
        bookService = Mockito.mock(BookService.class);
        bookController = new BookController(bookService);
    }

    @Given("I have a book and BookController")
    public void getBookAndController() {
        initialize();
        bookRequestDto = new BookRequestDto("Alamut Kalesi", 1, 15.00);
    }

    @When("I try to register this book to system and I call BookController")
    public void registerNewBook() {
        Mockito.when(bookService.registerNewBook(any(BookRequestDto.class))).thenReturn("Success");
        response = bookController.registerNewBook(bookRequestDto);
    }

    @Then("BookController should be register book to system")
    public void checkRegisterNewBook() {
        Assert.assertEquals("Success", response);
    }

    @When("I try to update this book's stock and I call BookController")
    public void updateBookStock() {
        bookRequestDto = new BookRequestDto("Alamut Kalesi", 3, 15.00);
        Mockito.when(bookService.updateBookStock(any(BookRequestDto.class))).thenReturn("Success");
        response = bookController.updateBookStock(bookRequestDto);
    }

    @Then("BookController should be update stock of book")
    public void checkUpdateBookStock() {
        Assert.assertEquals("Success", response);
    }
}
