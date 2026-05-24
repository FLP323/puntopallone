package it.ur3.siw.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.ur3.siw.model.Squadra;
import it.ur3.siw.service.SquadraService;

@Controller
public class SquadraController {

    private final SquadraService squadraService;

    public SquadraController(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @GetMapping("/squadre")
    public String list(Model model) {
        model.addAttribute("listaSquadre", squadraService.findAll());
        return "squadre/list";
    }
    
    @GetMapping("/squadre/{id}")
    public String show(@PathVariable Long id, Model model) {
    	Optional<Squadra> optional = squadraService.findByIdWithAssociazioni(id);
    	if(optional.isEmpty()) {
    		return "redirect:/squadre";
    	}
    	model.addAttribute("squadra", optional.get());
    	return "/squadre/show";
    }
}