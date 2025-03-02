package by.svyat.forecasting.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.invest")
public record InvestProperties(String token) {
}
