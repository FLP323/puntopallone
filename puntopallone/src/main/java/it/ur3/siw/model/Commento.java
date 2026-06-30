package it.ur3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Commento {

	/* ATTRIBUTI */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String corpo;

	@NotNull
	@Column(nullable = false)
	private Instant dataDelCommento;

	@NotNull
	@Min(0)
	@Column(nullable = false)
	private Integer miPiace;

	@NotNull
	@Min(0)
	@Column(nullable = false)
	private Integer nonMiPiace;

	/* ASSOCIAZIONI */

	// Associazione con esattamente un utente
	@JsonIgnore
	@NotNull
	@ManyToOne
	private Utente commentatore;

	// Associazione con esattamente una partita
	@NotNull
	@ManyToOne
	@JsonIgnore
	private Partita partitaCommentata;

	/* GETTERS E SETTERS */

	public Long getId() {
		return id;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public Instant getDataDelCommento() {
		return dataDelCommento;
	}

	public void setDataDelCommento(Instant dataDelCommento) {
		this.dataDelCommento = dataDelCommento;
	}

	public Integer getMiPiace() {
		return miPiace;
	}

	public void setMiPiace(Integer miPiace) {
		this.miPiace = miPiace;
	}

	public Integer getNonMiPiace() {
		return nonMiPiace;
	}

	public void setNonMiPiace(Integer nonMiPiace) {
		this.nonMiPiace = nonMiPiace;
	}

	public Utente getCommentatore() {
		return commentatore;
	}

	public void setCommentatore(Utente commentatore) {
		this.commentatore = commentatore;
	}

	public Partita getPartitaCommentata() {
		return partitaCommentata;
	}

	public void setPartitaCommentata(Partita partitaCommentata) {
		this.partitaCommentata = partitaCommentata;
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
		if (obj == null || getClass() != obj.getClass())
			return false;
		Commento other = (Commento) obj;
		return Objects.equals(id, other.id);
	}
}