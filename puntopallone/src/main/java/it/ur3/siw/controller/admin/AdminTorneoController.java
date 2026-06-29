package it.ur3.siw.controller.admin;

import it.ur3.siw.model.Squadra;
import it.ur3.siw.model.Torneo;
import it.ur3.siw.service.SquadraService;
import it.ur3.siw.service.TorneoService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tornei")
public class AdminTorneoController {

	private final TorneoService torneoService;
	private final SquadraService squadraService;

	public AdminTorneoController(TorneoService torneoService, SquadraService squadraService) {
		this.torneoService = torneoService;
		this.squadraService = squadraService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("tornei", torneoService.findAll());
		return "admin/tornei/list";
	}

	@GetMapping("/new")
	public String newTorneo(Model model) {
		model.addAttribute("torneo", new Torneo());
		model.addAttribute("squadreDisponibili", squadraService.findAll()); // per la select
		return "admin/tornei/form";
	}

	@PostMapping
	public String saveTorneo(@Valid @ModelAttribute("torneo") Torneo torneo, BindingResult br,
			@RequestParam(name = "squadreIds", required = false) List<Long> squadreIds, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("squadreDisponibili", squadraService.findAll());
			return "admin/tornei/form";
		}
		// Associazione squadre
		if (squadreIds != null && !squadreIds.isEmpty()) {
			Set<Squadra> squadre = squadreIds.stream()
					.map(id -> squadraService.findById(id)
							.orElseThrow(() -> new IllegalArgumentException("Squadra non trovata")))
					.collect(Collectors.toSet());
			torneo.setSquadrePartecipanti(squadre);
		}
		torneoService.save(torneo);
		return "redirect:/admin/tornei";
	}

	@GetMapping("/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		Torneo torneo = torneoService.findByIdWithAssociazioni(id) // carica le squadre esistenti
				.orElseThrow(() -> new IllegalArgumentException("Torneo non trovato"));
		model.addAttribute("torneo", torneo);
		model.addAttribute("squadreDisponibili", squadraService.findAll());
		return "admin/tornei/form";
	}

	@PostMapping("/{id}/edit")
	public String updateTorneo(@PathVariable Long id, @Valid @ModelAttribute("torneo") Torneo torneo, BindingResult br,
			@RequestParam(name = "squadreIds", required = false) List<Long> squadreIds, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("squadreDisponibili", squadraService.findAll());
			return "admin/tornei/form";
		}
		// Carica il torneo esistente e aggiorna i campi
		Torneo existing = torneoService.findByIdWithAssociazioni(id)
				.orElseThrow(() -> new IllegalArgumentException("Torneo non trovato"));
		existing.setNome(torneo.getNome());
		existing.setAnno(torneo.getAnno());
		existing.setDescrizione(torneo.getDescrizione());

		// Sincronizza squadre
		if (squadreIds != null) {
			Set<Squadra> nuoveSquadre = squadreIds.stream()
					.map(sid -> squadraService.findById(sid)
							.orElseThrow(() -> new IllegalArgumentException("Squadra non trovata")))
					.collect(Collectors.toSet());
			existing.getSquadrePartecipanti().clear();
			existing.getSquadrePartecipanti().addAll(nuoveSquadre);
		} else {
			existing.getSquadrePartecipanti().clear();
		}
		torneoService.save(existing);
		return "redirect:/admin/tornei";
	}
	
    @GetMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable Long id, Model model) {
        Torneo torneo = torneoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Torneo non trovato"));
        model.addAttribute("torneo", torneo);
        return "admin/tornei/delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteTorneo(@PathVariable Long id) {
        torneoService.delete(id);
        return "redirect:/admin/tornei";
    }
}