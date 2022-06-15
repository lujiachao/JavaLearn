package org.example.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class TestTwoRequest {
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

    @JsonProperty("TestColFour")
    @Getter
    @Setter
    private List<TestList> TestColFour;

    public static class TestList
    {
        @JsonProperty("TestListColOne")
        @Getter
        @Setter
        private int TestListColOne;

        @JsonProperty("TestListColTwo")
        @Getter
        @Setter
        private String TestListColTwo;

        @JsonProperty("TestListColThree")
        @Getter
        @Setter
        private Boolean TestListColThree;
    }
}
