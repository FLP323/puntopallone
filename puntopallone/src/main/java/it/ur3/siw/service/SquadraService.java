package it.ur3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Squadra;
import it.ur3.siw.repository.SquadraRepository;

@Service
public class SquadraService {

	private final SquadraRepository squadraRepository;

	public SquadraService(SquadraRepository squadraRepository) {
		this.squadraRepository = squadraRepository;
	}
	
	@Transactional(readOnly = true)
	public List<Squadra> findAll() {
		return squadraRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Squadra> findByIdWithAssociazioni(Long id) {
		return squadraRepository.findByIdWithAssociazioni(id);
	}
}
