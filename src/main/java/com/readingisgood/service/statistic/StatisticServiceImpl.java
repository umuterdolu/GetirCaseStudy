package com.readingisgood.service.statistic;

import com.readingisgood.dto.StatisticResponseDto;
import com.readingisgood.entity.OrderEntity;
import com.readingisgood.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Umut Ismet Erdolu
 */
@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final CustomerService customerService;

    @Override
    @SuppressWarnings("unchecked")
    public List<StatisticResponseDto> getCustomerOrderStatistics(String email) {
        List<OrderEntity> orderEntityList = customerService.ordersOfCustomer(email);

        Map<Month, Long> totalOrderCountMapMonthly = (Map<Month, Long>) collectOrderMap(orderEntityList,
                o -> o.orderDate().getMonth(), Collectors.counting());

        Map<Month, Integer> totalBookCountMapMonthly = (Map<Month, Integer>) collectOrderMap(orderEntityList,
                o -> o.orderDate().getMonth(), Collectors.mapping(
                        OrderEntity::book, Collectors.counting()));

        Map<Month, Double> totalPurchaseAmountMapMonthly = (Map<Month, Double>) collectOrderMap(orderEntityList,
                o -> o.orderDate().getMonth(), Collectors.summingDouble(b -> b.book().price()));

        List<StatisticResponseDto> statisticResponseDtoList = new ArrayList<>(orderEntityList.size());

        LocalDate now = LocalDate.now();
        for (int i = 1; i < now.getMonthValue() + 1; i++) {
            int monthValue = i;
            Month month = Month.of(monthValue);
            orderEntityList.forEach(o -> {
                if (o.orderDate().getMonthValue() == monthValue) {
                    statisticResponseDtoList.add(
                            new StatisticResponseDto(now.getMonth().name(),
                                    totalOrderCountMapMonthly.get(month).intValue(),
                                    totalBookCountMapMonthly.get(month),
                                    totalPurchaseAmountMapMonthly.get(month)
                            ));
                }
            });
        }

        return statisticResponseDtoList;
    }

    private Map<?, ?> collectOrderMap(List<OrderEntity> orderEntityList, Function<OrderEntity, ?> function,
                                      Collector<OrderEntity, ?, ?> collector) {
        return orderEntityList
                .stream()
                .collect(Collectors
                        .groupingBy(function, collector)
                );
    }
}
