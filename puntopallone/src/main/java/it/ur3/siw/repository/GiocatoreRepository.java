package it.ur3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.ur3.siw.model.Giocatore;
import it.ur3.siw.model.Squadra;

public interface GiocatoreRepository extends JpaRepository<Giocatore, Long>{

	@Query("SELECT g FROM Giocatore g LEFT JOIN FETCH g.squadraDiAppartenenza WHERE g.id = :id")
	Optional<Giocatore> findByIdWithSquadra(@Param("id") Long id);
	
	@Query("SELECT g FROM Giocatore g WHERE g.squadraDiAppartenenza = :squadra")
	List<Giocatore> findBySquadraDiAppartenenza(@Param("squadra") Squadra squadra);

}
