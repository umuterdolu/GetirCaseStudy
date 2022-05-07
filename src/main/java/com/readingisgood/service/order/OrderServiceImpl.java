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
import java.util.UUID;

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
        String customerId = orderRequestDto.customerId();
        CustomerEntity customer = Optional
                .ofNullable(customerRepository.findBy(customerId))
                .orElseThrow(() -> new CustomerNotFoundException(
                        ExceptionMessagesConstants.CUSTOMER_IS_NOT_FOUND.formatted(customerId)));

        String bookName = orderRequestDto.bookName();
        BookEntity book = Optional
                .ofNullable(bookRepository.findBy(orderRequestDto.bookName()))
                .orElseThrow(() -> new BookNotFoundException(
                        ExceptionMessagesConstants.BOOK_IS_NOT_FOUND.formatted(bookName)));

        Optional<OrderEntity> optionalOrder = Optional.ofNullable(orderRepository.findByCustomerAndBook(customer, book));

        if (optionalOrder.isPresent()) {
            logger.log(Level.INFO, orderRequestDto);
            OrderEntity order = optionalOrder.get();
            return responseAndLogMessage(ResponseMessagesConstants.ORDER_IS_ALREADY_HAVE
                    .formatted(order.orderId(), order.status()));
        } else {
            OrderEntity order = orderRepository.save(
                    new OrderEntity(UUID.randomUUID().toString(), customer, book,
                            Status.CONFIRMED, LocalDate.now()));
            bookService.updateBookStock(bookName, book.stock() - orderRequestDto.count());
            return responseAndLogMessage(ResponseMessagesConstants.ORDER_HAS_BEEN_TAKEN.formatted(order.orderId()));
        }
    }

    @Override
    public List<OrderEntity> orderById() {
        return orderRepository.findAll()
                .stream()
                .sorted()
                .toList();
    }

    @Override
    public List<OrderEntity> orderByDateInterval(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    private String responseAndLogMessage(String message) {
        logger.log(Level.INFO, message);
        return message;
    }
}
