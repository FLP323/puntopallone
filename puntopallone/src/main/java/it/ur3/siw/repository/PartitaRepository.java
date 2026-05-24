package it.ur3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.ur3.siw.model.Partita;

public interface PartitaRepository extends JpaRepository<Partita, Long>{

	@Query("SELECT p FROM Partita p " +
	           "LEFT JOIN FETCH p.squadraInCasa " +
	           "LEFT JOIN FETCH p.squadraInTrasferta " +
	           "LEFT JOIN FETCH p.torneoDiAppartenenza " +
	           "LEFT JOIN FETCH p.arbitroInCarica " +
	           "WHERE p.id = :id")
	    Optional<Partita> findByIdWithAssociazioni(@Param("id") Long id);

}
