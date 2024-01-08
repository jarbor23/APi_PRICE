package com.test.buy.api.infrastructure.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    private static final String HTTP_URL = "https://github.com/jarbor23/APi_PRICE/blob/master/README.md";
    public static final String[] PRICE_ALLOWED_PATH = {"/price*", "/price/*", "/price/queryPrice/**"};

    @Bean
    public GroupedOpenApi price() {
        return GroupedOpenApi.builder().group("price").pathsToMatch(PRICE_ALLOWED_PATH).build();
    }

    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Query price REST API")
                                .description("test Price")
                                .version("v0.0.1")
                                .license(new License().name("Apache 2.0").url(HTTP_URL)))
                .externalDocs(
                        new ExternalDocumentation().description("git readme").url(HTTP_URL));
    }
}
