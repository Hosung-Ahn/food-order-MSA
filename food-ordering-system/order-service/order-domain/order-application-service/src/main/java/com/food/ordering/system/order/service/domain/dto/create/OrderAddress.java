package com.food.ordering.system.order.service.domain.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class OrderAddress {
    @NotNull
    private final String street;
    @NotNull
    private final String postalCode;
    @NotNull
    private final String city;
}
