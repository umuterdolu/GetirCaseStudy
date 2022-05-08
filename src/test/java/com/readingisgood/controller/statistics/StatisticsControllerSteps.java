package com.readingisgood.controller.statistics;

import com.readingisgood.controller.StatisticsController;
import com.readingisgood.dto.CustomerRequestDto;
import com.readingisgood.dto.StatisticResponseDto;
import com.readingisgood.service.statistic.StatisticService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Umut Ismet Erdolu
 */
public class StatisticsControllerSteps {
    private StatisticsController statisticsController;
    private StatisticService statisticService;
    private CustomerRequestDto customerRequestDto;
    private List<StatisticResponseDto> returnedStatisticResponseDtoList;
    private List<StatisticResponseDto> expectedStatisticResponseDtoList;

    private void initialize() {
        statisticService = Mockito.mock(StatisticService.class);
        statisticsController = new StatisticsController(statisticService);
    }

    @Given("I have a customer and StatisticsController")
    public void getCustomerAndController() {
        initialize();
        customerRequestDto = new CustomerRequestDto("Umut", "Erdolu", "umutismeterdolu@gmail.com");
    }

    @When("I try to serve this customer's monthly statistics and I call StatisticsController")
    public void registerNewCustomer() {
        expectedStatisticResponseDtoList = getStatisticResponseList();
        Mockito.when(statisticService.getCustomerOrderStatistics(anyString())).thenReturn(expectedStatisticResponseDtoList);
        returnedStatisticResponseDtoList = statisticsController.getCustomerOrderStatistics(customerRequestDto.email());
    }

    @Then("StatisticsController should be serve statistics")
    public void checkRegisterNewCustomer() {
        Assert.assertEquals(expectedStatisticResponseDtoList.get(0).month(),
                returnedStatisticResponseDtoList.get(0).month());
    }

    private List<StatisticResponseDto> getStatisticResponseList() {
        List<StatisticResponseDto> statisticResponseDtoList = new ArrayList<>();
        StatisticResponseDto statisticResponseDto = new StatisticResponseDto("May", 5,
                2, 119.45);
        statisticResponseDtoList.add(statisticResponseDto);
        return statisticResponseDtoList;
    }
}
