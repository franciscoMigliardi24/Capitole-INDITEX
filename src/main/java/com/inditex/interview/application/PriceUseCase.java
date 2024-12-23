package com.inditex.interview.application;

import com.inditex.interview.domain.GetPricePort;
import com.inditex.interview.domain.PriceRepositoryPort;
import com.inditex.interview.domain.model.Price;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PriceUseCase implements GetPricePort {

    private final PriceRepositoryPort priceRepository;

    public PriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepository = priceRepositoryPort;
    }

    @Override
    public Optional<Price> getApplicablePrice(Integer brandId, Long productId, LocalDateTime applicationDate) {
        List<Price> prices = priceRepository.findApplicablePrices(brandId, productId, applicationDate);

        if (prices.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(prices.getFirst());
    }
}
