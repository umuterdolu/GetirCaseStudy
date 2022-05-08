package com.readingisgood.service.book;

import com.readingisgood.dto.BookRequestDto;

/**
 * @author Umut Ismet Erdolu
 */
public interface BookService {
    String registerNewBook(BookRequestDto bookRequestDto);

    String updateBookStock(BookRequestDto bookRequestDto);

    default void updateBookStock(String bookName, int stock, double price) {
        updateBookStock(new BookRequestDto(bookName, stock, price));
    }
}
