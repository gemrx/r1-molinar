package com.gemr.accounts_api.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountUpdateRequest(
        @NotNull(message = "newBalance is mandatory")
        BigDecimal newBalance
) {
}
