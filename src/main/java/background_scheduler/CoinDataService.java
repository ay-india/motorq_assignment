package background_scheduler;

import background_scheduler.CoinGeckoResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinDataService {

    private final RestTemplate restTemplate;

    public CoinDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(key = "#coinId", cacheManager = "coinDataCacheManager")
    public Double getCoinPrice(String coinId) {
        // Fetch data from CoinGecko or AirTable
        // Let's assume CoinGecko is used here for simplicity
        String coinGeckoUrl = "https://api.coingecko.com/api/v3/simple/price?ids=" + coinId + "&vs_currencies=usd";
        CoinGeckoResponse response = restTemplate.getForObject(coinGeckoUrl, CoinGeckoResponse.class);
        if (response != null && response.containsKey(coinId)) {
            return response.get(coinId).getUsd();
        } else {
            return null;
        }
    }
}
