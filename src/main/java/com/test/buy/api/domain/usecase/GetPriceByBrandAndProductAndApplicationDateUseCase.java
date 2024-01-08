package com.test.buy.api.domain.usecase;

import com.test.buy.api.domain.command.QueryPriceCommand;
import com.test.buy.api.domain.command.PriceCommand;
import com.test.buy.api.domain.exception.AmbiguityException;
import com.test.buy.api.domain.exception.PriceNotFoundException;
import com.test.buy.api.domain.ports.out.PriceRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class GetPriceByBrandAndProductAndApplicationDateUseCase implements UseCase<QueryPriceCommand,PriceCommand> {
    private final PriceRepositoryPort priceRepositoryPort;


    @Override
    public PriceCommand execute(final QueryPriceCommand command) {

        List<PriceCommand> prices = priceRepositoryPort.getPricesByBrandAndProductAndApplicationDate(command);
        PriceCommand price = getHighestPriority(prices);
        validPriority(price, prices);
        return price;
    }
    private PriceCommand getHighestPriority(List<PriceCommand> prices) {
        Optional<PriceCommand> lowestPriorityPrice = prices.stream()
                .max(Comparator.comparingInt(PriceCommand::getPriority));

        return lowestPriorityPrice.orElseThrow(() ->  new PriceNotFoundException("01","No prices found for the given date range.") );
    }
    private void validPriority(PriceCommand price, List<PriceCommand> prices ){
        Optional.of(prices.stream().filter(p-> Objects.equals(p.getPriority(), price.getPriority())).count())
                .filter(total-> (total == 1))
                .orElseThrow(() ->  new AmbiguityException("02","Ambiguity: Multiple prices found with the same highest priority.") );
    }
}
