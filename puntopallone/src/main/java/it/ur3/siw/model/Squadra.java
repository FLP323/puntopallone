package it.ur3.siw.model;

import java.util.List;
import java.util.Objects;

import it.ur3.siw.validation.NotFutureYear;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	private Integer dataDiFondazione;
	
	@NotBlank
	@Column(nullable = false)
	private String città;
	
	/* ASSOCIAZIONI */
	
	// Associazione con uno o più tornei
	@ManyToMany(mappedBy = "squadrePartecipanti")
	private List<Torneo> torneiPartecipati;
	
	// Associzione con uno o più giocatori
	@OneToMany(mappedBy = "squadraDiAppartenenza")
	private List<Giocatore> rosa;
	
	// Associazione con una o più partite
	@OneToMany(mappedBy = "")
	private List<Partita> partiteInCasa;
	@OneToMany(mappedBy = "")
	private List<Partita> partiteInTrasferta;
	
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

	public Integer getDataDiFondazione() {
		return dataDiFondazione;
	}

	public void setDataDiFondazione(Integer dataDiFondazione) {
		this.dataDiFondazione = dataDiFondazione;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public List<Torneo> getTorneiPartecipati() {
		return torneiPartecipati;
	}

	public void setTorneiPartecipati(List<Torneo> torneiPartecipati) {
		this.torneiPartecipati = torneiPartecipati;
	}

	public List<Giocatore> getRosa() {
		return rosa;
	}

	public void setRosa(List<Giocatore> rosa) {
		this.rosa = rosa;
	}

	public List<Partita> getPartiteInCasa() {
		return partiteInCasa;
	}

	public void setPartiteInCasa(List<Partita> partiteInCasa) {
		this.partiteInCasa = partiteInCasa;
	}

	public List<Partita> getPartiteInTrasferta() {
		return partiteInTrasferta;
	}

	public void setPartiteInTrasferta(List<Partita> partiteInTrasferta) {
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
