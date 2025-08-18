package com.test.infrastructure;

import com.test.domain.model.PriceModel;
import com.test.domain.ports.output.repository.PriceRepository;
import com.test.infrastructure.adapters.output.repository.JpaPriceRepository;
import com.test.infrastructure.entity.PriceEntity;
import com.test.infrastructure.exception.PriceRepositoryErrorHandler;
import com.test.infrastructure.mapper.EntityMapper;
import com.test.infrastructure.mapper.PriceDataMapper;
import com.test.infrastructure.repository.PriceJpaRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@SpringBootApplication(scanBasePackages = "com.test")
public class InfrastructureTestConfig {
    @Bean(name = "priceJpaRepositoryTest")
    public PriceJpaRepository priceJpaRepository() {
        return mock(PriceJpaRepository.class);
    }

    @Bean(name = "priceMapperTest")
    @Primary
    public EntityMapper<PriceEntity, PriceModel> priceMapper() {
        return mock(PriceDataMapper.class);
    }

    @Bean(name = "priceRepositoryErrorHandlerTest")
    public PriceRepositoryErrorHandler priceRepositoryErrorHandler() {
        return mock(PriceRepositoryErrorHandler.class);
    }

    @Bean(name = "priceRepositoryTest")
    public PriceRepository priceRepository() {
        return new JpaPriceRepository(priceJpaRepository(), priceMapper(), priceRepositoryErrorHandler());
    }
}
