package br.com.fiap.monitoramentomottu.dto.response;

import org.springframework.hateoas.Link;

public record UwbResponse(
        Long id,
        String codigo,
        String status,
        String moto,
        LocalizacaoResponse localizacao,
        Link link

) {}
