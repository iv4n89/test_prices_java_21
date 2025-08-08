package com.test.infrastructure.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class PriceEntityId implements Serializable {
    private Long brandId;
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceEntityId that = (PriceEntityId) o;
        return Objects.equals(brandId, that.brandId)
                && Objects.equals(productId, that.productId)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(priority, that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, productId, startDate, endDate, priority);
    }

    @Serial private static final long serialVersionUID = 1L;
}
