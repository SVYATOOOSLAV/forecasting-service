package by.svyat.forecasting.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InstrumentsService;
import ru.tinkoff.piapi.core.InvestApi;
import ru.tinkoff.piapi.core.MarketDataService;

@Configuration
@EnableConfigurationProperties(InvestProperties.class)
public class InvestConfig {

    @Bean
    public InvestApi investApi(
            InvestProperties properties
    ) {
        return InvestApi.createReadonly(properties.token());
    }

    @Bean
    public InstrumentsService instrumentsService(InvestApi investApi) {
        return investApi.getInstrumentsService();
    }

    @Bean
    public MarketDataService marketDataService(InvestApi investApi) {
        return investApi.getMarketDataService();
    }
}
