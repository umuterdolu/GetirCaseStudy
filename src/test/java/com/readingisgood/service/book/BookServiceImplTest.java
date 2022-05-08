package com.readingisgood.service.book;

import com.readingisgood.constants.ResponseMessagesConstants;
import com.readingisgood.dto.BookRequestDto;
import com.readingisgood.entity.BookEntity;
import com.readingisgood.exception.BookNotFoundException;
import com.readingisgood.repository.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * @author Umut Ismet Erdolu
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;
    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;
    private BookEntity bookEntity;
    private BookRequestDto bookRequestDto;

    @Before
    public void setup() {
        bookService = new BookServiceImpl(bookRepository);
        bookRequestDto = new BookRequestDto("Alamut Kalesi", 1, 15.00);
        bookEntity = new BookEntity(bookRequestDto.bookName(), bookRequestDto.stock(), bookRequestDto.price());
    }

    @Test
    public void testRegisterNewBookIsAlreadyExist() {
        Mockito.when(bookRepository.findBy(stringArgumentCaptor.capture())).thenReturn(bookEntity);
        String response = bookService.registerNewBook(bookRequestDto);

        assertThat(stringArgumentCaptor.getValue())
                .isNotNull()
                .isExactlyInstanceOf(String.class);
        Assert.assertEquals(ResponseMessagesConstants.BOOK_IS_ALREADY_REGISTERED, response);
    }

    @Test
    public void testRegisterNewBookHasBeenRegistered() {
        Mockito.when(bookRepository.findBy(stringArgumentCaptor.capture())).thenReturn(null);
        String response = bookService.registerNewBook(bookRequestDto);

        assertThat(stringArgumentCaptor.getValue())
                .isNotNull()
                .isExactlyInstanceOf(String.class);
        Assert.assertEquals(ResponseMessagesConstants.BOOK_HAS_BEEN_REGISTERED, response);
    }

    @Test
    public void testUpdateBookStockSuccessfully() {
        Mockito.when(bookRepository.findBy(stringArgumentCaptor.capture())).thenReturn(bookEntity);
        String response = bookService.updateBookStock(bookRequestDto);

        assertThat(stringArgumentCaptor.getValue())
                .isNotNull()
                .isExactlyInstanceOf(String.class);
        Assert.assertEquals(ResponseMessagesConstants.BOOK_STOCK_HAS_BEEN_UPDATED
                .formatted(bookRequestDto.stock()), response);
        Assert.assertEquals(bookRequestDto.stock(), bookEntity.stock());
    }

    @Test
    public void testUpdateBookStockThrowException() {
        Mockito.when(bookRepository.findBy(stringArgumentCaptor.capture())).thenReturn(null);
        bookRequestDto = new BookRequestDto("Alamut Kalesi", -1, 15.00);

        Throwable throwable = catchThrowable(() -> bookService.updateBookStock(bookRequestDto));

        assertThat(throwable)
                .isNotNull()
                .isExactlyInstanceOf(BookNotFoundException.class);
    }
}
