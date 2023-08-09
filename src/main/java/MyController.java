import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class MyController {

    @RateLimited(key = "coinGeckoApi", limit = 5, duration = 60)
    @GetMapping("/coingecko")
    public String coinGeckoEndpoint() {
        return "CoinGecko API - Rate limited!";
    }

    // Other endpoints

    @GetMapping("/nonRateLimited")
    public String nonRateLimitedEndpoint() {
        return "Non Rate-Limited Endpoint";
    }
}
