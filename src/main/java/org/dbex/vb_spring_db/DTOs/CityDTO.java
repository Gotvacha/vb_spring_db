package org.dbex.vb_spring_db.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
    private Long id;
    private String name;
    private CountryDTO country;
}
