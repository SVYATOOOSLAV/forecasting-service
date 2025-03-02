package by.svyat.forecasting.service;

import by.svyat.forecasting.common.CandlesResponse;
import by.svyat.forecasting.component.CandleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.CandleInterval;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;
import ru.tinkoff.piapi.contract.v1.InstrumentShort;
import ru.tinkoff.piapi.contract.v1.LastPrice;
import ru.tinkoff.piapi.core.InstrumentsService;
import ru.tinkoff.piapi.core.MarketDataService;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.tinkoff.piapi.core.utils.MapperUtils.quotationToBigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestApiService {
    private final MarketDataService marketDataService;
    private final InstrumentsService instrumentsService;
    private final CandleMapper mapper;
    private final CandlesService candlesService;

    public BigDecimal getLastPrice(String tiket) {
        List<InstrumentShort> instruments = instrumentsService.findInstrumentSync(tiket);

        InstrumentShort instrument = getInstrument(tiket, instruments);

        List<LastPrice> lastPrices = marketDataService.getLastPricesSync(List.of(instrument.getUid()));
        var quotation = lastPrices.get(0).getPrice();

        return quotationToBigDecimal(quotation);
    }

    public CandlesResponse getCandles(String tiket) {
        List<InstrumentShort> instruments = instrumentsService.findInstrumentSync(tiket);

        InstrumentShort instrument = getInstrument(tiket, instruments);

        log.debug("Successfully find instrument with tiker: {}", instrument.getTicker());

        List<HistoricCandle> candles5min = candlesService.getCandlesByInterval(instrument, CandleInterval.CANDLE_INTERVAL_5_MIN);

        List<HistoricCandle> candlesHour = candlesService.getCandlesByInterval(instrument, CandleInterval.CANDLE_INTERVAL_HOUR);

        // В выходные дни доступен диапазон от CANDLE_INTERVAL_1_MIN до CANDLE_INTERVAL_HOUR
        List<HistoricCandle> candles4hour = candlesService.getCandlesByInterval(instrument, CandleInterval.CANDLE_INTERVAL_4_HOUR);

        // Если выходной день
        if(candles4hour.isEmpty()){
            candles4hour = candlesService.getCandlesInDayOffStockExchange(instrument, CandleInterval.CANDLE_INTERVAL_4_HOUR);
        }

        return new CandlesResponse()
                .setCandles5minute(candles5min.stream().map(mapper::mapToCandle).toList())
                .setCandles1hour(candlesHour.stream().map(mapper::mapToCandle).toList())
                .setCandles4hour(candles4hour.stream().map(mapper::mapToCandle).toList());
    }

    private InstrumentShort getInstrument(String tiket, List<InstrumentShort> instruments) {
        return instruments.stream()
                .filter(el -> Boolean.TRUE.equals(el.getApiTradeAvailableFlag()))
                .filter(stock -> stock.getTicker().equals(tiket))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such instrument: " + tiket));
    }
}


