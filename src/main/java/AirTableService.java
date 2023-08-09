import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;




@Service
public class AirTableService {

    private static final String AIRTABLE_API_URL = "https://api.airtable.com/v0/app0XWD00g0nX8Aos/cryptocurrency";
    private static final String AIRTABLE_API_KEY = "patiUspIn55wTw4oi.174aaa465bb23e1528e5a4f0423fd154a2046bbb5d58f47f2aa4404bbd3e48f1"; // Replace with your AirTable API key

    @Autowired
    private RestTemplate restTemplate;

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
    // Implement other CRUD operations (retrieve, update, delete) similarly
}
