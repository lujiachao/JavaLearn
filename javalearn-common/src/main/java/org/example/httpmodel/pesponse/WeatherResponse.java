package org.example.httpmodel.pesponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private String message;
    private String time;
    private int status;
    private String date;
    private CityInfo cityInfo;
    private Data data;
}
