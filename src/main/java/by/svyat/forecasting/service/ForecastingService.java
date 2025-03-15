package by.svyat.forecasting.service;

import by.svyat.forecasting.common.CandlesResponse;
import by.svyat.forecasting.common.PredictResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForecastingService {
    private final InvestApiService investApiService;
    private final StockPredictorService stockPredictorService;

    public PredictResponse predict(String tiket) {
        CandlesResponse candles = investApiService.getCandles(tiket);

        Double probabilityUp = stockPredictorService.predictPrice(
                candles.getCandles5minute(),
                candles.getCandles1hour(),
                candles.getCandles4hour()
        );

        PredictResponse predictResponse = createPredictResponse(probabilityUp);

        log.debug("{}", predictResponse.getPrediction());

        return predictResponse;
    }

    private PredictResponse createPredictResponse(Double probabilityUp) {
        return new PredictResponse().setPrediction(
                String.format("Цена пойдет вверх с вероятностью: %.2f%%", probabilityUp)
        );
    }
}
