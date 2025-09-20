package br.com.fiap.monitoramentomottu.service.impl;
import br.com.fiap.monitoramentomottu.dto.request.CondicaoRequest;
import br.com.fiap.monitoramentomottu.dto.response.CondicaoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICondicaoService {

    CondicaoResponse create(CondicaoRequest dto) throws Exception;

    CondicaoResponse getById(Long id) throws Exception;

    Page<CondicaoResponse> getAll(Pageable pageable) throws Exception;

    CondicaoResponse update(Long id, CondicaoRequest dto) throws Exception;

    void delete(Long id) throws Exception;
}
