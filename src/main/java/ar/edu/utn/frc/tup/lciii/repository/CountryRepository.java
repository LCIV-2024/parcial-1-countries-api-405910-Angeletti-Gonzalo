package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.Entities.ContryEntity;
import ar.edu.utn.frc.tup.lciii.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Completar segun corresponda
 */
@Repository
public interface CountryRepository extends JpaRepository<ContryEntity, Long> {

    //Borrar

    @Query("SELECT e FROM ContryEntity e WHERE e.id = :contry_id")
    Optional<Country> itExists(Long contry_id);
}
