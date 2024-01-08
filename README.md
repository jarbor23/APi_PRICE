# Api-Price-test

# Table of contents
[1. Introduction](#introduction)

[2. Guidelines](#guidelines)
* [2.1 Libraries and technologies integrated](#libraries-and-techologies-integrated)
* [2.2 Project structure](#project-structure)

## Introduction <a id="introduction"></a>

this project exposes services Query Price to DB h2
using hexagonal architecture 

Operations included:
1. [QueryPrice](#query-price)
   * [test 1](#test_1)
   * [test 2](#test_2)
   * [test 3](#test_3)
   * [test 4](#test_4)
   * [test 5](#test_5)

To start the project it is only necessary to execute the following command.
``` ./gradlew bootRun"```

Valid test only execute the following command.
``` ./gradlew test"```

#### Libraries and technologies integrated: <a id="libraries-and-techologies-integrated"></a>

##### FOR PROGRAMMING
    - Java JDK 17
    - Spring with Tomcat (SpringBoot 3.2.1)
    - OpenApi & Swagger3 (Contracts)
    - gradle 8.5
##### FOR UNIT TESTING
    - JUnit

---
###Operations included

-------
#### Get query price<a id="query-price"></a>
```javascript 
curl --location 'localhost:8080/price/queryPrice?applicationDate=2023-12-12%2012%3A00%3A13&productId=1&brandId=1'
```

#### TEST 1<a id="test_1"></a>
```javascript 
curl --location 'localhost:8080/price/queryPrice?applicationDate=2020-06-14%2010%3A00%3A00&productId=35455&brandId=1'
```

#### TEST 2<a id="test_2"></a>
```javascript 
curl --location 'localhost:8080/price/queryPrice?applicationDate=2020-06-14%2016%3A00%3A00&productId=35455&brandId=1'
```

#### TEST 3<a id="test_3"></a>
```javascript 
curl --location 'localhost:8080/price/queryPrice?applicationDate=2020-06-14%2021%3A00%3A00&productId=35455&brandId=1'
```

#### TEST 4<a id="test_4"></a>
```javascript 
curl --location 'localhost:8080/price/queryPrice?applicationDate=2020-06-15%2010%3A00%3A00&productId=35455&brandId=1'
```

#### TEST 5<a id="test_5"></a>
```javascript 
curl --location 'localhost:8080/price/queryPrice?applicationDate=2020-06-16%2021%3A00%3A00&productId=35455&brandId=1'
```

## Guidelines <a id="guidelines"></a>

#### Project structure <a id="project-structure"></a>
    ```
    .
    |-- HELP.md
    |-- build.gradle
    |-- gradle
    |   `-- wrapper
    |       |-- gradle-wrapper.jar
    |       `-- gradle-wrapper.properties
    |-- gradlew
    |-- gradlew.bat
    |-- settings.gradle
    `-- src
    |-- main
    |   |-- java
    |   |   `-- com
    |   |       `-- test
    |   |           `-- buy
    |   |               `-- api
    |   |                   |-- ApiApplication.java
    |   |                   |-- domain
    |   |                   |   |-- command
    |   |                   |   |   |-- Command.java
    |   |                   |   |   |-- PriceCommand.java
    |   |                   |   |   `-- QueryPriceCommand.java
    |   |                   |   |-- exception
    |   |                   |   |   |-- AmbiguityException.java
    |   |                   |   |   |-- BusinessException.java
    |   |                   |   |   `-- PriceNotFoundException.java
    |   |                   |   |-- ports
    |   |                   |   |   `-- out
    |   |                   |   |       `-- PriceRepositoryPort.java
    |   |                   |   `-- usecase
    |   |                   |       |-- GetPriceByBrandAndProductAndApplicationDateUseCase.java
    |   |                   |       `-- UseCase.java
    |   |                   `-- infrastructure
    |   |                       |-- adapter
    |   |                       |   |-- in
    |   |                       |   |   |-- dto
    |   |                       |   |   |   |-- RequestQueryPrice.java
    |   |                       |   |   |   `-- ResponseQueryPrice.java
    |   |                       |   |   |-- handler
    |   |                       |   |   |   |-- PriceHandler.java
    |   |                       |   |   |   `-- common
    |   |                       |   |   |       |-- Constants.java
    |   |                       |   |   |       |-- ErrorDTO.java
    |   |                       |   |   |       |-- RestExceptionHandler.java
    |   |                       |   |   |       `-- validator
    |   |                       |   |   |           |-- ApplicationDateOrder.java
    |   |                       |   |   |           `-- ApplicationDateOrderValidator.java
    |   |                       |   |   |-- mapper
    |   |                       |   |   |   `-- PriceMapper.java
    |   |                       |   |   `-- rest
    |   |                       |   |       `-- PriceRestAdapter.java
    |   |                       |   `-- out
    |   |                       |       `-- jpa
    |   |                       |           `-- price
    |   |                       |               |-- PriceRepository.java
    |   |                       |               |-- PriceRepositoryAdapter.java
    |   |                       |               |-- entity
    |   |                       |               |   `-- EntityPrice.java
    |   |                       |               `-- mapper
    |   |                       |                   `-- MapperEntity.java
    |   |                       `-- configuration
    |   |                           |-- OpenApiConfiguration.java
    |   |                           `-- UseCaseConfiguration.java
    |   `-- resources
    |       |-- application.yaml
    |       |-- static
    |       `-- templates
    `-- test
    |-- java
    |   `-- com
    |       `-- test
    |           `-- buy
    |               `-- api
    |                   |-- ApiApplicationTests.java
    |                   `-- infrastructure
    |                       `-- adapter
    |                           `-- in
    |                               `-- rest
    |                                   `-- PriceRestAdapterTest.java
    `-- resources
    |-- application-test.yaml
    `-- data.sql
    ```