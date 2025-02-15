package com.example.apigatewayservice.config;

import com.example.apigatewayservice.filter.CustomFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

    private final CustomFilter customFilter;

    public FilterConfig(CustomFilter customFilter) {
        this.customFilter = customFilter;
    }


    // custom filter 적용
//    @Bean
    public RouteLocator gatewayLocator(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.filter(customFilter.apply(new CustomFilter.Config())))
                        .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.filter(customFilter.apply(new CustomFilter.Config())))
                        .uri("http://localhost:8082"))
                .build();
    }


//    @Bean
//    public RouteLocator gatewayLocator(RouteLocatorBuilder locatorBuilder) {
//        return locatorBuilder.routes()
//                .route(r -> r.path("/first-service/**")
//                        .filters(f ->
//                                f.addRequestHeader("first-request", "first-request-header")
//                                .addResponseHeader("first-response", "first-response-header")
//                        )
//                        .uri("http://localhost:8081"))
//                .route(r -> r.path("/second-service/**")
//                        .filters(f ->
//                                f.addRequestHeader("second-request", "second-request-header")
//                                .addResponseHeader("second-response", "second-response-header")
//                        )
//                        .uri("http://localhost:8082"))
//                .build();
//    }
}
