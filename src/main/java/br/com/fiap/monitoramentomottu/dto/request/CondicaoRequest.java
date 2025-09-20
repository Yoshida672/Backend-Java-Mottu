package br.com.fiap.monitoramentomottu.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CondicaoRequest(
        Long id,
        @NotBlank(message = "O nome da condição é obrigatório")
        String nome,

        @NotBlank(message = "A cor da condição é obrigatória")
        String cor
) {
}
