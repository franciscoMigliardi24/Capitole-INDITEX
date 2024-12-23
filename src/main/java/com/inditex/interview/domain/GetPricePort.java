package com.inditex.interview.domain;

import com.inditex.interview.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPricePort {
    Optional<Price> getApplicablePrice(Integer brandId, Long productId, LocalDateTime applicationDate);
}
