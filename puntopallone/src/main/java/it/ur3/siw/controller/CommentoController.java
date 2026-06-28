package it.ur3.siw.controller;

import it.ur3.siw.model.Commento;
import it.ur3.siw.service.CommentoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
public class CommentoController {

	private final CommentoService commentoService;

	public CommentoController(CommentoService commentoService) {
		this.commentoService = commentoService;
	}

	@PostMapping("/partite/{idPartita}/commento")
	public String addCommento(@PathVariable Long idPartita, @RequestParam("corpo") String corpo, Principal principal) {
		commentoService.addCommento(corpo, idPartita, principal.getName());
		return "redirect:/partite/" + idPartita;
	}

	@GetMapping("/commento/{id}/edit")
	public String editForm(@PathVariable Long id, Model model, Principal principal) {
		Commento commento = commentoService.findByIdWithUtente(id);
		if (!commento.getCommentatore().getUsername().equals(principal.getName())) {
			return "redirect:/partite/" + commento.getPartitaCommentata().getId() + "?error=notowner";
		}
		model.addAttribute("commento", commento);
		return "commento/edit";
	}

	@PostMapping("/commento/{id}/edit")
	public String updateCommento(@PathVariable Long id, @RequestParam("corpo") String nuovoCorpo, Principal principal) {
		commentoService.updateCorpo(id, nuovoCorpo, principal.getName());
		Commento commento = commentoService.findByIdWithUtente(id);
		return "redirect:/partite/" + commento.getPartitaCommentata().getId();
	}
}