package br.com.fiap.monitoramentomottu.dto.anotacao;

import br.com.fiap.monitoramentomottu.exception.AnoAtualValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AnoAtualValidator.class)
public @interface AnoAtualOuInferior {
    String message() default "Ano não pode ser maior que o atual";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
