package it.ur3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Arbitro;
import it.ur3.siw.repository.ArbitroRepository;
import it.ur3.siw.repository.PartitaRepository;

@Service
public class ArbitroService {

	private final ArbitroRepository arbitroRepository;
	private final PartitaRepository partitaRepository;

	public ArbitroService(ArbitroRepository arbitroRepository, PartitaRepository partitaRepository) {
		this.arbitroRepository = arbitroRepository;
		this.partitaRepository = partitaRepository;
	}

	@Transactional(readOnly = true)
	public List<Arbitro> findAll() {
		return arbitroRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Arbitro> findByIdWithPartite(Long id) {
	    return arbitroRepository.findByIdWithPartite(id);
	}
}
