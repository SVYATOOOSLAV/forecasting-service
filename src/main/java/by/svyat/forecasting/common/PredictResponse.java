package by.svyat.forecasting.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PredictResponse {
    private String prediction;
}
