package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            // Custom Pre Filter
            ServerHttpRequest request = exchange.getRequest();
            log.info("Custom Pre Filter: request id -> {}", request.getId());

            // Custom Post Filter
            ServerHttpResponse response = exchange.getResponse();
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom Post Filter: response code -> {}", response.getStatusCode());
            }));
        });
    }

    public static class Config {
        // Put the configuration properties
    }
}
