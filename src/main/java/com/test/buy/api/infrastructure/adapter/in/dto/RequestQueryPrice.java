package com.test.buy.api.infrastructure.adapter.in.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.test.buy.api.infrastructure.adapter.in.handler.common.validator.ApplicationDateOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(as = RequestQueryPrice.class)
public class RequestQueryPrice {
    @NotNull(message = "The brandId is required.")
    private Integer brandId;
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$",
            message = "The applicationDate must meet the specified criteria. YYYY-mm-DD HH:mm:ss")
    @ApplicationDateOrder
    private String applicationDate;
    @NotNull(message = "The brandId is productId.")
    private int productId;
}
