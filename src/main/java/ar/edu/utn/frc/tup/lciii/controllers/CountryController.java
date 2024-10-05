package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class CountryController {

    @Autowired
    private final CountryService countryService ;

    //Ver de usar para no pegar tanto a la API
    //private final List<Country> countryList =new ArrayList<>(countryService.getAllCountries()) ;

    //Anda
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
//Anda
    @GetMapping("/countries/{continent}/continent")
    public ResponseEntity<List<CountryDTO>> getCountriesByContinent(@PathVariable String continent ){
        List<CountryDTO> countryDTOS = new ArrayList<>();
        List<Country> countryList = countryService.getAllCountries();
        for (int i = 0; i < countryList.size(); i++) {
            if (Objects.equals(countryList.get(i).getRegion(), continent)){
                Country country = countryList.get(i);
                CountryDTO countryDTO = new CountryDTO(country.getCode(), country.getName());
                countryDTOS.add(countryDTO);
            }
        }
        return ResponseEntity.ok(countryDTOS);
    }
    //Revisar
    @GetMapping("/countries/{language}/language")
    public ResponseEntity<List<CountryDTO>> getCountriesByLanguage(@PathVariable String[] language){
        List<CountryDTO> countryDTOS = new ArrayList<>();
       List<Country> countryList = countryService.getAllCountries();
        for (int i = 0; i < countryList.size(); i++) {
            if (countryList.get(i).getLanguages().containsValue(language)){
                Country country = countryList.get(i);
                CountryDTO countryDTO = new CountryDTO(country.getCode(), country.getName());
                countryDTOS.add(countryDTO);
            }
        }
        return ResponseEntity.ok(countryDTOS);

    }



}