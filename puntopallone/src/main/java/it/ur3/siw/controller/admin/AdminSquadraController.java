package it.ur3.siw.controller.admin;

import it.ur3.siw.model.Squadra;
import it.ur3.siw.service.SquadraService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/squadre")
public class AdminSquadraController {

    private final SquadraService squadraService;

    public AdminSquadraController(SquadraService squadraService) {
        this.squadraService = squadraService;
    }
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("squadre", squadraService.findAll());
        return "admin/squadre/list";
    }
    
    @GetMapping("/new")
    public String newSquadra(Model model) {
        model.addAttribute("squadra", new Squadra());
        return "admin/squadre/form";
    }
    
    @PostMapping
    public String saveSquadra(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult br) {
        if (br.hasErrors()) {
            return "admin/squadre/form";
        }
        squadraService.save(squadra);
        return "redirect:/admin/squadre";
    }
    
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Squadra squadra = squadraService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Squadra non trovata"));
        model.addAttribute("squadra", squadra);
        return "admin/squadre/form";
    }
    
    @PostMapping("/{id}/edit")
    public String updateSquadra(@PathVariable Long id,
                                @Valid @ModelAttribute("squadra") Squadra squadra, BindingResult br) {
        if (br.hasErrors()) {
            return "admin/squadre/form";
        }
        squadraService.update(id, squadra);
        return "redirect:/admin/squadre";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable Long id, Model model) {
        Squadra squadra = squadraService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Squadra non trovata"));
        model.addAttribute("squadra", squadra);
        return "admin/squadre/delete";
    }
    
    @PostMapping("/{id}/delete")
    public String deleteSquadra(@PathVariable Long id) {
        squadraService.delete(id);
        return "redirect:/admin/squadre";
    }
}