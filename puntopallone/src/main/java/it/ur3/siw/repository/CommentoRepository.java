package it.ur3.siw.repository;

import it.ur3.siw.model.Commento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface CommentoRepository extends JpaRepository<Commento, Long> {

	// Carica i commenti di una partita con i loro autori, ordinati per data
	@Query("SELECT c FROM Commento c "
			+ "JOIN FETCH c.commentatore "
			+ "WHERE c.partitaCommentata.id = :partitaId "
			+ "ORDER BY c.dataDelCommento DESC")
	List<Commento> findByPartitaIdWithUtente(@Param("partitaId") Long partitaId);

	// Carica un singolo commento con il suo autore
	@Query("SELECT c FROM Commento c JOIN FETCH c.commentatore WHERE c.id = :id")
	Optional<Commento> findByIdWithUtente(@Param("id") Long id);
}