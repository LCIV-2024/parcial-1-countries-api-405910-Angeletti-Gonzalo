package ar.edu.utn.frc.tup.lciii.Controllers;

import ar.edu.utn.frc.tup.lciii.controllers.CountryController;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class CountryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;
    @Autowired
    private CountryController countryController2;


    @Test
    public void testGetAllCountries()  {
        CountryDTO countryDTO1 = new CountryDTO("aa","aa");
        CountryDTO countryDTO2 = new CountryDTO("aa","aa");
        CountryDTO countryDTO3 = new CountryDTO("aa","aa");
        List<CountryDTO> countryDTOList= new ArrayList<>();
        countryDTOList.add(countryDTO1);
        countryDTOList.add(countryDTO2);
        countryDTOList.add(countryDTO3);
        //ResponseEntity<> = ResponseEntity.ok(countryDTOList);
        when(countryController.getAllCountries()).thenReturn(ResponseEntity.ok(countryDTOList));
        ResponseEntity<List<CountryDTO>> result = countryController.getAllCountries();
        assertEquals(3, Objects.requireNonNull(result.getBody()).size());
    }
    @Test
    public void testGetAllCountries1()  {
        ResponseEntity <List<CountryDTO>> response = countryController2.getAllCountries();
        assertEquals(250, Objects.requireNonNull(response.getBody()).size());

    }

    @Test
    void getCountriesByContinent() {
        String continente = "Americas";
        List<Country> countryList = Arrays.asList(
                new Country("Argentina", 1, 1, "AR", "America", Arrays.asList("CHL", "BRA"), Map.of("es", "Spanish")),
                new Country("Brazil", 2, 1, "BR", "America", Arrays.asList("ARG", "URY"), Map.of("pt", "Portuguese")),
                new Country("Germany", 3, 1, "DE", "Europe", Arrays.asList("AUT", "FRA"), Map.of("de", "German"))
        );

        ResponseEntity<List<CountryDTO>> result = countryController2.getCountriesByContinent(continente);
        assertEquals(56, Objects.requireNonNull(result.getBody()).size());
        //assertEquals();


    }
    @Test
    void GetCountriesByContinentTest() {
        List<Country> countryList = Arrays.asList(
                new Country("Argentina", 1, 1, "AR", "America", Arrays.asList("CHL", "BRA"), Map.of("es", "Spanish")),
                new Country("Brazil", 2, 1, "BR", "America", Arrays.asList("ARG", "URY"), Map.of("pt", "Portuguese")),
                new Country("Germany", 3, 1, "DE", "Europe", Arrays.asList("AUT", "FRA"), Map.of("de", "German"))
        );

        when(countryService.getAllCountries()).thenReturn(countryList);
        ResponseEntity<List<CountryDTO>> response = countryController.getCountriesByContinent("America");

        assertNotNull(response);

        assertEquals(2, response.getBody().size());

        assertEquals("Argentina", response.getBody().get(0).getName());

        assertEquals("Brazil", response.getBody().get(1).getName());
        verify(countryService, times(1)).getAllCountries();
    }

    @Test
    void getCountriesByLanguage() {

    }

    @Test
    void getCountriesMostBorder() {

        // Datos de prueba: países con diferentes números de fronteras
        List<Country> countryList = Arrays.asList(
                new Country("Argentina", 1, 1, "AR", "America", Arrays.asList("CHL", "BRA"), Map.of("es", "Spanish")),
                new Country("Brazil", 2, 1, "BR", "America", Arrays.asList("ARG", "URY"), Map.of("pt", "Portuguese")),
                new Country("Germany", 3, 1, "DE", "Europe", Arrays.asList("AUT", "FRA", "SA", "SAS"), Map.of("de", "German"))
        );

        when(countryService.getAllCountries()).thenReturn(countryList);

        ResponseEntity<CountryDTO> response = countryController.getCountriesMostBorder();

        assertNotNull(response);
        assertEquals("Germany", response.getBody().getName());
        assertEquals("DE", response.getBody().getCode());

        verify(countryService, times(1)).getAllCountries();
    }
}