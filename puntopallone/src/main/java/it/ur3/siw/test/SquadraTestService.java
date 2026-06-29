package it.ur3.siw.test;

import it.ur3.siw.model.Squadra;
import it.ur3.siw.repository.SquadraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SquadraTestService {

    private final SquadraRepository squadraRepository;

    public SquadraTestService(SquadraRepository squadraRepository) {
        this.squadraRepository = squadraRepository;
    }

    @Transactional
    public void testLazy(Long id) {
        Squadra squadra = squadraRepository.findById(id).orElseThrow();
        squadra.getTorneiPartecipati().size();
        squadra.getRosa().size();
        squadra.getPartiteInCasa().size();
        squadra.getPartiteInTrasferta().size();
    }

    @Transactional
    public void testJoinFetch(Long id) {
        Squadra squadra = squadraRepository.findByIdJOINFETCH(id).orElseThrow();
        squadra.getTorneiPartecipati().size();
        squadra.getRosa().size();
        squadra.getPartiteInCasa().size();
        squadra.getPartiteInTrasferta().size();
    }

    @Transactional
    public void testEntityGraph(Long id) {
        Squadra squadra = squadraRepository.findByIdWithAssociazioni(id).orElseThrow();
        squadra.getTorneiPartecipati().size();
        squadra.getRosa().size();
        squadra.getPartiteInCasa().size();
        squadra.getPartiteInTrasferta().size();
    }
}