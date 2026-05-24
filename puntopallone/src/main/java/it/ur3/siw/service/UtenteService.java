package it.ur3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Utente;
import it.ur3.siw.repository.UtenteRepository;

@Service
public class UtenteService {
	
	private final UtenteRepository utenteRepository;

	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}

	@Transactional(readOnly = true)
	public List<Utente> findAll() {
		return utenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Utente> findById(Long id) {
		return utenteRepository.findById(id);
	}
}
