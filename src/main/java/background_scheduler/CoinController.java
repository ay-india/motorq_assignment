package background_scheduler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CoinController {

    private final CoinDataService coinDataService;
    private final AirTableService airTableService;
    public CoinController(CoinDataService coinDataService,AirTableService airTableService) {
        this.coinDataService = coinDataService;
        this.airTableService = airTableService;
    }

    // Assuming you have an AirTable service



    @GetMapping
    public ResponseEntity<List<AirTableCoin>> getCoins() {
        List<AirTableCoin> coinList = airTableService.getAllCoins();
        return ResponseEntity.ok(coinList);
    }
    @GetMapping("/coinPrice/{coinId}")
    public Double getCoinPrice(@PathVariable String coinId) {
        return coinDataService.getCoinPrice(coinId);
    }
}
