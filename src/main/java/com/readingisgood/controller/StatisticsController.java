package com.readingisgood.controller;

import com.readingisgood.dto.StatisticResponseDto;
import com.readingisgood.service.statistic.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Umut Ismet Erdolu
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatisticsController {

    private final StatisticService statisticService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customerOrderStatistics/{email}")
    public List<StatisticResponseDto> getCustomerOrderStatistics(@PathVariable String email) {
        return statisticService.getCustomerOrderStatistics(email);
    }
}
