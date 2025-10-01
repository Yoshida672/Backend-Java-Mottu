package br.com.fiap.monitoramentomottu.dto.response;

import org.springframework.hateoas.Link;

public record MotoResponse (
        Long id,
        String placa,
        String modelo,
        String condicao,
        Long condicaoId,
        Long patioId,
        String filial,
        Link link
) {

}
