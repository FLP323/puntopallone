package it.ur3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ur3.siw.model.Giocatore;
import it.ur3.siw.model.Partita;
import it.ur3.siw.model.Squadra;
import it.ur3.siw.model.Torneo;
import it.ur3.siw.repository.GiocatoreRepository;
import it.ur3.siw.repository.PartitaRepository;
import it.ur3.siw.repository.SquadraRepository;
import it.ur3.siw.repository.TorneoRepository;

@Service
public class SquadraService {

	private final SquadraRepository squadraRepository;
    private final GiocatoreRepository giocatoreRepository;
    private final PartitaRepository partitaRepository;
    private final TorneoRepository torneoRepository;

    public SquadraService(SquadraRepository squadraRepository,
                          GiocatoreRepository giocatoreRepository,
                          PartitaRepository partitaRepository,
                          TorneoRepository torneoRepository) {
        this.squadraRepository = squadraRepository;
        this.giocatoreRepository = giocatoreRepository;
        this.partitaRepository = partitaRepository;
        this.torneoRepository = torneoRepository;
    }

	@Transactional(readOnly = true)
	public List<Squadra> findAll() {
		return squadraRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Squadra> findByIdWithAssociazioni(Long id) {
		return squadraRepository.findByIdWithAssociazioni(id);
	}

	@Transactional(readOnly = true)
	public Optional<Squadra> findById(Long id) {
		return squadraRepository.findById(id);
	}

	@Transactional
	public Squadra save(Squadra squadra) {
		return squadraRepository.save(squadra);
	}

	@Transactional
	public Squadra update(Long id, Squadra squadraAggiornata) {
		Squadra squadra = squadraRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Squadra non trovata"));
		squadra.setNome(squadraAggiornata.getNome());
		squadra.setAnnoDiFondazione(squadraAggiornata.getAnnoDiFondazione());
		squadra.setCittà(squadraAggiornata.getCittà());
		return squadraRepository.save(squadra);
	}

    @Transactional
    public void delete(Long id) {
        // 1. Carica la squadra
        Squadra squadra = squadraRepository.findByIdWithAssociazioni(id)
                .orElseThrow(() -> new IllegalArgumentException("Squadra non trovata"));

        // 2. Rimuovi la squadra da tutti i tornei a cui partecipa
        for (Torneo torneo : squadra.getTorneiPartecipati()) {
            torneo.getSquadrePartecipanti().remove(squadra);
            torneoRepository.save(torneo);
        }
        squadra.getTorneiPartecipati().clear();  // pulisce anche lato Java

        // 3. Sgancia tutti i giocatori
        List<Giocatore> giocatori = giocatoreRepository.findBySquadraDiAppartenenza(squadra);
        for (Giocatore g : giocatori) {
            g.setSquadraDiAppartenenza(null);
        }
        giocatoreRepository.saveAll(giocatori);

        // 4. Elimina tutte le partite in cui la squadra compare (casa o trasferta)
        List<Partita> partite = partitaRepository.findAllBySquadra(squadra);
        // Elimina automaticamente i commenti associati
        partitaRepository.deleteAll(partite);

        // 5. Elimina la squadra
        squadraRepository.delete(squadra);
    }
}
