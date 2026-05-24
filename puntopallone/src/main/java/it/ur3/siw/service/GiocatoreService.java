package it.ur3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Giocatore;
import it.ur3.siw.repository.GiocatoreRepository;

@Service
public class GiocatoreService {

	private final GiocatoreRepository giocatoreRepository;

	public GiocatoreService(GiocatoreRepository giocatoreRepository) {
		this.giocatoreRepository = giocatoreRepository;
	}

	@Transactional(readOnly = true)
	public List<Giocatore> findAll() {
		return giocatoreRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Giocatore> findById(Long id) {
		return giocatoreRepository.findByIdWithSquadra(id);
	}
}
