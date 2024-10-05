package ar.edu.utn.frc.tup.lciii.Controllers;

import ar.edu.utn.frc.tup.lciii.controllers.CountryController;
import ar.edu.utn.frc.tup.lciii.dtos.common.CountryDTO;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CountryControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CountryService countryService;

    @Mock
    private CountryController countryController;


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
    void getCountriesByContinent() {
        String continente = "Americas";
        ResponseEntity<List<CountryDTO>> result = countryController.getCountriesByContinent(continente);
        assertEquals(56, Objects.requireNonNull(result.getBody()).size());
        //assertEquals();

    }

    @Test
    void getCountriesByLanguage() {

    }

    @Test
    void getCountriesMostBorder() {
    }
}