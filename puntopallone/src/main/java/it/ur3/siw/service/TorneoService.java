package it.ur3.siw.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Partita;
import it.ur3.siw.model.Squadra;
import it.ur3.siw.model.Torneo;
import it.ur3.siw.model.entries.ClassificaEntry;
import it.ur3.siw.model.enums.Stato;
import it.ur3.siw.repository.SquadraRepository;
import it.ur3.siw.repository.TorneoRepository;

@Service
public class TorneoService {

	private final TorneoRepository torneoRepository;
	private final SquadraRepository squadraRepository;

	public TorneoService(TorneoRepository torneoRepository, SquadraRepository squadraRepository) {
		this.torneoRepository = torneoRepository;
		this.squadraRepository = squadraRepository;
	}

	@Transactional(readOnly = true)
	public List<Torneo> findAll() {
		return torneoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Torneo> findByIdWithAssociazioni(Long id) {
		return torneoRepository.findByIdWithAssociazioni(id);
	}

	@Transactional(readOnly = true)
	public List<ClassificaEntry> calcolaClassifica(Long torneoId) {
		Torneo torneo = torneoRepository.findByIdWithPartite(torneoId)
				.orElseThrow(() -> new RuntimeException("Torneo non trovato"));

		Map<Squadra, ClassificaEntry> map = new HashMap<>();
		for (Squadra squadra : torneo.getSquadrePartecipanti()) {
			map.put(squadra, new ClassificaEntry(squadra));
		}

		for (Partita partita : torneo.getPartiteDelTorneo()) {
			if (partita.getStato() != Stato.PLAYED)
				continue;
			if (partita.getGoalsHome() == null || partita.getGoalsAway() == null)
				continue;

			Squadra casa = partita.getSquadraInCasa();
			Squadra ospite = partita.getSquadraInTrasferta();
			int golCasa = partita.getGoalsHome();
			int golOspite = partita.getGoalsAway();

			ClassificaEntry entryCasa = map.get(casa);
			ClassificaEntry entryOspite = map.get(ospite);
			if (entryCasa == null || entryOspite == null)
				continue;

			entryCasa.setPartiteGiocate(entryCasa.getPartiteGiocate() + 1);
			entryOspite.setPartiteGiocate(entryOspite.getPartiteGiocate() + 1);
			entryCasa.setGolFatti(entryCasa.getGolFatti() + golCasa);
			entryCasa.setGolSubiti(entryCasa.getGolSubiti() + golOspite);
			entryOspite.setGolFatti(entryOspite.getGolFatti() + golOspite);
			entryOspite.setGolSubiti(entryOspite.getGolSubiti() + golCasa);

			if (golCasa > golOspite) {
				entryCasa.setPunti(entryCasa.getPunti() + 3);
				entryCasa.setVinte(entryCasa.getVinte() + 1);
				entryOspite.setPerse(entryOspite.getPerse() + 1);
			} else if (golCasa < golOspite) {
				entryOspite.setPunti(entryOspite.getPunti() + 3);
				entryOspite.setVinte(entryOspite.getVinte() + 1);
				entryCasa.setPerse(entryCasa.getPerse() + 1);
			} else {
				entryCasa.setPunti(entryCasa.getPunti() + 1);
				entryOspite.setPunti(entryOspite.getPunti() + 1);
				entryCasa.setPareggiate(entryCasa.getPareggiate() + 1);
				entryOspite.setPareggiate(entryOspite.getPareggiate() + 1);
			}
		}

		List<ClassificaEntry> classifica = new ArrayList<>(map.values());
		Collections.sort(classifica);
		return classifica;
	}
	
	@Transactional
	public Torneo save(Torneo torneo) {
		return torneoRepository.save(torneo);
	}

	@Transactional
	public Torneo update(Long id, Torneo torneoAggiornato, List<Long> squadreIds) {
		Torneo torneo = torneoRepository.findByIdWithAssociazioni(id)
				.orElseThrow(() -> new IllegalArgumentException("Torneo non trovato"));
		torneo.setNome(torneoAggiornato.getNome());
		torneo.setAnno(torneoAggiornato.getAnno());
		torneo.setDescrizione(torneoAggiornato.getDescrizione());

		// Gestione squadre
		if (squadreIds != null) {
			Set<Squadra> nuoveSquadre = squadreIds.stream()
					.map(sid -> squadraRepository.findById(sid)
							.orElseThrow(() -> new IllegalArgumentException("Squadra non trovata: " + sid)))
					.collect(Collectors.toSet());
			// rimuove le vecchie e aggiunge le nuove
			torneo.getSquadrePartecipanti().clear();
			torneo.getSquadrePartecipanti().addAll(nuoveSquadre);
		}
		return torneoRepository.save(torneo);
	}

	@Transactional
	public void delete(Long id) {
		Torneo torneo = torneoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Torneo non trovato"));
		torneoRepository.delete(torneo);
	}

	@Transactional(readOnly = true)
	public Optional<Torneo> findById(Long id) {
		return torneoRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Torneo> findByFilters(String nome, Integer annoMin, Integer annoMax) {
	    String nomeLower = (nome != null && !nome.isEmpty()) ? nome.toLowerCase() : null;
	    return torneoRepository.findByFilters(nomeLower, annoMin, annoMax);
	}
}