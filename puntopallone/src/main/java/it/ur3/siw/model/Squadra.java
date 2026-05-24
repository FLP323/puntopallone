package it.ur3.siw.model;

import java.util.Objects;
import java.util.Set;

import it.ur3.siw.validation.NotFutureYear;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Squadra {
	
	/* ATTRIBUTI */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;
	
	@NotNull
	@Min(value = 1857, message = "La data di fondazione non può essere minore di 1857") // Data di nascita della prima squadra di calcio
	@NotFutureYear
	@Column(nullable = false)
	private Integer annoDiFondazione;
	
	@NotBlank
	@Column(nullable = false)
	private String città;
	
	/* ASSOCIAZIONI */
	
	// Associazione con zero o più tornei
	@ManyToMany(mappedBy = "squadrePartecipanti")
	private Set<Torneo> torneiPartecipati;
	
	// Associzione con zero o più giocatori
	@OneToMany(mappedBy = "squadraDiAppartenenza")
	private Set<Giocatore> rosa;
	
	// Associazione con zero o più partite
	@OneToMany(mappedBy = "squadraInCasa")
	private Set<Partita> partiteInCasa;
	@OneToMany(mappedBy = "squadraInTrasferta")
	private Set<Partita> partiteInTrasferta;
	
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

	public Integer getAnnoDiFondazione() {
		return annoDiFondazione;
	}

	public void setAnnoDiFondazione(Integer annoDiFondazione) {
		this.annoDiFondazione = annoDiFondazione;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public Set<Torneo> getTorneiPartecipati() {
		return torneiPartecipati;
	}

	public void setTorneiPartecipati(Set<Torneo> torneiPartecipati) {
		this.torneiPartecipati = torneiPartecipati;
	}

	public Set<Giocatore> getRosa() {
		return rosa;
	}

	public void setRosa(Set<Giocatore> rosa) {
		this.rosa = rosa;
	}

	public Set<Partita> getPartiteInCasa() {
		return partiteInCasa;
	}

	public void setPartiteInCasa(Set<Partita> partiteInCasa) {
		this.partiteInCasa = partiteInCasa;
	}

	public Set<Partita> getPartiteInTrasferta() {
		return partiteInTrasferta;
	}

	public void setPartiteInTrasferta(Set<Partita> partiteInTrasferta) {
		this.partiteInTrasferta = partiteInTrasferta;
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
		Squadra other = (Squadra) obj;
		return Objects.equals(id, other.id);
	}
}
