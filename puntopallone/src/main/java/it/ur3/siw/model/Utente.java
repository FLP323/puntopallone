package it.ur3.siw.model;

import it.ur3.siw.model.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Utente {

	/* ATTRIBUTI */
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
    @Column(nullable = false, unique = true)
	private String username;
    
	@NotBlank
    @Column(nullable = false)
    private String password;
    
	//@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 9)
	private UserRole role;
	
	/* ASSOCIAZIONI */
	
	/* GETTERS E SETTERS */
	
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
