package com.test.buy.api.infrastructure.adapter.out.jpa.price.mapper;

import com.test.buy.api.domain.command.PriceCommand;
import com.test.buy.api.infrastructure.adapter.out.jpa.price.entity.EntityPrice;

import java.util.List;
import java.util.stream.Collectors;

public class MapperEntity {
    public static PriceCommand modelFromEntity(EntityPrice entityPrice){
        return PriceCommand.builder()
                .price(entityPrice.getPrice())
                .brandId(entityPrice.getBrandId())
                .priority(entityPrice.getPriority())
                .productId(entityPrice.getProductId())
                .currency(entityPrice.getCurrency())
                .priceList(entityPrice.getPriceList())
                .build();
    }
    public static List<PriceCommand> modelsFromEntities(List<EntityPrice> entitiesPrices){
        return entitiesPrices.stream().map(MapperEntity::modelFromEntity).collect(Collectors.toList());
    }
}
