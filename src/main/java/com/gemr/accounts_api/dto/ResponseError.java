package com.gemr.accounts_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseError(
        String code,
        String message,
        List<ValidationError> errors
) {
    public record ValidationError(String field, String message) {}
}
