package com.test.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public final class ErrorDto {
    private final String code;
    private final String message;
}
