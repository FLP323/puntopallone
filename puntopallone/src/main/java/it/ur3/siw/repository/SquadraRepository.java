package it.ur3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.ur3.siw.model.Squadra;

public interface SquadraRepository extends JpaRepository<Squadra, Long>{

	@Query("SELECT s FROM Squadra s "
			+ "LEFT JOIN FETCH s.torneiPartecipati "
			+ "LEFT JOIN FETCH s.rosa "
			+ "LEFT JOIN FETCH s.partiteInCasa "
			+ "LEFT JOIN FETCH s.partiteInTrasferta "
			+ "WHERE s.id = :id")
	Optional<Squadra> findByIdWithAssociazioni(@Param("id") Long id);

}
