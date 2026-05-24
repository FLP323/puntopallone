package it.ur3.siw.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.ur3.siw.model.Giocatore;
import it.ur3.siw.service.GiocatoreService;

@Controller
public class GiocatoreController {

    private final GiocatoreService giocatoreService;

    public GiocatoreController(GiocatoreService giocatoreService) {
        this.giocatoreService = giocatoreService;
    }

    @GetMapping("/giocatori")
    public String list(Model model) {
        model.addAttribute("listaGiocatori", giocatoreService.findAll());
        return "giocatori/list";
    }
    
    @GetMapping("/giocatori/{id}")
    public String show(@PathVariable Long id, Model model) {
    	Optional<Giocatore> optional = giocatoreService.findById(id);
    	if(optional.isEmpty()) {
    		return "redirect:/arbitri";
    	}
    	model.addAttribute("giocatore", optional.get());
    	return "giocatori/show";
    }
}