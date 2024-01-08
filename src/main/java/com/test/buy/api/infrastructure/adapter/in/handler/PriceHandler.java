package com.test.buy.api.infrastructure.adapter.in.handler;

import com.test.buy.api.domain.usecase.GetPriceByBrandAndProductAndApplicationDateUseCase;
import com.test.buy.api.infrastructure.adapter.in.dto.RequestQueryPrice;
import com.test.buy.api.infrastructure.adapter.in.dto.ResponseQueryPrice;
import com.test.buy.api.infrastructure.adapter.in.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceHandler {
    private final GetPriceByBrandAndProductAndApplicationDateUseCase getPriceByBrandAndProductAndApplicationDateUseCase;

    public ResponseQueryPrice getPriceByBrandAndProductAndApplicationDate(RequestQueryPrice request) {
        return PriceMapper
                .responsePriceFromPrice(getPriceByBrandAndProductAndApplicationDateUseCase
                        .execute(PriceMapper.queryPriceFromRequestQueryPrice(request)));
    }

}
