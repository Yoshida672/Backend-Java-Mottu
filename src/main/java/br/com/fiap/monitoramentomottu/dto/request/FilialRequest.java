package br.com.fiap.monitoramentomottu.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

public record FilialRequest(
        Long id,

        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "O CNPJ não pode estar em branco")
        @Size(min = 14, max = 18, message = "O CNPJ deve ter entre 14 e 18 caracteres")
        String cnpj,

        @NotNull(message = "O ano não pode ser nulo")
        @Positive
        @Min(1500)
        Integer ano) {


}
