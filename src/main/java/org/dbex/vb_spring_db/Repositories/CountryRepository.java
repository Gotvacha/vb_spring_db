package org.dbex.vb_spring_db.Repositories;

import org.dbex.vb_spring_db.DTOs.CountryDTO;
import org.dbex.vb_spring_db.Modules.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<CountryDTO> findByIso2CountryCodeIn(Set<String> iso2CountryCodes);
}