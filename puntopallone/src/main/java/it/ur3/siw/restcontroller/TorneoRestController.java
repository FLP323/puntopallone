package it.ur3.siw.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.ur3.siw.model.Torneo;
import it.ur3.siw.service.TorneoService;

@RestController
@RequestMapping("/rest/tornei")
public class TorneoRestController {
	
	private TorneoService torneoService;
	
	public TorneoRestController (TorneoService torneoService) {
		this.torneoService = torneoService;
	}
	
	@GetMapping
	public List<Torneo> getAll(
	        @RequestParam(required = false) String nome,
	        @RequestParam(required = false) Integer annoMin,
	        @RequestParam(required = false) Integer annoMax) {
	    
	    if (nome != null || annoMin != null || annoMax != null) {
	        return torneoService.findByFilters(nome, annoMin, annoMax);
	    }
	    return torneoService.findAll();
	}

}
