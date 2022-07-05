package org.example.httpmodel.pesponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityInfo {

    private String city;
    private Integer citykey;
    private String parent;
    private String updateTime;
}
