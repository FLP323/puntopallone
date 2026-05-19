package it.ur3.siw.validation;

import it.ur3.siw.model.Partita;
import it.ur3.siw.model.enums.Stato;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ValidMatchDateValidator implements ConstraintValidator<ValidMatchDate, Partita> {

	@Override
	public boolean isValid(Partita partita, ConstraintValidatorContext context) {
		
		Instant data = partita.getDataEOraInizioPartita();
		Stato stato = partita.getStato();
		
		// Anche se già coperto da @NotNull
		if (data == null || stato == null) return false;

		// Ottieni l'anno della data (in UTC) per il controllo > 1860 (prima partita di calcio mai disputata)
		LocalDateTime localDateTime = data.atZone(ZoneOffset.UTC).toLocalDateTime();
		int anno = localDateTime.getYear();
		
		// Ottieni la data corrente
		Instant now = Instant.now();

		if (stato == Stato.PLAYED) {
			return anno > 1860 && data.isBefore(now);
		} else if (stato == Stato.SCHEDULED) {
			return data.isAfter(now);
		}

		return true;
	}
}