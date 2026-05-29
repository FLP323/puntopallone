package it.ur3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.ur3.siw.model.Utente;
import it.ur3.siw.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {

	private final UtenteService utenteService;

	public AuthenticationController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		return "authentication/register";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "authentication/login";
	}

	@GetMapping("/admin/HompageAdmin")
	public String showHomepageAdmin() {
		return "/admin/HompageAdmin";
	}

	@PostMapping("/register")
	public String registraUtente(@Valid @ModelAttribute("utente") Utente utente, BindingResult utenteBindingResult) {
		if (!utenteBindingResult.hasErrors()) {
			try {
				utenteService.saveUtente(utente);
			} catch (Exception e) {
				utenteBindingResult.rejectValue("username", "error.utente", e.getMessage());
				return "authentication/register";
			}
			return "redirect:/login?registerSuccess";
		}
		return "authentication/register";
	}
}
