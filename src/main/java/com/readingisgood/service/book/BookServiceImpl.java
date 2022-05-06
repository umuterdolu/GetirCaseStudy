package com.readingisgood.service.book;

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
            return responseAndLogMessage(ResponseMessagesConstants.BOOK_IS_ALREADY_HAVE_REGISTERED, bookName);
        } else {
            BookEntity newBook = new BookEntity(bookRequestDto.bookName(), bookRequestDto.stock());
            bookRepository.save(newBook);
            return responseAndLogMessage(ResponseMessagesConstants.BOOK_HAS_BEEN_REGISTERED, bookName);
        }
    }

    @Override
    public String updateBookStock(String bookName, int stock) {
        Optional<BookEntity> bookEntity = Optional.ofNullable(bookRepository.findBy(bookName));

        if (bookEntity.isPresent()) {
            bookRepository.save(new BookEntity(bookName, stock));
            return responseAndLogMessage(ResponseMessagesConstants.BOOK_STOCK_HAS_BEEN_UPDATED, bookName, stock);
        } else {
            throw new BookNotFoundException();
        }

    }

    private String responseAndLogMessage(String message, String bookName) {
        logger.log(Level.INFO, message, bookName);
        return message.formatted(bookName);
    }

    private String responseAndLogMessage(String message, String bookName, int stock) {
        logger.log(Level.INFO, message, bookName, stock);
        return message.formatted(bookName, stock);
    }

}
