package com.test.buy.api.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QueryPriceCommand implements Command {
    private Integer brandId;
    private LocalDateTime applicationDate;
    private int productId;
}
