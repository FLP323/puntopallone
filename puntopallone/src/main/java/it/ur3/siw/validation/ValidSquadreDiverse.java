package it.ur3.siw.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SquadreDiverseValidator.class)
public @interface ValidSquadreDiverse {
    String message() default "La squadra di casa e quella ospite devono essere diverse";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
