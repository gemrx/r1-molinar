package com.gemr.accounts_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountCreateRequest(
        @NotBlank(message = "clientName is mandatory")
        String clientName,

        @NotBlank(message = "accountNumber is mandatory")
        String accountNumber,

        @NotNull(message = "balance is mandatory")
        BigDecimal balance
) {
}
