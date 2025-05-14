package br.com.fiap.monitoramentomottu.dto;

import br.com.fiap.monitoramentomottu.entity.Modelo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MotoRequest(
        @NotBlank String placa,
        @NotNull String modelo,
        @NotNull Long condicaoId,
        Long localizacaoId
) {}
