package com.test.buy.api.infrastructure.adapter.out.jpa.price;

import com.test.buy.api.infrastructure.adapter.out.jpa.price.entity.EntityPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<EntityPrice, Long> {

    List<EntityPrice> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
            int brandId, int productId, LocalDateTime applicationDateBefore, LocalDateTime applicationDateAfter);

}
