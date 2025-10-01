package br.com.fiap.monitoramentomottu.exception;

import br.com.fiap.monitoramentomottu.dto.anotacao.AnoAtualOuInferior;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class AnoAtualValidator implements ConstraintValidator<AnoAtualOuInferior, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value <= Year.now().getValue();
    }
}
