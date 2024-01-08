package com.test.buy.api.infrastructure.adapter.in.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.buy.api.infrastructure.adapter.in.dto.ResponseQueryPrice;
import com.test.buy.api.infrastructure.adapter.in.handler.common.ErrorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceRestAdapterTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @Sql("/data.sql")
    void queryPriceTest1() throws JsonProcessingException {
        String url = "/price/queryPrice?applicationDate=2020-06-14 10:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ResponseQueryPrice responseQueryPrice = objectMapper.readValue(response.getBody(),ResponseQueryPrice.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(35455, responseQueryPrice.getProductId());
        assertEquals(1, responseQueryPrice.getBrandId());
        assertEquals(1, responseQueryPrice.getPriceList());
        assertEquals(35.5, responseQueryPrice.getPrice());

    }
    @Test
    @Sql("/data.sql")
    void queryPriceTest2() throws JsonProcessingException {
        String url = "/price/queryPrice?applicationDate=2020-06-14 16:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ResponseQueryPrice responseQueryPrice = objectMapper.readValue(response.getBody(),ResponseQueryPrice.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(35455, responseQueryPrice.getProductId());
        assertEquals(1, responseQueryPrice.getBrandId());
        assertEquals(2, responseQueryPrice.getPriceList());
        assertEquals(25.45, responseQueryPrice.getPrice());

    }
    @Test
    @Sql("/data.sql")
    void queryPriceTest3() throws JsonProcessingException {
        String url = "/price/queryPrice?applicationDate=2020-06-14 21:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ResponseQueryPrice responseQueryPrice = objectMapper.readValue(response.getBody(),ResponseQueryPrice.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(35455, responseQueryPrice.getProductId());
        assertEquals(1, responseQueryPrice.getBrandId());
        assertEquals(1, responseQueryPrice.getPriceList());
        assertEquals(35.5, responseQueryPrice.getPrice());

    }

    @Test
    @Sql("/data.sql")
    void queryPriceTest4() throws JsonProcessingException {
        String url = "/price/queryPrice?applicationDate=2020-06-15 10:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ResponseQueryPrice responseQueryPrice = objectMapper.readValue(response.getBody(),ResponseQueryPrice.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(35455, responseQueryPrice.getProductId());
        assertEquals(1, responseQueryPrice.getBrandId());
        assertEquals(3, responseQueryPrice.getPriceList());
        assertEquals(30.5, responseQueryPrice.getPrice());

    }
    @Test
    @Sql("/data.sql")
    void queryPriceTest5() throws JsonProcessingException {
        String url = "/price/queryPrice?applicationDate=2020-06-16 21:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ResponseQueryPrice responseQueryPrice = objectMapper.readValue(response.getBody(),ResponseQueryPrice.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(35455, responseQueryPrice.getProductId());
        assertEquals(1, responseQueryPrice.getBrandId());
        assertEquals(4, responseQueryPrice.getPriceList());
        assertEquals(38.95, responseQueryPrice.getPrice());

    }
    @Test
    @Sql("/data.sql")
    void queryPriceWhenOutRange() throws JsonProcessingException {
        String url = "/price/queryPrice?applicationDate=2023-12-12 12:00:13&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ErrorDTO errorDTO = objectMapper.readValue(response.getBody(),ErrorDTO.class);
        assertEquals(404, response.getStatusCode().value());
        assertEquals("An error occurred while consuming the service", errorDTO.getMessage());
        assertEquals("code error: 01, No prices found for the given date range. ", errorDTO.getError());
    }
    @Test
    @Sql("/data.sql")
    void queryPriceTestWhenErrorURL(){
        String url = "/price/error-url?applicationDate=2020-06-16 21:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(404, response.getStatusCode().value());
    }
    @Test
    @Sql("/data.sql")
    void queryPriceTestWhenProcessDateMalFormat(){
        String url = "/price/queryPrice?applicationDate=2020-06-1621:00:00&productId=35455&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(400, response.getStatusCode().value());
    }
    @Test
    @Sql("/data.sql")
    void queryPriceTestWhenInputRequired(){
        String url = "/price/queryPrice?applicationDate=2020-06-16 21:00:00&brandId=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(400, response.getStatusCode().value());
    }

}