package it.ur3.siw.validation;

import it.ur3.siw.model.Partita;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SquadreDiverseValidator implements ConstraintValidator<ValidSquadreDiverse, Partita> {
    @Override
    public boolean isValid(Partita partita, ConstraintValidatorContext context) {
        if (partita == null || partita.getSquadraInCasa() == null || partita.getSquadraInTrasferta() == null) {
            return true;
        }
        return !partita.getSquadraInCasa().equals(partita.getSquadraInTrasferta());
    }
}
