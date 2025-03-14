package by.svyat.forecasting.controller;

import by.svyat.forecasting.common.CandlesResponse;
import by.svyat.forecasting.common.PredictResponse;
import by.svyat.forecasting.service.ForecastingService;
import by.svyat.forecasting.service.InvestApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.piapi.contract.v1.Candle;
import ru.tinkoff.piapi.contract.v1.GetOrderBookResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "Контроллер предсказания",
        description = "Позволяет предсказывать направление цены ценной бумаги"
)
public class ForecastingController {
    private final InvestApiService investApiService;
    private final ForecastingService forecastingService;

    @Operation(
            summary = "Цена акции",
            description = "Позволяет получить последнюю цену акции по ее тикету"
    )
    @GetMapping("/api/orders/price/{tiket}")
    public ResponseEntity<BigDecimal> getLastPrice(@PathVariable String tiket) {
        return ResponseEntity.ok(investApiService.getLastPrice(tiket));
    }

    @Operation(
            summary = "Свечи акций",
            description = "Позволяет получить торговые свечи акции по ее тикету в периоде [now() - 1 day; now()]"
    )
    @GetMapping("/api/orders/candles/{tiket}")
    public ResponseEntity<CandlesResponse> getCandles(@PathVariable String tiket) {
        return ResponseEntity.ok(investApiService.getCandles(tiket));
    }

    @Operation(
            summary = "Предсказание цены акции",
            description = "Позволяет получить предсказание цены акции по ее тикету"
    )
    @GetMapping("/api/predict/{tiket}")
    public ResponseEntity<PredictResponse> predict(@PathVariable String tiket) {
        return ResponseEntity.ok(forecastingService.predict(tiket));
    }
}
