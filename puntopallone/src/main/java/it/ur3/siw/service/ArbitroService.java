package it.ur3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Arbitro;
import it.ur3.siw.repository.ArbitroRepository;

@Service
public class ArbitroService {

	private final ArbitroRepository arbitroRepository;

	public ArbitroService(ArbitroRepository arbitroRepository) {
		this.arbitroRepository = arbitroRepository;
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
