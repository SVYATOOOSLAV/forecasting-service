package by.svyat.forecasting.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CandlesResponse {

    @JsonProperty("candles_5_minute")
    List<CandleInfo> candles5minute;

    @JsonProperty("candles_1_hour")
    List<CandleInfo> candles1hour;

    @JsonProperty("candles_4_hour")
    List<CandleInfo> candles4hour;
}
