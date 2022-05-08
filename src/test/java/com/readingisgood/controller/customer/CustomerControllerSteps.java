package com.readingisgood.controller.customer;

import com.readingisgood.controller.CustomerController;
import com.readingisgood.dto.CustomerRequestDto;
import com.readingisgood.entity.BookEntity;
import com.readingisgood.entity.CustomerEntity;
import com.readingisgood.entity.OrderEntity;
import com.readingisgood.enums.Status;
import com.readingisgood.service.customer.CustomerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Umut Ismet Erdolu
 */
public class CustomerControllerSteps {
    private CustomerController customerController;
    private CustomerService customerService;
    private CustomerRequestDto customerRequestDto;
    private List<OrderEntity> returnedOrderEntityList;
    private List<OrderEntity> expectedOrderEntityList;
    private String response = "";

    private void initialize() {
        customerService = Mockito.mock(CustomerService.class);
        customerController = new CustomerController(customerService);
    }

    @Given("I have a customer and CustomerController")
    public void getCustomerAndController() {
        initialize();
        customerRequestDto = new CustomerRequestDto("Umut", "Erdolu", "umutismeterdolu@gmail.com");
    }

    @When("I try to register this customer to system and I call CustomerController")
    public void registerNewCustomer() {
        Mockito.when(customerService.registerNewCustomer(any(CustomerRequestDto.class))).thenReturn("Success");
        response = customerController.registerNewCustomer(customerRequestDto);
    }

    @Then("CustomerController should be register customer to system")
    public void checkRegisterNewCustomer() {
        Assert.assertEquals("Success", response);
    }

    @When("I try to list this customer's orders and I call CustomerController")
    public void ordersOfCustomer() {
        expectedOrderEntityList = getOrderEntityList();
        Mockito.when(customerService.ordersOfCustomer(anyString())).thenReturn(expectedOrderEntityList);
        returnedOrderEntityList = customerController.ordersOfCustomer(customerRequestDto.email());
    }

    @Then("CustomerController should be list orders of customer")
    public void checkOrdersOfCustomer() {
        Assert.assertEquals(expectedOrderEntityList.get(0).orderId(), returnedOrderEntityList.get(0).orderId());
    }

    private List<OrderEntity> getOrderEntityList() {
        CustomerEntity customer = new CustomerEntity(customerRequestDto.name(),
                customerRequestDto.surname(), customerRequestDto.email());
        BookEntity book = new BookEntity("Alamut Kalesi", 1, 15.00);
        OrderEntity order = new OrderEntity("1", customer, book, Status.CONFIRMED, LocalDate.now());
        List<OrderEntity> entities = new ArrayList<>();
        entities.add(order);
        return entities;
    }
}
