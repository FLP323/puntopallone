package it.ur3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Utente;
import it.ur3.siw.model.enums.UserRole;
import it.ur3.siw.repository.UtenteRepository;

@Service
public class UtenteService {
	
	private final UtenteRepository utenteRepository;
	private PasswordEncoder passwordEncoder;

	public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
		this.utenteRepository = utenteRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional(readOnly = true)
	public List<Utente> findAll() {
		return utenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Utente> findById(Long id) {
		return utenteRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Utente> findByUsername(String username) {
		return utenteRepository.findByUsername(username);
	}
	
	@Transactional
	public Utente saveUtente(Utente utente) {
	    if (utenteRepository.findByUsername(utente.getUsername()).isPresent()) {
	        throw new IllegalArgumentException("Username già in uso");
	    }
	    utente.setRole(UserRole.ROLE_USER);
	    utente.setPassword(passwordEncoder.encode(utente.getPassword()));
	    return utenteRepository.save(utente);
	}
}
