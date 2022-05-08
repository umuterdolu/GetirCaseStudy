package com.readingisgood.service.book;

import com.readingisgood.constants.ExceptionMessagesConstants;
import com.readingisgood.constants.ResponseMessagesConstants;
import com.readingisgood.dto.BookRequestDto;
import com.readingisgood.entity.BookEntity;
import com.readingisgood.exception.BookNotFoundException;
import com.readingisgood.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Umut Ismet Erdolu
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private final BookRepository bookRepository;

    @Override
    public String registerNewBook(BookRequestDto bookRequestDto) {
        String bookName = bookRequestDto.bookName();
        Optional<BookEntity> bookEntity = Optional.ofNullable(bookRepository.findBy(bookName));

        if (bookEntity.isPresent()) {
            return responseAndLogMessage(ResponseMessagesConstants.BOOK_IS_ALREADY_REGISTERED);
        } else {
            BookEntity newBook = new BookEntity(bookRequestDto.bookName(), bookRequestDto.stock(), bookRequestDto.price());
            bookRepository.save(newBook);
            return responseAndLogMessage(ResponseMessagesConstants.BOOK_HAS_BEEN_REGISTERED);
        }
    }

    @Override
    public String updateBookStock(BookRequestDto bookRequestDto) {
        String bookName = bookRequestDto.bookName();
        int stock = bookRequestDto.stock();
        Optional<BookEntity> bookEntity = Optional.ofNullable(bookRepository.findBy(bookName));

        if (bookEntity.isPresent()) {
            logger.log(Level.INFO, "Book Name: {} --- Stock: {}", bookName, stock);
            bookRepository.save(new BookEntity(bookName, stock, bookEntity.get().price()));
            return responseAndLogMessage(ResponseMessagesConstants.BOOK_STOCK_HAS_BEEN_UPDATED.formatted(stock));
        } else {
            throw new BookNotFoundException(ExceptionMessagesConstants.BOOK_IS_NOT_FOUND.formatted(bookName));
        }

    }

    private String responseAndLogMessage(String message) {
        logger.log(Level.INFO, message);
        return message;
    }
}
