package by.svyat.forecasting.service;

import by.svyat.forecasting.common.CandleInfo;
import by.svyat.forecasting.config.StockPredictorConfig;
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
    private final StockPredictorConfig config;

    public Double predictPrice(
            List<CandleInfo> candles5m,
            List<CandleInfo> candles1h,
            List<CandleInfo> candles4h
    ) {
        int inputSize = (candles5m.size() + candles1h.size() + candles4h.size())
                * CandleInfo.getCountParametersWithoutTime();

        MultiLayerConfiguration layerConfig = config.createNetwork(inputSize);
        MultiLayerNetwork model = config.multiLayerNetwork(layerConfig);

        INDArray input = prepareInput(candles5m, candles1h, candles4h, inputSize);
        INDArray output = model.output(input);

        return output.getDouble(0) * 100;
    }


    private INDArray prepareInput(
            List<CandleInfo> candles5m,
            List<CandleInfo> candles1h,
            List<CandleInfo> candles4h,
            int inputSize
    ) {
        double[][] data = new double[1][inputSize];

        int index = 0;
        index = updateData(candles5m, data, index);
        index = updateData(candles1h, data, index);
        updateData(candles4h, data, index);

        return Nd4j.create(data);
    }

    private int updateData(
            List<CandleInfo> candles,
            double[][] data,
            int index
    ) {
        for (CandleInfo candle : candles) {
            data[0][index++] = candle.getOpen().doubleValue();
            data[0][index++] = candle.getClose().doubleValue();
            data[0][index++] = candle.getHigh().doubleValue();
            data[0][index++] = candle.getLow().doubleValue();
            data[0][index++] = candle.getVolume();
        }
        return index;
    }
}
