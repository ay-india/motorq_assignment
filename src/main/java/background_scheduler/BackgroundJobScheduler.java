package background_scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;


@Component
public class BackgroundJobScheduler {

    private final RestTemplate restTemplate;
    private final AirTableService airTableService; // Assuming you have an AirTable service

    public BackgroundJobScheduler(RestTemplate restTemplate, AirTableService airTableService) {
        this.restTemplate = restTemplate;
        this.airTableService = airTableService;
    }

    @Scheduled(fixedRate = 600000) // Every 10 minutes
    public void updateCoinDetails() {
        String coinGeckoUrl = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1";
        ResponseEntity<CoinGeckoCoin[]> response = restTemplate.getForEntity(coinGeckoUrl, CoinGeckoCoin[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            CoinGeckoCoin[] coinList = response.getBody();

            // Update coin details in AirTable
            for (CoinGeckoCoin coin : coinList) {
                // Convert CoinGeckoCoin to your AirTable data model
                AirTableCoin airTableCoin = convertToAirTableCoin(coin);

                // Update the coin details in AirTable using your service
                airTableService.updateCoinDetails(airTableCoin);
            }
        }
    }
    private void updateCacheAndAirTable(CoinGeckoResponse coinPrices) {
        for (Map.Entry<String, CoinPrice> entry : coinPrices.entrySet()) {
            String coinId = entry.getKey();
            CoinPrice coinPrice = entry.getValue();

            // Update cache
            updateCache(coinId, coinPrice.getUsd());

            // Update AirTable
            updateAirTable(coinId, coinPrice.getUsd());
        }
    }

    private void updateCache(String coinId, double price) {
        // Implement your cache update logic here
        // For example, using your CoinCacheService to update the cache
        coinCacheService.updateCache(coinId, price);
    }

    private void updateAirTable(String coinId, double price) {
        // Implement your AirTable update logic here
        // For example, using your AirTableService to update the AirTable database
        AirTableCoin updatedCoin = new AirTableCoin();
        updatedCoin.setId(coinId);
        updatedCoin.setPrice(price);

        airTableService.updateCoinPrice(updatedCoin);
    }

    private AirTableCoin convertToAirTableCoin(CoinGeckoCoin coin) {
        // Convert CoinGeckoCoin to AirTableCoin
        AirTableCoin airTableCoin = new AirTableCoin();
        airTableCoin.setId(coin.getId());
        airTableCoin.setName(coin.getName());
        airTableCoin.setSymbol(coin.getSymbol());
        // Set other properties as needed
        return airTableCoin;
    }
}
