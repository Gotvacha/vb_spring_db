package org.dbex.vb_spring_db;

import org.dbex.vb_spring_db.DTOs.AirportDTO;
import org.dbex.vb_spring_db.AirportService;
import org.dbex.vb_spring_db.AirportFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/airports")
public class AirportController {
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<AirportDTO>> fetchAllAirports() {
        List<AirportDTO> airportDTOs = airportService.findAllAirports();
        if (airportDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(airportDTOs);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> fetchFilteredAirports(@RequestParam(required = false) boolean countryAsRoot,
                                                   @RequestBody AirportFilter airportFilter) {
        List<?> filteredAirports;
        if (countryAsRoot) {
            filteredAirports = airportService.findFilteredAirportsByCountryAsRoot(airportFilter);
        } else {
            filteredAirports = airportService.findFilteredAirportsAsRoot(airportFilter);
        }
        if (filteredAirports.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filteredAirports);
    }
}
