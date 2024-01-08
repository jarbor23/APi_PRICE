package com.test.buy.api.infrastructure.adapter.out.jpa.price.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "PRICES")
@Getter
@Setter
public class EntityPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    private int priceList;
    private int productId;
    private int brandId;
    private int priority;
    private double price;
    private String currency;
}
