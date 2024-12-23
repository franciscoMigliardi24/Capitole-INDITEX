package com.inditex.interview.infrastructure.h2;

import com.inditex.interview.infrastructure.h2.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("""
           SELECT p 
             FROM PriceEntity p 
            WHERE p.brandId = :brandId
              AND p.productId = :productId
              AND :applicationDate BETWEEN p.startDate AND p.endDate
            ORDER BY p.priority DESC
           """)
    List<PriceEntity> findApplicablePrices(Integer brandId, Long productId, LocalDateTime applicationDate);
}
