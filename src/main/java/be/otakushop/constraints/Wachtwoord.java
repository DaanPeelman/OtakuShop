package be.otakushop.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WachtwoordValidator.class)
public @interface Wachtwoord {
	String message() default "{be.otakushop.constraints.Wachtwoord}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
