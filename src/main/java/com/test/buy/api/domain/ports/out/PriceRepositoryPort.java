package com.test.buy.api.domain.ports.out;

import com.test.buy.api.domain.command.QueryPriceCommand;
import com.test.buy.api.domain.command.PriceCommand;

import java.util.List;

public interface PriceRepositoryPort {
    List<PriceCommand> getPricesByBrandAndProductAndApplicationDate(QueryPriceCommand queryPriceCommand);
}
