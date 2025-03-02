package by.svyat.forecasting.service;

import by.svyat.forecasting.common.CandleInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.CandleInterval;
import ru.tinkoff.piapi.contract.v1.HistoricCandle;
import ru.tinkoff.piapi.contract.v1.Instrument;
import ru.tinkoff.piapi.contract.v1.InstrumentShort;
import ru.tinkoff.piapi.core.MarketDataService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandlesService {
    private final MarketDataService marketDataService;

    public List<HistoricCandle> getCandlesByInterval(InstrumentShort instrument, CandleInterval interval) {
        return marketDataService.getCandlesSync(
                instrument.getUid(),
                Instant.now().minus(1, ChronoUnit.DAYS),
                Instant.now(),
                interval
        );
    }

    public List<HistoricCandle> getCandlesInDayOffStockExchange(InstrumentShort instrument, CandleInterval interval) {
        return marketDataService.getCandlesSync(
                instrument.getUid(),
                Instant.now().minus(2, ChronoUnit.DAYS),
                Instant.now(),
                CandleInterval.CANDLE_INTERVAL_4_HOUR
        );
    }
}
