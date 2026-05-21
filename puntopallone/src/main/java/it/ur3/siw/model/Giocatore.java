package it.ur3.siw.model;

import java.time.LocalDate;
import java.util.Objects;

import it.ur3.siw.model.enums.PlayerRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class Giocatore {

	/* ATTRIBUTI */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@NotBlank
	@Column(nullable = false)
	private String cognome;

	@NotNull
	@Past
	@Column(nullable = false)
	private LocalDate dataDiNascita;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private PlayerRole ruolo;
	
	@NotNull
	@Column(nullable = false)
	private Integer altezza;

	/* ASSOCIAZIONI */
	
	// Associazione con una singola squadra
	@NotNull
	@ManyToOne
	private Squadra squadraDiAppartenenza;
	
	/* GETTERS E SETTERS */
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public PlayerRole getRuolo() {
		return ruolo;
	}

	public void setRuolo(PlayerRole ruolo) {
		this.ruolo = ruolo;
	}

	public Integer getAltezza() {
		return altezza;
	}

	public void setAltezza(Integer altezza) {
		this.altezza = altezza;
	}

	public Squadra getSquadraDiAppartenenza() {
		return squadraDiAppartenenza;
	}

	public void setSquadraDiAppartenenza(Squadra squadraDiAppartenenza) {
		this.squadraDiAppartenenza = squadraDiAppartenenza;
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
		Giocatore other = (Giocatore) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
