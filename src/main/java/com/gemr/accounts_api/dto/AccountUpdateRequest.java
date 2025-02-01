package com.gemr.accounts_api.dto;

import java.math.BigDecimal;

public record AccountUpdateRequest(
        BigDecimal newBalance
) {
}
