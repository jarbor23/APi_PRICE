package com.test.buy.api.infrastructure.adapter.in.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseQueryPrice {
    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private Double price;

}
