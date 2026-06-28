package it.ur3.siw.model;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import it.ur3.siw.model.enums.Stato;
import it.ur3.siw.validation.ValidGoals;
import it.ur3.siw.validation.ValidMatchDate;
import it.ur3.siw.validation.ValidSquadreDiverse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@ValidMatchDate
@ValidGoals
@ValidSquadreDiverse
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
	@Column(nullable = false, length = 9)
	private Stato stato;
	
	/* ASSOCIAZIONI */
	
	// Associazione con un singolo torneo
	@NotNull
	@ManyToOne
	private Torneo torneoDiAppartenenza;
	
	// Associazione con due squadre
	@NotNull
	@ManyToOne
	private Squadra squadraInCasa;
	@NotNull
	@ManyToOne
	private Squadra squadraInTrasferta;
		
	// Associazione con un arbitro
	@NotNull
	@ManyToOne
	private Arbitro arbitroInCarica;
	
	//Associazione con uno o più commenti
	@OneToMany(mappedBy = "partitaCommentata")
	private List<Commento> commentiDegliUtenti;

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

	public Squadra getSquadraInCasa() {
		return squadraInCasa;
	}

	public void setSquadraInCasa(Squadra squadraInCasa) {
		this.squadraInCasa = squadraInCasa;
	}

	public Squadra getSquadraInTrasferta() {
		return squadraInTrasferta;
	}

	public void setSquadraInTrasferta(Squadra squadraInTrasferta) {
		this.squadraInTrasferta = squadraInTrasferta;
	}

	public Torneo getTorneoDiAppartenenza() {
		return torneoDiAppartenenza;
	}

	public void setTorneoDiAppartenenza(Torneo torneoDiAppartenenza) {
		this.torneoDiAppartenenza = torneoDiAppartenenza;
	}

	public Arbitro getArbitroInCarica() {
		return arbitroInCarica;
	}

	public void setArbitroInCarica(Arbitro arbitroInCarica) {
		this.arbitroInCarica = arbitroInCarica;
	}
	
	public List<Commento> getCommentiDegliUtenti() {
		return commentiDegliUtenti;
	}

	public void setCommentiDegliUtenti(List<Commento> commentiDegliUtenti) {
		this.commentiDegliUtenti = commentiDegliUtenti;
	}
	
	/* EQUALS E HASHCODE */

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partita other = (Partita) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
