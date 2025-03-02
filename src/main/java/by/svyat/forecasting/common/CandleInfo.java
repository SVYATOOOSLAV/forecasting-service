package by.svyat.forecasting.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Data
@Accessors(chain = true)
public class CandleInfo {
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal high;
    private BigDecimal low;
    private Long volume;
    private String time;

    public static int getCountParametersWithoutTime(){
        return 5;
    }
}
