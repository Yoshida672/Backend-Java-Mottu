package br.com.fiap.monitoramentomottu.service;

import br.com.fiap.monitoramentomottu.dto.request.MotoRequest;
import br.com.fiap.monitoramentomottu.dto.response.MotoResponse;
import br.com.fiap.monitoramentomottu.entity.*;
import br.com.fiap.monitoramentomottu.mappers.MotoMapper;
import br.com.fiap.monitoramentomottu.repository.*;
import br.com.fiap.monitoramentomottu.service.impl.IMotoService;
import br.com.fiap.monitoramentomottu.service.processor.BuscaMotosProcessorFactory;
import br.com.fiap.monitoramentomottu.service.processor.search.TiposBuscaMoto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MotoService implements IMotoService {

    private final MotoRepository motoRepository;
    private final CondicaoRepository condicaoRepository;
    private final PatioRepository patioRepository;
    private final MotoMapper mapper;
    private final BuscaMotosProcessorFactory processorFactory;

    public MotoService(MotoRepository motoRepository, CondicaoRepository condicaoRepository, PatioRepository patioRepository, MotoMapper mapper, BuscaMotosProcessorFactory processorFactory) {
        this.motoRepository = motoRepository;
        this.condicaoRepository = condicaoRepository;
        this.patioRepository = patioRepository;
        this.mapper = mapper;
        this.processorFactory = processorFactory;
    }

    @Transactional
    @CachePut(value = "motos", key = "#result.id")
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
        Patio patio = patioRepository.findById(dto.patioId()).orElseThrow(()->new Exception("Patio não encontrado"));
        Moto moto = mapper.RequestToMoto(dto);
        moto.setCondicao(condicao);
        moto.setPatio(patio);
        moto = motoRepository.save(moto);
        return mapper.MotoToResponse(moto,true);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "motos", key = "#id")
    public MotoResponse getById(Long id) throws Exception {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new Exception("Moto não encontrada"));
        return mapper.MotoToResponse(moto,true);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "motos",key = "'all'")
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
    @CachePut(value = "motos", key = "#id")
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

        if(dto.patioId()!=null){
            Patio patio  = patioRepository.findById(dto.patioId())
                    .orElseThrow(()->new Exception("Patio nao encontrado"));
            moto.setPatio(patio);
        }

        moto = motoRepository.save(moto);
        return mapper.MotoToResponse(moto,true);
    }

    @Transactional
    @CacheEvict(value = "motos", key = "#id")

    public void delete(Long id) throws Exception {
        if (!motoRepository.existsById(id)) {
            throw new Exception("Moto não encontrada");
        }
        motoRepository.deleteById(id);
    }
    @CacheEvict(value = "motos", key = "'all'")
    public void cleanMotosListCache() {
    }

    @CacheEvict(value = "motos", allEntries = true)
    public void cleanAllMotosCache() {
    }
    public List<MotoResponse> buscarMotos(TiposBuscaMoto tiposBuscaMoto, String modelo, String condicao) {

        List<Moto> resultado = processorFactory.getProcessor(tiposBuscaMoto).search(modelo,condicao);

        return resultado.stream()
                .map(moto -> {
                    try {
                        return mapper.MotoToResponse(moto, true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }


}