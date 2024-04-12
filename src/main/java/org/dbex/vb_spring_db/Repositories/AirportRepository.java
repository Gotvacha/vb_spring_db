package org.dbex.vb_spring_db.Repositories;

import org.dbex.vb_spring_db.AirportFilter;
import org.dbex.vb_spring_db.DTOs.AirportDTO;
import org.dbex.vb_spring_db.Modules.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>, JpaSpecificationExecutor<Airport> {
    Optional<Airport> findByName(String name);
    Optional<Airport> findByIataCode(String code);
    Optional<Airport> findByIcaoCode(String code);
    List<AirportDTO> findByCityIdInAndFilter(List<Long> cityIds, AirportFilter airportFilter);
    List<AirportDTO> findByCityIdAndFilter(Long cityId, AirportFilter airportFilter);
}