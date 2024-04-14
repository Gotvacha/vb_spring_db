package org.dbex.vb_spring_db;

import org.dbex.vb_spring_db.DTOs.AirportDTO;
import org.dbex.vb_spring_db.DTOs.CityDTO;
import org.dbex.vb_spring_db.DTOs.CountryDTO;
import org.dbex.vb_spring_db.Modules.Airport;
import org.dbex.vb_spring_db.Repositories.AirportRepository;
import org.dbex.vb_spring_db.Repositories.CityRepository;
import org.dbex.vb_spring_db.Repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public List<AirportDTO> findAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AirportDTO convertToDTO(Airport airport) {
        AirportDTO dto = new AirportDTO();
        dto.setId(airport.getId());
        dto.setName(airport.getName());
        dto.setIataCode(airport.getIataCode());
        dto.setIcaoCode(airport.getIcaoCode());
        dto.setLatitude(airport.getLatitude());
        dto.setLongitude(airport.getLongitude());

        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(airport.getCity().getId());
        cityDTO.setName(airport.getCity().getName());

        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(airport.getCity().getCountry().getName());
        countryDTO.setIso2CountryCode(airport.getCity().getCountry().getIso2CountryCode());
        countryDTO.setIso3CountryCode(airport.getCity().getCountry().getIso3CountryCode());

        cityDTO.setCountry(countryDTO);
        dto.setCity(cityDTO);

        return dto;
    }

    private AirportDTO convertToDTOFiltered(Airport airport) {
        AirportDTO dto = new AirportDTO();
        dto.setId(airport.getId());
        dto.setName(airport.getName());
        dto.setIataCode(airport.getIataCode());
        dto.setIcaoCode(airport.getIcaoCode());
        dto.setLatitude(airport.getLatitude());
        dto.setLongitude(airport.getLongitude());

        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(airport.getCity().getId());
        cityDTO.setName(airport.getCity().getName());

        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(airport.getCity().getCountry().getName());
        countryDTO.setIso2CountryCode(airport.getCity().getCountry().getIso2CountryCode());
        countryDTO.setIso3CountryCode(airport.getCity().getCountry().getIso3CountryCode());

        cityDTO.setCountry(countryDTO);
        dto.setCity(cityDTO);

        return dto;
    }

    public List<AirportDTO> findFilteredAirportsByCountryAsRoot(AirportFilter airportFilter) {
        Set<AirportDTO> filteredAirports = new HashSet<>();
        Set<CountryDTO> countries = (Set<CountryDTO>) countryRepository.findByIso2CountryCodeIn(airportFilter.countryIso2Codes());
        List<Long> countryIds = countries.stream().map(CountryDTO::getId).collect(Collectors.toList());
        List<CityDTO> cities = cityRepository.findByCountryIdIn(countryIds);
        List<Long> cityIds = cities.stream().map(CityDTO::getId).collect(Collectors.toList());
        List<AirportDTO> airports = airportRepository.findByCityIdInAndFilter(cityIds, airportFilter);
        filteredAirports.addAll(airports);
        return new ArrayList<>(filteredAirports);
    }

    public List<AirportDTO> findFilteredAirportsAsRoot(AirportFilter airportFilter) {
        Set<AirportDTO> filteredAirports = new HashSet<>();
        for (Long cityId : airportFilter.cityIds()) {
            filteredAirports.addAll(airportRepository.findByCityIdAndFilter(cityId, airportFilter));
        }
        return new ArrayList<>(filteredAirports);
    }
}
