package by.svyat.forecasting.common;

import java.math.BigDecimal;
import java.util.List;

public class ExampleDataSet {

    public static List<CandleInfo> candles5m = List.of(
            new CandleInfo()
                    .setOpen(new BigDecimal("57.385"))
                    .setClose(new BigDecimal("57.385"))
                    .setHigh(new BigDecimal("57.385"))
                    .setLow(new BigDecimal("57.260"))
                    .setVolume(37L)
                    .setTime("2025-03-01T09:40:00Z"),
            new CandleInfo()
                    .setOpen(new BigDecimal("57.365"))
                    .setClose(new BigDecimal("57.260"))
                    .setHigh(new BigDecimal("57.365"))
                    .setLow(new BigDecimal("57.260"))
                    .setVolume(105L)
                    .setTime("2025-03-01T09:45:00Z")
    );

    public static List<CandleInfo> candles1h = List.of(
            new CandleInfo()
                    .setOpen(new BigDecimal("57.400"))
                    .setClose(new BigDecimal("57.350"))
                    .setHigh(new BigDecimal("57.450"))
                    .setLow(new BigDecimal("57.300"))
                    .setVolume(500L)
                    .setTime("2025-03-01T09:00:00Z")
    );

    public static List<CandleInfo> candles4h = List.of(
            new CandleInfo()
                    .setOpen(new BigDecimal("57.500"))
                    .setClose(new BigDecimal("57.300"))
                    .setHigh(new BigDecimal("57.600"))
                    .setLow(new BigDecimal("57.200"))
                    .setVolume(1200L)
                    .setTime("2025-03-01T06:00:00Z")
    );
}
