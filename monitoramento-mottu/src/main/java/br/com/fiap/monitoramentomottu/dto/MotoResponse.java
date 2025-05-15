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
        Link link
) {

}
