package br.com.fiap.monitoramentomottu.dto;

import br.com.fiap.monitoramentomottu.entity.Localizacao;
import br.com.fiap.monitoramentomottu.entity.Modelo;
import org.springframework.hateoas.Link;

import java.util.List;

public record MotoResponse (
        Long id,
        String placa,
        String modelo,
        String condicao,
        Localizacao localizacao,
        List<Link> links
) {
    public MotoResponse withLinks(List<Link> links) {
        return new MotoResponse(id, placa, modelo, condicao, localizacao, links);
    }
}
