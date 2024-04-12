package org.dbex.vb_spring_db.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    private Long id;
    private String name;
    private String iso2CountryCode;
    private String iso3CountryCode;
}
