package br.com.fiap.monitoramentomottu.service;

import br.com.fiap.monitoramentomottu.dto.MotoRequest;
import br.com.fiap.monitoramentomottu.dto.MotoResponse;
import br.com.fiap.monitoramentomottu.entity.*;
import br.com.fiap.monitoramentomottu.mappers.MotoMapper;
import br.com.fiap.monitoramentomottu.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;
    private final CondicaoRepository condicaoRepository;
    private final MotoMapper mapper;

    public MotoService(MotoRepository motoRepository, CondicaoRepository condicaoRepository, MotoMapper mapper) {
        this.motoRepository = motoRepository;
        this.condicaoRepository = condicaoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public MotoResponse create(MotoRequest dto) throws Exception {
        if (motoRepository.existsByPlaca(dto.placa())) {
            throw new Exception("Placa já cadastrada!");
        }

        try {
            Modelo.valueOf(dto.modelo());
        } catch (IllegalArgumentException e) {
            throw new Exception("Modelo inválido: " + dto.modelo());
        }
        Condicao condicao = condicaoRepository.findById(dto.condicaoId())
                .orElseThrow(() -> new Exception("Condição não encontrada"));

        Moto moto = mapper.RequestToMoto(dto);
        moto.setCondicao(condicao);
        moto = motoRepository.save(moto);
        return mapper.MotoToResponse(moto,true);
    }

    @Transactional(readOnly = true)
    public MotoResponse getById(Long id) throws Exception {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new Exception("Moto não encontrada"));
        return mapper.MotoToResponse(moto,true);
    }

    @Transactional(readOnly = true)
    public Page<MotoResponse> getAll(Pageable pageable) {
        return motoRepository.findAll(pageable)
                .map(moto-> {
                    try {
                        return mapper.MotoToResponse(moto,true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Transactional
    public MotoResponse update(Long id, MotoRequest dto) throws Exception {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new Exception("Moto não encontrada"));

        if (dto.placa() != null && !dto.placa().equals(moto.getPlaca())) {
            if (motoRepository.existsByPlaca(dto.placa())) {
                throw new Exception("Nova placa já está em uso!");
            }
            moto.setPlaca(dto.placa());
        }

        if (dto.modelo() != null) {
            try {
                moto.setModelo(Modelo.valueOf(dto.modelo()));
            } catch (IllegalArgumentException e) {
                throw new Exception("Modelo inválido: " + dto.modelo());
            }
        }

        if (dto.condicaoId() != null) {
            Condicao condicao = condicaoRepository.findById(dto.condicaoId())
                    .orElseThrow(() -> new Exception("Condição não encontrada"));
            moto.setCondicao(condicao);
        }

        moto = motoRepository.save(moto);
        return mapper.MotoToResponse(moto,true);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        if (!motoRepository.existsById(id)) {
            throw new Exception("Moto não encontrada");
        }
        motoRepository.deleteById(id);
    }
}