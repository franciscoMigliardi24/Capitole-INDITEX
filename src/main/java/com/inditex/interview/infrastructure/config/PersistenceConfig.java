package com.inditex.interview.infrastructure.config;

import com.inditex.interview.application.PriceUseCase;
import com.inditex.interview.domain.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

    @Bean
    public PriceUseCase priceUseCase(PriceRepositoryPort priceRepositoryPort) {
        return new PriceUseCase(priceRepositoryPort);
    }
}