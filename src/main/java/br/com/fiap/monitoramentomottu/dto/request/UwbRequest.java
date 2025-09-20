package br.com.fiap.monitoramentomottu.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UwbRequest(

        @NotBlank(message = "O código UWB não pode estar em branco")
        String codigo,

        @NotBlank(message = "O status não pode estar em branco")
        String status,

        Long idMoto

) {}
