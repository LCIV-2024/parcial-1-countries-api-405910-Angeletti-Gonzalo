package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class CountryController {

    private final CountryService countryService;


    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getAllCountries(){
        List<CountryDTO> countryDTOS = new ArrayList<>();
        List<Country> countryList = countryService.getAllCountries();
        for (int i = 0; i < countryList.size(); i++) {
            Country country = countryList.get(i);
            CountryDTO countryDTO = new CountryDTO(country.getCode(), country.getName());
            countryDTOS.add(countryDTO);
        }
        return ResponseEntity.ok(countryDTOS);
    }





}