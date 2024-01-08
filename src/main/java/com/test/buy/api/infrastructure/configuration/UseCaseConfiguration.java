package com.test.buy.api.infrastructure.configuration;


import com.test.buy.api.domain.ports.out.PriceRepositoryPort;
import com.test.buy.api.domain.usecase.GetPriceByBrandAndProductAndApplicationDateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    public GetPriceByBrandAndProductAndApplicationDateUseCase getPriceByBrandAndProductAndApplicationDateUseCase(final PriceRepositoryPort priceRepositoryPort) {
        return new GetPriceByBrandAndProductAndApplicationDateUseCase(priceRepositoryPort);
    }
}
