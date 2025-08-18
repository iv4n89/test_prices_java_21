package com.test.infrastructure.exception;

import com.test.application.exception.PriceNotFoundException;
import com.test.domain.model.PriceModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class PriceRepositoryErrorHandler {
    public Optional<PriceModel> handleRepositoryError() {

        throw createNotFoundException();
    }

    public PriceNotFoundException createNotFoundException() {
        String message = "Price not found";

        return new PriceNotFoundException(message);
    }
}
