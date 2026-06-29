package it.ur3.siw.controller.admin;

import it.ur3.siw.model.Partita;
import it.ur3.siw.model.Arbitro;
import it.ur3.siw.model.Squadra;
import it.ur3.siw.model.Torneo;
import it.ur3.siw.service.PartitaService;
import it.ur3.siw.service.ArbitroService;
import it.ur3.siw.service.SquadraService;
import it.ur3.siw.service.TorneoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin/partite")
public class AdminPartitaController {

	private final PartitaService partitaService;
	private final ArbitroService arbitroService;
	private final SquadraService squadraService;
	private final TorneoService torneoService;

	public AdminPartitaController(PartitaService partitaService, ArbitroService arbitroService,
			SquadraService squadraService, TorneoService torneoService) {
		this.partitaService = partitaService;
		this.arbitroService = arbitroService;
		this.squadraService = squadraService;
		this.torneoService = torneoService;
	}

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Instant.class, new InstantEditor());
        binder.setDisallowedFields("torneoDiAppartenenza", "squadraInCasa",
                "squadraInTrasferta", "arbitroInCarica");
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("partite", partitaService.findAll());
        return "admin/partite/list";
    }

    @GetMapping("/new")
    public String newPartita(Model model) {
        model.addAttribute("partita", new Partita());
        model.addAttribute("tornei", torneoService.findAll());
        model.addAttribute("squadre", squadraService.findAll());
        model.addAttribute("arbitri", arbitroService.findAll());
        return "admin/partite/form";
    }

    @PostMapping
    public String savePartita(@Valid @ModelAttribute("partita") Partita partita, BindingResult br,
                              @RequestParam("torneoId") Long torneoId,
                              @RequestParam("squadraCasaId") Long squadraCasaId,
                              @RequestParam("squadraOspiteId") Long squadraOspiteId,
                              @RequestParam("arbitroId") Long arbitroId,
                              Model model) {
        if (br.hasErrors()) {
            aggiungiDatiAlModel(model);
            return "admin/partite/form";
        }
        settaAssociazioni(partita, torneoId, squadraCasaId, squadraOspiteId, arbitroId);
        partitaService.save(partita);
        return "redirect:/admin/partite";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Partita partita = partitaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Partita non trovata"));
        model.addAttribute("partita", partita);
        if (partita.getDataEOraInizioPartita() != null) {
            LocalDateTime ldt = LocalDateTime.ofInstant(partita.getDataEOraInizioPartita(),
                    ZoneId.of("Europe/Rome"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            model.addAttribute("dataOraFormattata", ldt.format(formatter));
        }
        aggiungiDatiAlModel(model);
        return "admin/partite/form";
    }

    @PostMapping("/{id}/edit")
    public String updatePartita(@PathVariable Long id,
                                @Valid @ModelAttribute("partita") Partita partita, BindingResult br,
                                @RequestParam("torneoId") Long torneoId,
                                @RequestParam("squadraCasaId") Long squadraCasaId,
                                @RequestParam("squadraOspiteId") Long squadraOspiteId,
                                @RequestParam("arbitroId") Long arbitroId,
                                Model model) {
        if (br.hasErrors()) {
            aggiungiDatiAlModel(model);
            return "admin/partite/form";
        }
        Partita existing = partitaService.findById(id).orElseThrow();
        existing.setDataEOraInizioPartita(partita.getDataEOraInizioPartita());
        existing.setLuogo(partita.getLuogo());
        existing.setGoalsHome(partita.getGoalsHome());
        existing.setGoalsAway(partita.getGoalsAway());
        existing.setStato(partita.getStato());
        settaAssociazioni(existing, torneoId, squadraCasaId, squadraOspiteId, arbitroId);
        partitaService.save(existing);
        return "redirect:/admin/partite";
    }

    @GetMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable Long id, Model model) {
        Partita partita = partitaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Partita non trovata"));
        model.addAttribute("partita", partita);
        return "admin/partite/delete";
    }

    @PostMapping("/{id}/delete")
    public String deletePartita(@PathVariable Long id) {
        partitaService.delete(id);
        return "redirect:/admin/partite";
    }

    private void settaAssociazioni(Partita partita, Long torneoId, Long squadraCasaId,
                                   Long squadraOspiteId, Long arbitroId) {
        Torneo torneo = torneoService.findById(torneoId).orElse(null);
        Squadra casa = squadraService.findById(squadraCasaId).orElse(null);
        Squadra ospite = squadraService.findById(squadraOspiteId).orElse(null);
        Arbitro arbitro = arbitroService.findById(arbitroId).orElse(null);
        partita.setTorneoDiAppartenenza(torneo);
        partita.setSquadraInCasa(casa);
        partita.setSquadraInTrasferta(ospite);
        partita.setArbitroInCarica(arbitro);
    }

    private void aggiungiDatiAlModel(Model model) {
        model.addAttribute("tornei", torneoService.findAll());
        model.addAttribute("squadre", squadraService.findAll());
        model.addAttribute("arbitri", arbitroService.findAll());
    }
}