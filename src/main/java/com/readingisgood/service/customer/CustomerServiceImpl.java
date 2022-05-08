package com.readingisgood.service.customer;

import com.readingisgood.constants.ExceptionMessagesConstants;
import com.readingisgood.constants.ResponseMessagesConstants;
import com.readingisgood.dto.CustomerRequestDto;
import com.readingisgood.entity.CustomerEntity;
import com.readingisgood.entity.OrderEntity;
import com.readingisgood.exception.CustomerNotFoundException;
import com.readingisgood.repository.CustomerRepository;
import com.readingisgood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author Umut Ismet Erdolu
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    public String registerNewCustomer(CustomerRequestDto customerRequestDto) {
        String email = customerRequestDto.email();
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findByEmail(email));

        if (customerEntity.isPresent()) {
            logger.log(Level.INFO, customerRequestDto);
            return responseAndLogMessage(ResponseMessagesConstants.CUSTOMER_IS_ALREADY_REGISTERED);
        } else {
            CustomerEntity newCustomer = new CustomerEntity(customerRequestDto.name(), customerRequestDto.surname(),
                    email);
            customerRepository.save(newCustomer);
            return responseAndLogMessage(ResponseMessagesConstants.CUSTOMER_HAS_BEEN_REGISTERED);
        }
    }

    @Override
    public List<OrderEntity> ordersOfCustomer(String email) {
        CustomerEntity customer = Optional
                .ofNullable(customerRepository.findByEmail(email))
                .orElseThrow(() -> new CustomerNotFoundException(
                        ExceptionMessagesConstants.CUSTOMER_IS_NOT_FOUND));

        return orderRepository
                .findAllByCustomer(customer, Pageable.ofSize(10))
                .toList();
    }

    private String responseAndLogMessage(String message) {
        logger.log(Level.INFO, message);
        return message;
    }
}
