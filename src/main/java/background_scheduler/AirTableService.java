package background_scheduler;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class AirTableService {

    private static final String AIRTABLE_API_URL = "https://api.airtable.com/v0/app0XWD00g0nX8Aos/cryptocurrency";
    private static final String AIRTABLE_API_KEY = "patiUspIn55wTw4oi.174aaa465bb23e1528e5a4f0423fd154a2046bbb5d58f47f2aa4404bbd3e48f1"; // Replace with your AirTable API key

    @Autowired
    private RestTemplate restTemplate;


    public AirTableService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void updateCoinDetails(AirTableCoin airTableCoin) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AIRTABLE_API_KEY);

        // Build the request object
        AirTableUpdateRequest updateRequest = new AirTableUpdateRequest();
        updateRequest.setFields(airTableCoin);

        HttpEntity<AirTableUpdateRequest> requestEntity = new HttpEntity<>(updateRequest, headers);

        // Send PUT request to update the record
        restTemplate.exchange(AIRTABLE_API_URL + "/" + airTableCoin.getId(), HttpMethod.PUT, requestEntity, String.class);
    }

    public void createRecord(CoinData coinData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + AIRTABLE_API_KEY);

        HttpEntity<CoinData> request = new HttpEntity<>(coinData, headers);

        restTemplate.exchange(AIRTABLE_API_URL, HttpMethod.POST, request, String.class);
    }

    public CoinData retrieveRecord(String recordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + AIRTABLE_API_KEY);

        HttpEntity<CoinData> request = new HttpEntity<>(headers);

        String retrieveUrl = AIRTABLE_API_URL + "/" + recordId;

        return restTemplate.exchange(retrieveUrl, HttpMethod.GET, request, CoinData.class).getBody();
    }

    public void updateRecord(String recordId, CoinData coinData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + AIRTABLE_API_KEY);

        HttpEntity<CoinData> request = new HttpEntity<>(coinData, headers);

        String updateUrl = AIRTABLE_API_URL + "/" + recordId;

        restTemplate.exchange(updateUrl, HttpMethod.PUT, request, String.class);
    }

    public void deleteRecord(String recordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + AIRTABLE_API_KEY);

        HttpEntity<CoinData> request = new HttpEntity<>(headers);

        String deleteUrl = AIRTABLE_API_URL + "/" + recordId;

        restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request, String.class);
    }

    public List<AirTableCoin> getAllCoins() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AIRTABLE_API_KEY);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<AirTableResponse> response = restTemplate.exchange(
                AIRTABLE_API_URL, HttpMethod.GET, requestEntity, AirTableResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            AirTableResponse airTableResponse = response.getBody();
            return airTableResponse.getRecords();
        } else {
            throw new RuntimeException("Failed to fetch coin information from AirTable");
        }
    }
}
