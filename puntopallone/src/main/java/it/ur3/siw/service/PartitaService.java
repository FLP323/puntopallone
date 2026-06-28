package it.ur3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Partita;
import it.ur3.siw.repository.PartitaRepository;

@Service
public class PartitaService {

	private final PartitaRepository partitaRepository;

	public PartitaService(PartitaRepository partitaRepository) {
		this.partitaRepository = partitaRepository;
	}

	@Transactional(readOnly = true)
	public List<Partita> findAll() {
		return partitaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Partita> findById(Long id) {
		return partitaRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Partita> findByIdWithAssociazioni(Long id) {
		return partitaRepository.findByIdWithAssociazioni(id);
	}
}
