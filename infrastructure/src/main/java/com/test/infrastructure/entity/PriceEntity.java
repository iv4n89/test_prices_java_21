package com.test.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prices")
@IdClass(PriceEntityId.class)
public class PriceEntity {
    @Id
    private Long brandId;

    @Id
    private Long productId;

    @Id
    private LocalDateTime startDate;

    @Id
    private LocalDateTime endDate;

    @Id
    private Integer priority;

    private BigDecimal price;

    private Long priceList;

    private String curr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceEntity that = (PriceEntity) o;
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
}
