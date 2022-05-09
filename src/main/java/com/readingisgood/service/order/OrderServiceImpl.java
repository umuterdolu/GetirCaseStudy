package com.readingisgood.service.order;

import com.readingisgood.constants.ExceptionMessagesConstants;
import com.readingisgood.constants.ResponseMessagesConstants;
import com.readingisgood.dto.OrderRequestDto;
import com.readingisgood.entity.BookEntity;
import com.readingisgood.entity.CustomerEntity;
import com.readingisgood.entity.OrderEntity;
import com.readingisgood.enums.Status;
import com.readingisgood.exception.BookNotFoundException;
import com.readingisgood.exception.CustomerNotFoundException;
import com.readingisgood.exception.InsufficientBookStockException;
import com.readingisgood.exception.OrderNotFoundException;
import com.readingisgood.repository.BookRepository;
import com.readingisgood.repository.CustomerRepository;
import com.readingisgood.repository.OrderRepository;
import com.readingisgood.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author Umut Ismet Erdolu
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Override
    public String takeNewOrder(OrderRequestDto orderRequestDto) {
        String email = orderRequestDto.email();
        CustomerEntity customer = Optional
                .ofNullable(customerRepository.findByEmail(email))
                .orElseThrow(() -> new CustomerNotFoundException(
                        ExceptionMessagesConstants.CUSTOMER_IS_NOT_FOUND));

        String bookName = orderRequestDto.bookName();
        BookEntity book = Optional
                .ofNullable(bookRepository.findBy(orderRequestDto.bookName()))
                .orElseThrow(() -> new BookNotFoundException(
                        ExceptionMessagesConstants.BOOK_IS_NOT_FOUND.formatted(bookName)));

        validateBookStock(book.stock(), orderRequestDto.count());

        Optional<OrderEntity> optionalOrder = Optional.ofNullable(orderRepository.findByCustomerAndBook(customer, book));

        if (optionalOrder.isPresent()) {
            logger.log(Level.INFO, orderRequestDto);
            OrderEntity order = optionalOrder.get();
            return responseAndLogMessage(ResponseMessagesConstants.ORDER_IS_ALREADY_HAVE
                    .formatted(order.orderId(), order.status()));
        } else {
            String orderId = generateId();
            orderRepository.save(
                    new OrderEntity(orderId, customer, book,
                            Status.CONFIRMED, LocalDate.now()));
            bookService.updateBookStock(bookName, book.stock() - orderRequestDto.count(), book.price());
            return responseAndLogMessage(ResponseMessagesConstants.ORDER_HAS_BEEN_TAKEN.formatted(orderId));
        }
    }

    @Override
    public OrderEntity orderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new OrderNotFoundException(ExceptionMessagesConstants.ORDER_IS_NOT_FOUND));
    }

    @Override
    public List<OrderEntity> orderByDateInterval(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    private void validateBookStock(int bookStock, int count) {
        if (bookStock < 1 || bookStock < count) {
            throw new InsufficientBookStockException(
                    ExceptionMessagesConstants.INSUFFICIENT_BOOK_STOCK.formatted(bookStock));
        }
    }

    private String generateId() {
        Random random = new Random(System.nanoTime());
        return String.valueOf(random.nextInt(1000000));
    }

    private String responseAndLogMessage(String message) {
        logger.log(Level.INFO, message);
        return message;
    }
}
