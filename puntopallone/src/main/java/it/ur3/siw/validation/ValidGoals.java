package it.ur3.siw.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented 												// Fa si che l'annotazione compaia nei javadoc degli elementi annotati
@Constraint(validatedBy = ValidGoalsValidator.class) 	// Inserire la classe che effettua la verifica
@Target({ ElementType.TYPE }) 								// Specifica i target a cui può essere applicata
@Retention(RetentionPolicy.RUNTIME) 						// Quando effettuare la verifica
public @interface ValidGoals {

	String message() default "Data non corretta"; 			// Messaggio di errore default

	Class<?>[] groups() default {};							// Gruppi di validazione di appertenenza	

	Class<? extends Payload>[] payload() default {};		// Dettagli aggiuntivi per l'errore
}
