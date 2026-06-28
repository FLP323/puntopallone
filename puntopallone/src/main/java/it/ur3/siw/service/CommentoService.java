package it.ur3.siw.service;

import it.ur3.siw.model.Commento;
import it.ur3.siw.model.Partita;
import it.ur3.siw.model.Utente;
import it.ur3.siw.repository.CommentoRepository;
import it.ur3.siw.repository.PartitaRepository;
import it.ur3.siw.repository.UtenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentoService {

	private final CommentoRepository commentoRepository;
	private final PartitaRepository partitaRepository;
	private final UtenteRepository utenteRepository;

	public CommentoService(CommentoRepository commentoRepository, PartitaRepository partitaRepository,
			UtenteRepository utenteRepository) {
		this.commentoRepository = commentoRepository;
		this.partitaRepository = partitaRepository;
		this.utenteRepository = utenteRepository;
	}

	@Transactional
	public Commento addCommento(String corpo, Long partitaId, String username) {
		Utente utente = utenteRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

		Partita partita = partitaRepository.findById(partitaId)
				.orElseThrow(() -> new IllegalArgumentException("Partita non trovata"));

		Commento commento = new Commento();
		commento.setCorpo(corpo);
		commento.setCommentatore(utente);
		commento.setPartitaCommentata(partita);
		commento.setDataDelCommento(Instant.now());
		commento.setMiPiace(0);
		commento.setNonMiPiace(0);

		return commentoRepository.save(commento);
	}

	public Commento findByIdWithUtente(Long id) {
		return commentoRepository.findByIdWithUtente(id)
				.orElseThrow(() -> new IllegalArgumentException("Commento non trovato"));
	}

	@Transactional
	public Commento updateCorpo(Long commentoId, String nuovoCorpo, String username) {
		Commento commento = commentoRepository.findByIdWithUtente(commentoId)
				.orElseThrow(() -> new IllegalArgumentException("Commento non trovato"));

		if (!commento.getCommentatore().getUsername().equals(username)) {
			throw new SecurityException("Non sei autorizzato a modificare questo commento");
		}

		commento.setCorpo(nuovoCorpo);
		return commentoRepository.save(commento);
	}

	public List<Commento> getCommentiByPartita(Long partitaId) {
		return commentoRepository.findByPartitaIdWithUtente(partitaId);
	}
}