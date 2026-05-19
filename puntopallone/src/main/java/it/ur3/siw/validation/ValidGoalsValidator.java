package it.ur3.siw.validation;

import it.ur3.siw.model.Partita;
import it.ur3.siw.model.enums.Stato;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidGoalsValidator implements ConstraintValidator<ValidGoals, Partita> {

	@Override
	public boolean isValid(Partita partita, ConstraintValidatorContext context) {
		
        Stato stato = partita.getStato();
        Integer goalsHome = partita.getGoalsHome();
        Integer goalsAway = partita.getGoalsAway();

        if (stato == Stato.PLAYED) {
            return goalsHome != null && goalsAway != null && goalsHome >= 0 && goalsAway >= 0;
        } else if (stato == Stato.SCHEDULED) {
            return goalsHome == null && goalsAway == null;
        }
        
        return true;
	}
}