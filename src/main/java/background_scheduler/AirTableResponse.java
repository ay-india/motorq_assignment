package background_scheduler;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirTableResponse {

    @JsonProperty("records")
    private List<AirTableCoin> records;

    public List<AirTableCoin> getRecords() {
        return records;
    }

    public void setRecords(List<AirTableCoin> records) {
        this.records = records;
    }
}
