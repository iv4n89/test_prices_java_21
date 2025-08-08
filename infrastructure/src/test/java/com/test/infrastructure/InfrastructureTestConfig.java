package com.test.infrastructure;

import com.test.domain.ports.output.repository.PriceRepository;
import com.test.infrastructure.adapters.output.repository.H2PriceRepository;
import com.test.infrastructure.repository.PriceJpaRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@SpringBootApplication(scanBasePackages = "com.test")
public class InfrastructureTestConfig {
    @Bean(name = "priceJpaRepositoryTest")
    public PriceJpaRepository priceJpaRepository() {
        return mock(PriceJpaRepository.class);
    }

    @Bean(name = "priceRepositoryTest")
    public PriceRepository priceRepository() {
        return new H2PriceRepository(priceJpaRepository());
    }
}
