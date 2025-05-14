package br.com.fiap.monitoramentomottu.mappers;

import br.com.fiap.monitoramentomottu.dto.MotoRequest;
import br.com.fiap.monitoramentomottu.dto.MotoResponse;
import br.com.fiap.monitoramentomottu.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MotoMapper {

    public Moto RequestToMoto(MotoRequest dto) {
        Moto moto = new Moto();
        moto.setPlaca(dto.placa());
        moto.setModelo(Modelo.valueOf(dto.modelo()));
        return moto;
    }

    public MotoResponse MotoToResponse(Moto moto) {
        return new MotoResponse(
                moto.getId(),
                moto.getPlaca(),
                moto.getModelo().name(),
                moto.getCondicao().getNome(),
                moto.getLocalizacao(),
                new ArrayList<>()
        );
    }
}