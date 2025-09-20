package br.com.fiap.monitoramentomottu.dto.request;


public record LocalizacaoRequest(
        String timestamp,
        Double xCoord,
        Double yCoord
) {
}
