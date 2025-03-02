package by.svyat.forecasting.controller;

import by.svyat.forecasting.common.CandlesResponse;
import by.svyat.forecasting.service.ForecastingService;
import by.svyat.forecasting.service.InvestApiService;
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
public class ForecastingController {
    private final InvestApiService investApiService;
    private final ForecastingService forecastingService;

    @GetMapping("/api/orders/price/{tiket}")
    public ResponseEntity<BigDecimal> getLastPrice(@PathVariable String tiket) {
        return ResponseEntity.ok(investApiService.getLastPrice(tiket));
    }

    @GetMapping("/api/orders/candles/{tiket}")
    public ResponseEntity<CandlesResponse> getCandles(@PathVariable String tiket) {
        return ResponseEntity.ok(investApiService.getCandles(tiket));
    }

    @GetMapping("/api/predict/{tiket}")
    public ResponseEntity<String> predict(@PathVariable String tiket) {
        return ResponseEntity.ok(forecastingService.predict(tiket));
    }
}
