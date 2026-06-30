package it.ur3.siw.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Arbitro {
	
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
	
	@NotBlank
	@Column(nullable = false)
	private String codiceArbitrale;
	
	/* ASSOCIAZIONI */
	
	// Associazione con zero o più partite
	@JsonIgnore
	@OneToMany(mappedBy = "arbitroInCarica")
	private List<Partita> partiteDoveArbitra;
	
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

	public String getCodiceArbitrale() {
		return codiceArbitrale;
	}

	public void setCodiceArbitrale(String codiceArbitrale) {
		this.codiceArbitrale = codiceArbitrale;
	}

	public List<Partita> getPartiteDoveArbitra() {
		return partiteDoveArbitra;
	}

	public void setPartiteDoveArbitra(List<Partita> partiteDoveArbitra) {
		this.partiteDoveArbitra = partiteDoveArbitra;
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
		Arbitro other = (Arbitro) obj;
		return Objects.equals(id, other.id);
	}

}
