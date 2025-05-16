package br.com.fiap.monitoramentomottu.dto.Moto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MotoRequest(
        @NotBlank String placa,
        @NotBlank String modelo,
        @Positive Long condicaoId,
        @Positive Long patioId
) {}
