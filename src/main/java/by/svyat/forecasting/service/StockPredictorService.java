package by.svyat.forecasting.service;

import by.svyat.forecasting.common.CandleInfo;
import by.svyat.forecasting.config.StockPredictorInitializer;
import lombok.RequiredArgsConstructor;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockPredictorService {

    public Double predictPrice(
            List<CandleInfo> candles5m,
            List<CandleInfo> candles1h,
            List<CandleInfo> candles4h
    ) {
        double prediction5m = predictForCandles(candles5m);
        double prediction1h = predictForCandles(candles1h);
        double prediction4h = predictForCandles(candles4h);

        return (prediction5m + prediction1h + prediction4h) / 3.0;
    }

    private double predictForCandles(List<CandleInfo> candles) {
        if (candles.isEmpty()) {
            return 0.0;
        }

        int inputSize = candles.size() * CandleInfo.getCountParametersWithoutTime();
        MultiLayerConfiguration layerConfig = StockPredictorInitializer.createNetwork(inputSize);
        MultiLayerNetwork model = StockPredictorInitializer.multiLayerNetwork(layerConfig);

        INDArray input = prepareInput(candles, inputSize);
        INDArray output = model.output(input);

        return output.getDouble(0) * 100;
    }

    private INDArray prepareInput(List<CandleInfo> candles, int inputSize) {
        double[][] data = new double[1][inputSize];

        int index = 0;
        for (CandleInfo candle : candles) {
            data[0][index++] = candle.getOpen().doubleValue();
            data[0][index++] = candle.getClose().doubleValue();
            data[0][index++] = candle.getHigh().doubleValue();
            data[0][index++] = candle.getLow().doubleValue();
            data[0][index++] = candle.getVolume();
        }

        return Nd4j.create(data);
    }
}

