package br.com.fiap.monitoramentomottu.service;

import br.com.fiap.monitoramentomottu.dto.MotoRequest;
import br.com.fiap.monitoramentomottu.dto.MotoResponse;
import br.com.fiap.monitoramentomottu.entity.*;
import br.com.fiap.monitoramentomottu.mappers.MotoMapper;
import br.com.fiap.monitoramentomottu.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;
    private final CondicaoRepository condicaoRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final MotoMapper mapper;

    public MotoService(MotoRepository motoRepository, CondicaoRepository condicaoRepository, LocalizacaoRepository localizacaoRepository, MotoMapper mapper) {
        this.motoRepository = motoRepository;
        this.condicaoRepository = condicaoRepository;
        this.localizacaoRepository = localizacaoRepository;
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
        Localizacao localizacao = null;
        if (dto.localizacaoId() != null) {
            localizacao = localizacaoRepository.findById(dto.localizacaoId())
                    .orElseThrow(() -> new Exception("Localização não encontrada"));
        }
        Moto moto = mapper.RequestToMoto(dto);
        moto.setCondicao(condicao);
        moto.setLocalizacao(localizacao);
        moto = motoRepository.save(moto);
        return mapper.MotoToResponse(moto);
    }

    @Transactional(readOnly = true)
    public MotoResponse getById(Long id) throws Exception {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new Exception("Moto não encontrada"));
        return mapper.MotoToResponse(moto);
    }

    @Transactional(readOnly = true)
    public List<MotoResponse> getAll() {
        return motoRepository.findAll().stream()
                .map(mapper::MotoToResponse)
                .toList();
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

        if (dto.localizacaoId() != null) {
            Localizacao localizacao = localizacaoRepository.findById(dto.localizacaoId())
                    .orElseThrow(() -> new Exception("Localização não encontrada"));
            moto.setLocalizacao(localizacao);
        }

        moto = motoRepository.save(moto);
        return mapper.MotoToResponse(moto);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        if (!motoRepository.existsById(id)) {
            throw new Exception("Moto não encontrada");
        }
        motoRepository.deleteById(id);
    }
}