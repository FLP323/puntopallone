package it.ur3.siw.controller.admin;

import it.ur3.siw.model.Giocatore;
import it.ur3.siw.model.Squadra;
import it.ur3.siw.service.GiocatoreService;
import it.ur3.siw.service.SquadraService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/giocatori")
public class AdminGiocatoreController {

    private final GiocatoreService giocatoreService;
    private final SquadraService squadraService;

    public AdminGiocatoreController(GiocatoreService giocatoreService, SquadraService squadraService) {
        this.giocatoreService = giocatoreService;
        this.squadraService = squadraService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("giocatori", giocatoreService.findAll());
        return "admin/giocatori/list";
    }

    @GetMapping("/new")
    public String newGiocatore(Model model) {
        model.addAttribute("giocatore", new Giocatore());
        model.addAttribute("squadre", squadraService.findAll());
        return "admin/giocatori/form";
    }
    
    @PostMapping
    public String saveGiocatore(@Valid @ModelAttribute("giocatore") Giocatore giocatore, BindingResult br,
                                @RequestParam(name = "squadraId", required = false) Long squadraId,
                                Model model) {
        if (br.hasErrors()) {
            model.addAttribute("squadre", squadraService.findAll());
            return "admin/giocatori/form";
        }
        if (squadraId != null) {
            Squadra squadra = squadraService.findById(squadraId).orElse(null);
            giocatore.setSquadraDiAppartenenza(squadra);
        }
        giocatoreService.save(giocatore);
        return "redirect:/admin/giocatori";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Giocatore giocatore = giocatoreService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Giocatore non trovato"));
        model.addAttribute("giocatore", giocatore);
        model.addAttribute("squadre", squadraService.findAll());
        return "admin/giocatori/form";
    }

    @PostMapping("/{id}/edit")
    public String updateGiocatore(@PathVariable Long id,
                                  @Valid @ModelAttribute("giocatore") Giocatore giocatore, BindingResult br,
                                  @RequestParam(name = "squadraId", required = false) Long squadraId,
                                  Model model) {
        if (br.hasErrors()) {
            model.addAttribute("squadre", squadraService.findAll());
            return "admin/giocatori/form";
        }
        giocatoreService.update(id, giocatore);  // aggiorna tutti i campi tranne la squadra
        // Aggiorna la squadra separatamente
        Giocatore existing = giocatoreService.findById(id).orElseThrow();
        if (squadraId != null) {
            Squadra squadra = squadraService.findById(squadraId).orElse(null);
            existing.setSquadraDiAppartenenza(squadra);
        } else {
            existing.setSquadraDiAppartenenza(null);
        }
        giocatoreService.save(existing);
        return "redirect:/admin/giocatori";
    }

    @GetMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable Long id, Model model) {
        Giocatore giocatore = giocatoreService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Giocatore non trovato"));
        model.addAttribute("giocatore", giocatore);
        return "admin/giocatori/delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteGiocatore(@PathVariable Long id) {
        giocatoreService.delete(id);
        return "redirect:/admin/giocatori";
    }
}