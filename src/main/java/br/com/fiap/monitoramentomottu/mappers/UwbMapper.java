package br.com.fiap.monitoramentomottu.mappers;

import br.com.fiap.monitoramentomottu.controller.rest.UwbRestController;
import br.com.fiap.monitoramentomottu.dto.request.UwbRequest;
import br.com.fiap.monitoramentomottu.dto.response.UwbResponse;
import br.com.fiap.monitoramentomottu.dto.response.LocalizacaoResponse;
import br.com.fiap.monitoramentomottu.entity.Moto;
import br.com.fiap.monitoramentomottu.entity.Uwb;
import br.com.fiap.monitoramentomottu.entity.Localizacao;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UwbMapper {
    private final LocalizacaoMapper localizacaoMapper;

    public UwbMapper(LocalizacaoMapper localizacaoMapper) {
        this.localizacaoMapper = localizacaoMapper;
    }
    public Uwb RequestToUwb(UwbRequest dto) {
        Uwb uwb = new Uwb();
        uwb.setCodigo(dto.codigo());
        uwb.setStatus(dto.status());

        if (dto.idMoto() != null) {
            Moto moto = new Moto();
            moto.setId(dto.idMoto());
            uwb.setMoto(moto);
        }

        return uwb;
    }

    public UwbResponse UwbToResponse(Uwb uwb, boolean self) throws Exception {
        Link link = self
                ? linkTo(methodOn(UwbRestController.class).getById(uwb.getId())).withSelfRel()
                : linkTo(methodOn(UwbRestController.class).getById(0L)).withRel("Lista de UWBs");

        String motoPlaca = (uwb.getMoto() != null) ? uwb.getMoto().getPlaca() : null;

        LocalizacaoResponse localizacao = null;
        if (uwb.getLocalizacao() != null) {
            Localizacao loc = uwb.getLocalizacao();
            localizacao =  LocalizacaoResponse.from(
                    loc.getId(),
                    LocalDateTime.now().withNano(0),
                    loc.getxCoord(),
                    loc.getyCoord()
            );
        }

        return new UwbResponse(
                uwb.getId(),
                uwb.getCodigo(),
                uwb.getStatus(),
                motoPlaca,
                localizacao,
                link
        );
    }
}
