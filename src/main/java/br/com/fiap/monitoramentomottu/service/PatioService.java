package br.com.fiap.monitoramentomottu.service;

import br.com.fiap.monitoramentomottu.dto.request.PatioRequest;
import br.com.fiap.monitoramentomottu.dto.response.FilialResponse;
import br.com.fiap.monitoramentomottu.dto.response.PatioResponse;
import br.com.fiap.monitoramentomottu.entity.*;
import br.com.fiap.monitoramentomottu.mappers.PatioMapper;
import br.com.fiap.monitoramentomottu.repository.FilialRepository;
import br.com.fiap.monitoramentomottu.repository.MotoRepository;
import br.com.fiap.monitoramentomottu.repository.PatioRepository;
import br.com.fiap.monitoramentomottu.service.impl.IPatioService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatioService implements IPatioService {
    private final MotoRepository motoRepository;
    private final PatioRepository patioRepository;
    private final FilialRepository filialRepository;

    private final PatioMapper mapper;

    public PatioService(MotoRepository motoRepository, PatioRepository patioRepository, FilialRepository filialRepository, PatioMapper mapper) {
        this.motoRepository = motoRepository;
        this.patioRepository = patioRepository;
        this.filialRepository = filialRepository;
        this.mapper = mapper;
    }

    @Transactional
    public PatioResponse create(PatioRequest dto) throws Exception{
        Patio patio = mapper.RequestToPatio(dto);
        patio.setCapacidadeMoto(dto.capacidadeMax());
        patio.setAreaPatio( dto.area());
        patio.setFilial(filialRepository.findById(dto.filialId()).orElseThrow(() -> new Exception("Filial não encontrada")));
        patio = patioRepository.save(patio);
        return mapper.PatioToResponse(patio,true);
    }
    @Transactional(readOnly = true)
    public PatioResponse getById(Long id) throws Exception {
        System.out.println("Buscando Patio com ID: " + id);
        Patio patio  = patioRepository.findById(id)
                .orElseThrow(() -> new Exception("Patio não encontrada"));
        return mapper.PatioToResponse(patio,true);
    }

    @Transactional(readOnly = true)
    public Page<PatioResponse> getAll(Pageable pageable) {
        return patioRepository.findAll(pageable)
                .map(patio-> {
                    try {
                        return mapper.PatioToResponse(patio,true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    @Transactional(readOnly = true)
    public List<FilialResponse> getAllFiliais() {
        return filialRepository.findAll()
                .stream()
                .map(f -> new FilialResponse(f.getId(), f.getNome(), f.getCnpj(), f.getAno(), null, null, null))
                .collect(Collectors.toList());
    }

    @Transactional
    public PatioResponse update(Long id, PatioRequest dto) throws Exception {
        System.out.println("Buscando Patio com ID: " + id);

        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new Exception("Patio não encontrado"));

        if (dto.filialId() != null) {
            System.out.println("Buscando Filial com ID: " + dto.filialId());
            Filial filial = filialRepository.findById(dto.filialId())
                    .orElseThrow(() -> new Exception("Filial não encontrada"));
            patio.setFilial(filial);
        }
        patio.setAreaPatio(dto.area());
        patio.setCapacidadeMoto(dto.capacidadeMax());
        patio = patioRepository.save(patio);
        return mapper.PatioToResponse(patio,true);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        if (!patioRepository.existsById(id)) {
            throw new Exception("Moto não encontrada");
        }
        patioRepository.deleteById(id);
    }

}
