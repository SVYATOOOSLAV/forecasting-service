package by.svyat.forecasting.service;

import by.svyat.forecasting.common.CandlesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForecastingService {
    private final InvestApiService investApiService;
    private final StockPredictorService stockPredictorService;

    public String predict(String tiket) {
        CandlesResponse candles = investApiService.getCandles(tiket);

        Double probabilityUp = stockPredictorService.predictPrice(
                candles.getCandles5minute(),
                candles.getCandles1hour(),
                candles.getCandles4hour()
        );

        String probabilityUpString = String.format("%.2f%%", probabilityUp);

        log.debug("Цена акции пойдет вверх с вероятностью {}", probabilityUpString);

        return probabilityUpString;
    }
}
