package com.readingisgood.service.customer;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        String idNumber = customerRequestDto.idNumber();
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findBy(idNumber));

        if (customerEntity.isPresent()) {
            return responseAndLogMessage(ResponseMessagesConstants.CUSTOMER_IS_ALREADY_HAVE_REGISTERED, idNumber);
        } else {
            CustomerEntity newCustomer = new CustomerEntity(customerRequestDto.name(), customerRequestDto.surname(),
                    idNumber);
            customerRepository.save(newCustomer);
            return responseAndLogMessage(ResponseMessagesConstants.CUSTOMER_HAS_BEEN_REGISTERED, idNumber);
        }
    }

    @Override
    public Page<OrderEntity> ordersOfCustomer(String customerId) {
        CustomerEntity customer = Optional
                .ofNullable(customerRepository.findBy(customerId))
                .orElseThrow(CustomerNotFoundException::new);
        return orderRepository.findAllByCustomer(customer, Pageable.ofSize(10));
    }

    private String responseAndLogMessage(String message, String idNumber) {
        logger.log(Level.INFO, message, idNumber);
        return message.formatted(idNumber);
    }
}