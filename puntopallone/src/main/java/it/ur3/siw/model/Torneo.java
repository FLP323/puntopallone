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
public class Torneo {

	/* ATTRIBUTI */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;
	
	@NotNull
	@Min(1871) // Data del primo torneo di calcio in assoluto
	@NotFutureYear
	@Column(nullable = false)
	private Integer anno;
	
	private String descrizione;

	/* ASSOCIAZIONI */
	
	// Associazione con zero o più squadre
	@ManyToMany
	private Set<Squadra> squadrePartecipanti;
	
	// Associazione con zero o più partite
	@OneToMany(mappedBy = "torneoDiAppartenenza")
	private Set<Partita> partiteDelTorneo;
	
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

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Squadra> getSquadrePartecipanti() {
		return squadrePartecipanti;
	}

	public void setSquadrePartecipanti(Set<Squadra> squadrePartecipanti) {
		this.squadrePartecipanti = squadrePartecipanti;
	}

	public Set<Partita> getPartiteDelTorneo() {
		return partiteDelTorneo;
	}

	public void setPartiteDelTorneo(Set<Partita> partiteDelTorneo) {
		this.partiteDelTorneo = partiteDelTorneo;
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
		Torneo other = (Torneo) obj;
		return Objects.equals(id, other.id);
	}
}
