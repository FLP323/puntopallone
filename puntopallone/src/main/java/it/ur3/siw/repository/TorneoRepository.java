package it.ur3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.ur3.siw.model.Torneo;

public interface TorneoRepository extends JpaRepository<Torneo, Long>{

	@Query("SELECT t FROM Torneo t "
			+ "LEFT JOIN FETCH t.squadrePartecipanti "
			+ "LEFT JOIN FETCH t.partiteDelTorneo "
			+ "WHERE t.id = :id")
	Optional<Torneo> findByIdWithAssociazioni(@Param("id") Long id);

}
