package br.com.fiap.monitoramentomottu.mappers;

import br.com.fiap.monitoramentomottu.controller.rest.FilialRestController;
import br.com.fiap.monitoramentomottu.dto.request.FilialRequest;
import br.com.fiap.monitoramentomottu.dto.response.EnderecoResponse;
import br.com.fiap.monitoramentomottu.dto.response.FilialResponse;
import br.com.fiap.monitoramentomottu.dto.response.PatioResponse;
import br.com.fiap.monitoramentomottu.entity.Filial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FilialMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private PatioMapper patioMapper;

    public Filial RequestToFilial(FilialRequest dto) {
        Filial filial = new Filial();
        filial.setNome(dto.nome());
        filial.setCnpj(dto.cnpj());
        filial.setAno(dto.ano());

        return filial;
    }

    public FilialResponse FilialToResponse(Filial filial, boolean self) {
        Link link = null;
        EnderecoResponse enderecoResponse = filial.getEndereco() != null
                ? enderecoMapper.EnderecoToResponse(filial.getEndereco(), false)
                : null;
        try {
            link = self
                    ? linkTo(methodOn(FilialRestController.class).getById(filial.getId())).withSelfRel()
                    : linkTo(methodOn(FilialRestController.class).getById(0L)).withRel("Lista de Filiais");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<PatioResponse> patios = filial.getPatios() != null
                ? filial.getPatios().stream()
                .map(p -> {
                    try {
                        return patioMapper.PatioToResponse(p, false);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList())
                : List.of();

        return new FilialResponse(
                filial.getId(),
                filial.getNome(),
                filial.getCnpj(),
                filial.getAno(),
               enderecoResponse,
                patios,
                link
        );
    }
    public FilialResponse toSimpleResponse(Filial filial) {
        return new FilialResponse(
                filial.getId(),
                filial.getNome(),
                null,  // CNPJ não precisa
                filial.getAno(),  // Ano não precisa
                null,  // Endereço não precisa
                null,  // Patios não precisa
                null   // Link não precisa
        );
    }


}
