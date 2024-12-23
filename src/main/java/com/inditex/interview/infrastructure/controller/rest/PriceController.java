package com.inditex.interview.infrastructure.controller.rest;

import com.inditex.interview.application.PriceUseCase;
import com.inditex.interview.domain.GetPricePort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final GetPricePort priceUseCase;

    public PriceController(PriceUseCase priceUseCase) {
        this.priceUseCase = priceUseCase;
    }

    @GetMapping
    public PriceResponse getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Integer brandId
    ) {
        return priceUseCase.getApplicablePrice(brandId, productId, applicationDate)
                .map(price -> new PriceResponse(
                        price.productId(),
                        price.brandId(),
                        price.priceList(),
                        price.startDate(),
                        price.endDate(),
                        price.priority(),
                        price.price(),
                        price.currency()
                ))
                .orElseThrow(() -> new PriceNotFoundException("No hay precio aplicable para esos parámetros"));
    }

    record PriceResponse(
            Long productId,
            Integer brandId,
            Integer priceList,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Integer priority,
            Double price,
            String currency
    ) {}

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class PriceNotFoundException extends RuntimeException {
        public PriceNotFoundException(String msg) {
            super(msg);
        }
    }
}
