package it.ur3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.ur3.siw.model.Arbitro;

public interface ArbitroRepository extends JpaRepository<Arbitro, Long>{
	
	@Query("SELECT a FROM Arbitro a LEFT JOIN FETCH a.partiteDoveArbitra WHERE a.id = :id")
    Optional<Arbitro> findByIdWithPartite(@Param("id") Long id);
}