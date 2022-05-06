package com.readingisgood.controller;

import com.readingisgood.dto.BookRequestDto;
import com.readingisgood.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Umut Ismet Erdolu
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookController {

    private final BookService bookService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/registerBook")
    public String registerNewCustomer(@Valid @RequestBody BookRequestDto bookRequestDto) {
        return bookService.registerNewBook(bookRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/updateBookStock/{bookName}")
    public String updateBookStock(@PathVariable String bookName, @RequestParam int stock) {
        return bookService.updateBookStock(bookName, stock);
    }
}
