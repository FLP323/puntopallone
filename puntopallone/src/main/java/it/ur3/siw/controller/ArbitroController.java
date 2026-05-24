package it.ur3.siw.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.ur3.siw.model.Arbitro;
import it.ur3.siw.service.ArbitroService;

@Controller
public class ArbitroController {
	
	private final ArbitroService arbitroService;
	
	public ArbitroController(ArbitroService arbitroService) {
		this.arbitroService = arbitroService;
	}
	
	@GetMapping("/arbitri")
	public String list(Model model) {
		model.addAttribute("listaArbitri", arbitroService.findAll());
	     return "arbitri/list";
	}
	
	@GetMapping("/arbitri/{id}")
    public String show(@PathVariable Long id, Model model) {
        Optional<Arbitro> optional = arbitroService.findByIdWithPartite(id);
        if (optional.isEmpty()) {
            return "redirect:/arbitri";
        }
        model.addAttribute("arbitro", optional.get());
        return "arbitri/show";
    }
}