package br.com.fiap.monitoramentomottu.service;

import br.com.fiap.monitoramentomottu.dto.request.CondicaoRequest;
import br.com.fiap.monitoramentomottu.dto.response.CondicaoResponse;
import br.com.fiap.monitoramentomottu.entity.Condicao;
import br.com.fiap.monitoramentomottu.mappers.CondicaoMapper;
import br.com.fiap.monitoramentomottu.repository.CondicaoRepository;
import br.com.fiap.monitoramentomottu.service.impl.ICondicaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CondicaoService implements ICondicaoService {
    private final CondicaoRepository condicaoRepository;
    private final CondicaoMapper mapper;
    public CondicaoService(CondicaoRepository condicaoRepository, CondicaoMapper mapper) {
        this.condicaoRepository = condicaoRepository;
        this.mapper = mapper;
    }

    @Transactional

    public CondicaoResponse create(CondicaoRequest dto) throws Exception{
        Condicao condicao = mapper.RequestToCondicao(dto);
        condicao.setCor(dto.cor());
        condicao.setNome(dto.nome());
        condicao = condicaoRepository.save(condicao);

        return mapper.CondicaoToResponse(condicao,true);

    }

    @Transactional(readOnly = true)
    public CondicaoResponse getById(Long id) throws Exception {
        Condicao condicao = condicaoRepository.findById(id)
                .orElseThrow(() -> new Exception("Condição não encontrada"));
        return mapper.CondicaoToResponse(condicao, true);
    }

    @Transactional(readOnly = true)
    public Page<CondicaoResponse> getAll(Pageable pageable) {
        return condicaoRepository.findAll(pageable)
                .map(condicao -> {
                    try {
                        return mapper.CondicaoToResponse(condicao, true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }


    @Transactional
    public CondicaoResponse update(Long id, CondicaoRequest dto) throws Exception {
        Condicao condicao = condicaoRepository.findById(id)
                .orElseThrow(() -> new Exception("Condição não encontrada"));

        if (dto.cor() != null) {
            condicao.setCor(dto.cor());
        }

        if (dto.nome() != null) {
            condicao.setNome(dto.nome());
        }

        condicao = condicaoRepository.save(condicao);

        return mapper.CondicaoToResponse(condicao, true);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        if (!condicaoRepository.existsById(id)) {
            throw new Exception("Condição não encontrada");
        }

        condicaoRepository.deleteById(id);
    }




}
