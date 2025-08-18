package com.test.infrastructure.adapters.output.repository;

import com.test.domain.model.PriceModel;
import com.test.domain.ports.output.repository.PriceRepository;
import com.test.domain.valueObject.BrandId;
import com.test.domain.valueObject.ProductId;
import com.test.infrastructure.entity.PriceEntity;
import com.test.infrastructure.entity.PriceEntityId;
import com.test.infrastructure.exception.PriceRepositoryErrorHandler;
import com.test.infrastructure.mapper.EntityMapper;
import com.test.infrastructure.repository.PriceJpaRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Primary
@Component("priceRepository")
public final class JpaPriceRepository extends BaseRepository<PriceEntity, PriceModel, PriceEntityId> implements PriceRepository {

    private final PriceRepositoryErrorHandler priceRepositoryErrorHandler;

    public JpaPriceRepository(PriceJpaRepository jpaRepository, EntityMapper<PriceEntity, PriceModel> priceMapper, PriceRepositoryErrorHandler priceRepositoryErrorHandler) {
        super(jpaRepository, priceMapper);
        this.priceRepositoryErrorHandler = priceRepositoryErrorHandler;
    }


    @Override
    public Optional<PriceModel> findApplicablePrice(BrandId brandId, ProductId productId, LocalDateTime applicationDate) {
        Optional<PriceEntity> entity = ((PriceJpaRepository) jpaRepository).findByBrandIdAndProductIdAndStartDateAndEndDate(brandId.getValue(), productId.getValue(), applicationDate);

        if (entity.isEmpty()) {
            throw this.priceRepositoryErrorHandler.createNotFoundException();
        }

        return entity.map(this::mapToDomain);
    }
}
