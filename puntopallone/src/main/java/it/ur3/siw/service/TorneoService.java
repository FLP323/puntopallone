package it.ur3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Torneo;
import it.ur3.siw.repository.TorneoRepository;

@Service
public class TorneoService {

	private final TorneoRepository torneoRepository;

	public TorneoService(TorneoRepository torneoRepository) {
		this.torneoRepository = torneoRepository;
	}

	@Transactional(readOnly = true)
	public List<Torneo> findAll() {
		return torneoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Torneo> findByIdWithAssociazioni(Long id) {
		return torneoRepository.findByIdWithAssociazioni(id);
	}
}