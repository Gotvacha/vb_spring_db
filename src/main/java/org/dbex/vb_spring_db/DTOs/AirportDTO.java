package org.dbex.vb_spring_db.DTOs;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportDTO {
    private Long id;
    private String name;
    private CityDTO city;
    private String iataCode;
    private String icaoCode;
    private Double latitude;
    private Double longitude;
}