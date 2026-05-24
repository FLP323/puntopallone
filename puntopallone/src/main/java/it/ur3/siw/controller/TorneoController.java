package it.ur3.siw.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.ur3.siw.model.Torneo;
import it.ur3.siw.service.TorneoService;

@Controller
public class TorneoController {

    private final TorneoService torneoService;

    public TorneoController(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    @GetMapping("/tornei")
    public String list(Model model) {
        model.addAttribute("listaTornei", torneoService.findAll());
        return "tornei/list";
    }
    
    @GetMapping("/tornei/{id}")
    public String show(@PathVariable Long id, Model model) {
    	Optional<Torneo> optional = torneoService.findByIdWithAssociazioni(id);
    	if (optional.isEmpty()) {
            return "redirect:/tornei";
        }
        model.addAttribute("torneo", optional.get());
        return "tornei/show";
    }
}
