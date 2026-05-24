package it.ur3.siw.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.ur3.siw.model.Partita;
import it.ur3.siw.service.PartitaService;

@Controller
public class PartitaController {

    private final PartitaService partitaService;

    public PartitaController(PartitaService partitaService) {
        this.partitaService = partitaService;
    }

    @GetMapping("/partite")
    public String list(Model model) {
        model.addAttribute("listaPartite", partitaService.findAll());
        return "partite/list";
    }
    
    @GetMapping("/partite/{id}")
    public String show(@PathVariable Long id, Model model) {
        Optional<Partita> optional = partitaService.findById(id);
        if (optional.isEmpty()) {
            return "redirect:/partite";
        }
        model.addAttribute("partita", optional.get());
        return "partite/show";
    }
}
