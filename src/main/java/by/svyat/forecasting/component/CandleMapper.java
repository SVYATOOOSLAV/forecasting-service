package by.svyat.forecasting.component;

import by.svyat.forecasting.common.CandleInfo;
import org.springframework.stereotype.Component;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;

import static ru.tinkoff.piapi.core.utils.DateUtils.timestampToString;
import static ru.tinkoff.piapi.core.utils.MapperUtils.quotationToBigDecimal;

@Component
public class CandleMapper {

    public CandleInfo mapToCandle(HistoricCandle candle) {
        var open = quotationToBigDecimal(candle.getOpen());
        var close = quotationToBigDecimal(candle.getClose());
        var high = quotationToBigDecimal(candle.getHigh());
        var low = quotationToBigDecimal(candle.getLow());
        var volume = candle.getVolume();
        var time = timestampToString(candle.getTime());

        return new CandleInfo()
                .setOpen(open)
                .setClose(close)
                .setHigh(high)
                .setLow(low)
                .setVolume(volume)
                .setTime(time);
    }
}
