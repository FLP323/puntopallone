package it.ur3.siw.model.enums;

public enum Stato {
	SCHEDULED("Da svolgere"), 	// Partita da giocare
	PLAYED("Giocata");			// Partita già giocata
	
	private final String stato;
	
	Stato (String string){
		this.stato = string;
	}

	public String getStato() {
		return stato;
	}
}
