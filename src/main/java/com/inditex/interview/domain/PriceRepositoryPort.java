package com.inditex.interview.domain;

import com.inditex.interview.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findApplicablePrices(Integer brandId, Long productId, LocalDateTime applicationDate);

}
