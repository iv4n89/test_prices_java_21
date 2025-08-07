package com.test.domain.model;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;

import com.test.domain.AggregateRoot;
import com.test.domain.exceptions.PriceDomainException;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.Money;
import com.test.domain.valueObject.PriceList;
import com.test.domain.valueObject.Priority;
import com.test.domain.valueObject.ProductId;

public final class PriceModel implements AggregateRoot {
    private final ProductId productId;
    private final BrandId brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final PriceList priceList;
    private final Priority priority;
    private final Money price;
    private final Currency currency;

    private PriceModel(Builder builder) {
        if (builder.startDate.isAfter(builder.endDate)) {
            throw new PriceDomainException("Start date cannot be after end date");
        }

        brandId = builder.brandId;
        productId = builder.productId;
        startDate = builder.startDate;
        endDate = builder.endDate;
        priceList = builder.priceList;
        priority = builder.priority;
        price = builder.price;
        currency = builder.currency;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer comparePriority(PriceModel priceModel) {
        return this.priority.getValue() - priceModel.priority.getValue();
    }

    public BrandId getBrandId() {
        return brandId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public Priority getPriority() {
        return priority;
    }

    public Money getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public static final class Builder {
        private BrandId brandId;
        private ProductId productId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private PriceList priceList;
        private Priority priority;
        private Money price;
        private Currency currency;

        private Builder() {
        }

        public Builder brandId(BrandId brandId) {
            this.brandId = brandId;
            return this;
        }

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder priceList(PriceList priceList) {
            this.priceList = priceList;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public PriceModel build() {
            return new PriceModel(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceModel that = (PriceModel) o;
        return Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(priceList, that.priceList)
                && Objects.equals(productId, that.productId)
                && Objects.equals(priority, that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, priceList, productId, priority);
    }
}
