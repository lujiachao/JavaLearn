package org.example.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class TestTwoResponse {
    @Getter
    @Setter
    private int TestColOne;

    @Getter
    @Setter
    private String TestColTwo;

    @Getter
    @Setter
    private Boolean TestColThree;

    @Getter
    @Setter
    private List<TestList> TestColFour;

    public class TestList
    {
        @Getter
        @Setter
        private int TestListColOne;

        @Getter
        @Setter
        private String TestListColTwo;

        @Getter
        @Setter
        private Boolean TestListColThree;
    }
}
