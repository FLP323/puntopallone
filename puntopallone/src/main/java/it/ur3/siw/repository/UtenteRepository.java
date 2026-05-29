package it.ur3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.ur3.siw.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long>{
	
	@Query("SELECT u FROM Utente u WHERE u.username = :username")
	public Optional<Utente> findByUsername(String username);
}
