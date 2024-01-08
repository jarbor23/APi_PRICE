package com.test.buy.api.infrastructure.adapter.out.jpa.price;

import com.test.buy.api.domain.command.QueryPriceCommand;
import com.test.buy.api.domain.command.PriceCommand;
import com.test.buy.api.domain.ports.out.PriceRepositoryPort;
import com.test.buy.api.infrastructure.adapter.out.jpa.price.entity.EntityPrice;
import com.test.buy.api.infrastructure.adapter.out.jpa.price.mapper.MapperEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {
    private final PriceRepository priceRepository;
    @Override
    public List<PriceCommand> getPricesByBrandAndProductAndApplicationDate(QueryPriceCommand queryPriceCommand) {
        List<EntityPrice> resultQuery = priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
                queryPriceCommand.getBrandId(),
                queryPriceCommand.getProductId(),
                queryPriceCommand.getApplicationDate(),
                queryPriceCommand.getApplicationDate()
        );

        return MapperEntity.modelsFromEntities(resultQuery);
    }
}
