package br.com.fiap.monitoramentomottu.dto.Patio;

import jakarta.validation.constraints.Positive;

import java.util.List;

public record PatioRequest(
        int qtdMoto,
        int area,
        int capacidadeMax,
        @Positive Long filialId,
        @Positive List<Long> motosId
        ) {
}
