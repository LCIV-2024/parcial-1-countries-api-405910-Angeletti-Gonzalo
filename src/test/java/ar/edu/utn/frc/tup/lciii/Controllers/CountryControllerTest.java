package ar.edu.utn.frc.tup.lciii.Controllers;

import ar.edu.utn.frc.tup.lciii.controllers.CountryController;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CountryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    public void testGetAllCountries() throws Exception {
        // Crear objetos simulados (mocks) para la prueba
        List<Country> mockCountries = Arrays.asList(
                new Country("AR", "Argentina"),
                new Country("BR", "Brazil")
        );

        when(countryService.getAllCountries()).thenReturn(mockCountries);

        // Simular el comportamiento del endpoint
        mockMvc.perform(get("/countries"))
                .andExpect(status().isOk());

        // Verificar que el método del servicio fue llamado
        List<CountryDTO> expectedCountryDTOs = Arrays.asList(
                new CountryDTO("AR", "Argentina"),
                new CountryDTO("BR", "Brazil")
        );

        ResponseEntity<List<CountryDTO>> response = countryController.getAllCountries();
        assertEquals(ResponseEntity.ok(expectedCountryDTOs), response);
    }

    @Test
    void getCountriesByContinent() {
    }

    @Test
    void getCountriesByLanguage() {
    }

    @Test
    void getCountriesMostBorder() {
    }
}