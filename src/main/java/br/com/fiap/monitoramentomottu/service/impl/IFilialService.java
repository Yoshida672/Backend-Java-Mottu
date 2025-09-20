package br.com.fiap.monitoramentomottu.service.impl;
import br.com.fiap.monitoramentomottu.dto.request.FilialRequest;
import br.com.fiap.monitoramentomottu.dto.response.FilialResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFilialService {

    FilialResponse create(FilialRequest dto) throws Exception;

    FilialResponse getById(Long id) throws Exception;

    Page<FilialResponse> getAll(Pageable pageable) throws Exception;

    FilialResponse update(Long id, FilialRequest dto) throws Exception;

    void delete(Long id) throws Exception;
}
