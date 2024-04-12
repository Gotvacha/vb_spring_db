package org.dbex.vb_spring_db.Repositories;

import org.dbex.vb_spring_db.DTOs.CityDTO;
import org.dbex.vb_spring_db.Modules.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
    List<CityDTO> findByCountryIdIn(List<Long> countryIds);
}