package com.readingisgood.service.customer;

import com.readingisgood.constants.ResponseMessagesConstants;
import com.readingisgood.dto.CustomerRequestDto;
import com.readingisgood.entity.CustomerEntity;
import com.readingisgood.entity.OrderEntity;
import com.readingisgood.exception.CustomerNotFoundException;
import com.readingisgood.repository.CustomerRepository;
import com.readingisgood.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

/**
 * @author Umut Ismet Erdolu
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private Page<OrderEntity> orderEntityPage;
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;
    @Captor
    private ArgumentCaptor<CustomerEntity> entityArgumentCaptor;
    @Captor
    private ArgumentCaptor<Pageable> pageableArgumentCaptor;
    private CustomerRequestDto customerRequestDto;
    private CustomerEntity customerEntity;

    @Before
    public void setup() {
        customerService = new CustomerServiceImpl(customerRepository, orderRepository);
        customerRequestDto = new CustomerRequestDto("Umut", "Erdolu", "umutismeterdolu@gmail.com");
        customerEntity = new CustomerEntity(customerRequestDto.name(), customerRequestDto.surname(),
                customerRequestDto.email());
    }

    @Test
    public void testRegisterNewCustomerIsAlreadyExist() {
        Mockito.when(customerRepository.findByEmail(stringArgumentCaptor.capture())).thenReturn(customerEntity);
        String response = customerService.registerNewCustomer(customerRequestDto);

        assertThat(stringArgumentCaptor.getValue())
                .isNotNull()
                .isExactlyInstanceOf(String.class);
        Assert.assertEquals(ResponseMessagesConstants.CUSTOMER_IS_ALREADY_REGISTERED, response);
    }

    @Test
    public void testRegisterNewCustomerHasBeenRegistered() {
        Mockito.when(customerRepository.findByEmail(stringArgumentCaptor.capture())).thenReturn(null);
        String response = customerService.registerNewCustomer(customerRequestDto);

        assertThat(stringArgumentCaptor.getValue())
                .isNotNull()
                .isExactlyInstanceOf(String.class);
        Assert.assertEquals(ResponseMessagesConstants.CUSTOMER_HAS_BEEN_REGISTERED, response);
    }

    @Test
    public void testOrdersOfCustomerSuccessfully() {
        Mockito.when(customerRepository.findByEmail(stringArgumentCaptor.capture())).thenReturn(customerEntity);
        Mockito.when(orderRepository.findAllByCustomer(entityArgumentCaptor.capture(),
                pageableArgumentCaptor.capture())).thenReturn(orderEntityPage);
        Mockito.when(orderEntityPage.toList()).thenReturn(new ArrayList<>());

        List<OrderEntity> orderEntityList = customerService.ordersOfCustomer(customerRequestDto.email());

        assertThat(stringArgumentCaptor.getValue())
                .isNotNull()
                .isExactlyInstanceOf(String.class);
        assertThat(entityArgumentCaptor.getValue())
                .isNotNull()
                .isExactlyInstanceOf(CustomerEntity.class);
        Assert.assertNotNull(orderEntityList);
        Assert.assertEquals(orderEntityList, orderEntityPage.toList());
    }

    @Test
    public void testOrdersOfCustomerThrowException() {
        Mockito.when(customerRepository.findByEmail(stringArgumentCaptor.capture())).thenReturn(null);
        Throwable throwable = catchThrowable(() -> customerService.ordersOfCustomer(customerRequestDto.email()));

        assertThat(throwable)
                .isNotNull()
                .isExactlyInstanceOf(CustomerNotFoundException.class);
    }
}
