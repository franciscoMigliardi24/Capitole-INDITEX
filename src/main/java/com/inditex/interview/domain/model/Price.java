package com.inditex.interview.domain.model;

import java.time.LocalDateTime;

public record Price(
        Long productId,
        Integer brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priority,
        Double price,
        String currency
) {}
