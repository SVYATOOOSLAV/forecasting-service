package by.svyat.forecasting.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Forecasting Api",
                description = "API предсказания котировок",
                version = "1.0.0"
        )
)
public class OpenApiConfig {
}