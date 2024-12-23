package com.inditex.interview.infrastructure.h2;

import com.inditex.interview.domain.PriceRepositoryPort;
import com.inditex.interview.domain.model.Price;
import com.inditex.interview.infrastructure.h2.model.PriceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository jpaRepository;

    public PriceRepositoryAdapter(PriceJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Price> findApplicablePrices(Integer brandId, Long productId, LocalDateTime applicationDate) {
        List<PriceEntity> entities = jpaRepository.findApplicablePrices(brandId, productId, applicationDate);
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

    private Price toDomain(PriceEntity entity) {
        return new Price(
                entity.getId(),
                entity.getBrandId(),
                entity.getPriceList(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurr()
        );
    }
}
