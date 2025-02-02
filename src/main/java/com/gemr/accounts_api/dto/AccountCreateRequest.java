package com.gemr.accounts_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record AccountCreateRequest(
        @NotBlank(message = "clientName is mandatory")
        String clientName,

        @NotBlank(message = "accountNumber is mandatory")
        @Pattern(regexp = "\\d+", message = "The account number must contain only numbers.")
        String accountNumber,

        @NotNull(message = "balance is mandatory")
        BigDecimal balance
) {
}
