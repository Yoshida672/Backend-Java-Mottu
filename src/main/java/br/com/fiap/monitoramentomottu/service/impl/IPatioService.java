package br.com.fiap.monitoramentomottu.service.impl;
import br.com.fiap.monitoramentomottu.dto.request.PatioRequest;
import br.com.fiap.monitoramentomottu.dto.response.PatioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPatioService {

    PatioResponse create(PatioRequest dto) throws Exception;

    PatioResponse getById(Long id) throws Exception;

    Page<PatioResponse> getAll(Pageable pageable) throws Exception;

    PatioResponse update(Long id, PatioRequest dto) throws Exception;

    void delete(Long id) throws Exception;
}
