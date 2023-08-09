package background_scheduler;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinGeckoResponse {

    @JsonProperty("btc")
    private CoinPrice btc;

    @JsonProperty("eth")
    private CoinPrice eth;

    // Add more coin prices as needed

    public CoinPrice getBtc() {
        return btc;
    }

    public CoinPrice getEth() {
        return eth;
    }

    // Add getter methods for other coin prices
}
