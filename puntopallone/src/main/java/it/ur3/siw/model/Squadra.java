package it.ur3.siw.model;

import it.ur3.siw.validation.NotFutureYear;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Min(1857) // Data di nascita della prima squadra di calcio
	@NotFutureYear
	@Column(nullable = false)
	private Integer dataDiFondazione;
	
	@NotBlank
	@Column(nullable = false)
	private String città;
	
	/* ASSOCIAZIONI */
	
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
}
