package org.example.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TestExceptionResponse {
    @JsonProperty("TestColOne")
    @Getter
    @Setter
    private int TestColOne;

    @JsonProperty("TestColTwo")
    @Getter
    @Setter
    private String TestColTwo;

    @JsonProperty("TestColThree")
    @Getter
    @Setter
    private Boolean TestColThree;
}
