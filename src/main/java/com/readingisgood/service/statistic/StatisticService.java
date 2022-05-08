package com.readingisgood.service.statistic;

import com.readingisgood.dto.StatisticResponseDto;

import java.util.List;

/**
 * @author Umut Ismet Erdolu
 */
public interface StatisticService {
    List<StatisticResponseDto> getCustomerOrderStatistics(String email);
}
