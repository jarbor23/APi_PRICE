package com.test.buy.api.infrastructure.adapter.in.mapper;

import com.test.buy.api.domain.command.QueryPriceCommand;
import com.test.buy.api.domain.command.PriceCommand;
import com.test.buy.api.infrastructure.adapter.in.dto.RequestQueryPrice;
import com.test.buy.api.infrastructure.adapter.in.dto.ResponseQueryPrice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceMapper {
    public static ResponseQueryPrice responsePriceFromPrice(PriceCommand priceCommand) {
        return ResponseQueryPrice.builder()
                .price(priceCommand.getPrice())
                .priceList(priceCommand.getPriceList())
                .brandId(priceCommand.getBrandId())
                .productId(priceCommand.getProductId())
                .build();
    }
    public static QueryPriceCommand queryPriceFromRequestQueryPrice(RequestQueryPrice requestQueryPrice){
        return QueryPriceCommand.builder()
                .brandId(requestQueryPrice.getBrandId())
                .applicationDate(convertStringToLocalDateTime(requestQueryPrice.getApplicationDate()))
                .productId(requestQueryPrice.getProductId())
                .build();
    }
    public static LocalDateTime convertStringToLocalDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }


}
