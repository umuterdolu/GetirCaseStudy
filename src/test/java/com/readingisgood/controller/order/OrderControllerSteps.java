package com.readingisgood.controller.order;

import com.readingisgood.controller.OrderController;
import com.readingisgood.dto.OrderRequestDto;
import com.readingisgood.entity.BookEntity;
import com.readingisgood.entity.CustomerEntity;
import com.readingisgood.entity.OrderEntity;
import com.readingisgood.enums.Status;
import com.readingisgood.service.order.OrderService;
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
public class OrderControllerSteps {

    private OrderController orderController;
    private OrderService orderService;
    private OrderRequestDto orderRequestDto;
    private OrderEntity expectedOrder;
    private OrderEntity returnedOrder;
    private List<OrderEntity> expectedOrderList;
    private List<OrderEntity> returnedOrderList;
    private String response = "";
    private String orderId = "";
    private String startDate = "";
    private String endDate = "";

    private void initialize() {
        orderService = Mockito.mock(OrderService.class);
        orderController = new OrderController(orderService);
    }

    @Given("I have an order and OrderController")
    public void getOrderAndController() {
        initialize();
        orderRequestDto = new OrderRequestDto("Alamut Kalesi", "umutismeterdolu@gmail.com", 1);
    }

    @When("I try to take this order and I call OrderController")
    public void takeNewOrder() {
        Mockito.when(orderService.takeNewOrder(any(OrderRequestDto.class))).thenReturn("Success");
        response = orderController.takeNewOrder(orderRequestDto);
    }

    @Then("OrderController should be take order")
    public void checkTakeNewOrder() {
        Assert.assertEquals("Success", response);
    }

    @Given("I have an order id and OrderController")
    public void getOrderIdAndController() {
        initialize();
        orderId = "1";
    }

    @When("I try to see details of order and I call OrderController")
    public void orderById() {
        expectedOrder = getOrderEntity();
        Mockito.when(orderService.orderById(anyString())).thenReturn(expectedOrder);
        returnedOrder = orderController.orderById(orderId);
    }

    @Then("OrderController should be show order details")
    public void checkOrderById() {
        Assert.assertEquals(expectedOrder.orderId(), returnedOrder.orderId());
    }

    @Given("I have a startDate, endDate and OrderController")
    public void getDatesAndController() {
        initialize();
        startDate = "2022-01-01";
        endDate = "2022-05-10";
    }

    @When("I try to list orders between startDate-endDate and I call OrderController")
    public void orderByDateInterval() {
        expectedOrderList = getOrderEntityList();
        Mockito.when(orderService.orderByDateInterval(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(expectedOrderList);
        returnedOrderList = orderController.orderByDateInterval(startDate, endDate);
    }

    @Then("OrderController should be list orders")
    public void checkOrderByDateInterval() {
        Assert.assertEquals(expectedOrderList.get(0).orderId(),
                returnedOrderList.get(0).orderId());
    }

    private OrderEntity getOrderEntity() {
        CustomerEntity customer = new CustomerEntity("Umut",
                "Erdolu", "umutismeterdolu@gmail.com");
        BookEntity book = new BookEntity("Alamut Kalesi", 1, 15.00);
        return new OrderEntity("1", customer, book, Status.CONFIRMED, LocalDate.now());
    }

    private List<OrderEntity> getOrderEntityList() {
        List<OrderEntity> orders = new ArrayList<>();
        orders.add(getOrderEntity());
        return orders;
    }
}
