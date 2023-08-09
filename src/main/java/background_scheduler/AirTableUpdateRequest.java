package background_scheduler;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AirTableUpdateRequest {

    @JsonProperty("fields")
    private AirTableCoin fields;

    public AirTableCoin getFields() {
        return fields;
    }

    public void setFields(AirTableCoin fields) {
        this.fields = fields;
    }
}
