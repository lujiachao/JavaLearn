package org.example.httpmodel.pesponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecast {

    private String date;
    private String high;
    private String low;
    private String ymd;
    private String week;
    private String sunrise;
    private String sunset;
    private int aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;
}