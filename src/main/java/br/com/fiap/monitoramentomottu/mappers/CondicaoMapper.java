package br.com.fiap.monitoramentomottu.mappers;

import br.com.fiap.monitoramentomottu.controller.rest.CondicaoRestController;

import br.com.fiap.monitoramentomottu.dto.request.CondicaoRequest;
import br.com.fiap.monitoramentomottu.dto.response.CondicaoResponse;

import br.com.fiap.monitoramentomottu.entity.Condicao;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class CondicaoMapper {
    public Condicao RequestToCondicao(CondicaoRequest dto) {
       Condicao condicao = new Condicao();
        condicao.setNome(dto.nome());
        condicao.setCor(dto.cor());
        return condicao;
    }

    public CondicaoResponse CondicaoToResponse(Condicao condicao, boolean self) throws Exception {
        Link link;
        if (self) {
            link = linkTo(
                    methodOn(
                            CondicaoRestController.class
                    ).getById(condicao.getId())
            ).withSelfRel();
        } else {
            link = linkTo(
                    methodOn(
                            CondicaoRestController.class
                    ).getById(0L)
            ).withRel("Lista de Condições");
        }
        return new CondicaoResponse(
                condicao.getId(),
                condicao.getNome(),
                condicao.getCor(),
                link

        );
    }
}
