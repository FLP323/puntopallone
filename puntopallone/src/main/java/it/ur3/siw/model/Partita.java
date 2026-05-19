package it.ur3.siw.model;

import java.time.Instant;

import it.ur3.siw.model.enums.PlayerRole;
import it.ur3.siw.model.enums.Stato;
import it.ur3.siw.validation.ValidGoals;
import it.ur3.siw.validation.ValidMatchDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@ValidMatchDate
@ValidGoals
public class Partita {

	/* ATTRIBUTI */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private Instant dataEOraInizioPartita;
	
	@NotBlank
	@Column(nullable = false)
	private String luogo;
	
	@Min(value = 0, message = "I gol non possono essere negativi")
	private Integer goalsHome;
	
	@Min(value = 0, message = "I gol non possono essere negativi")
	private Integer goalsAway;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 11)
	private Stato stato;
	
	/* ASSOCIAZIONI */

	/* GETTERS E SETTERS */
	
	public Long getId() {
		return id;
	}
	
	public Instant getDataEOraInizioPartita() {
		return dataEOraInizioPartita;
	}

	public void setDataEOraInizioPartita(Instant dataEOraInizioPartita) {
		this.dataEOraInizioPartita = dataEOraInizioPartita;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public Integer getGoalsHome() {
		return goalsHome;
	}

	public void setGoalsHome(Integer goalsHome) {
		this.goalsHome = goalsHome;
	}

	public Integer getGoalsAway() {
		return goalsAway;
	}

	public void setGoalsAway(Integer goalsAway) {
		this.goalsAway = goalsAway;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
}
