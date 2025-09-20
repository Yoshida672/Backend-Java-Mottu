package br.com.fiap.monitoramentomottu.controller.rest.impl;

import br.com.fiap.monitoramentomottu.dto.request.PatioRequest;
import br.com.fiap.monitoramentomottu.dto.response.PatioResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestPatioController {
    ResponseEntity<PatioResponse> create(PatioRequest dto) throws Exception;

    ResponseEntity<PatioResponse> getById( Long id) throws Exception;

    ResponseEntity<Page<PatioResponse>> getAll(Integer page);

    ResponseEntity<PatioResponse> update(Long id, PatioRequest dto) throws Exception;

    ResponseEntity<Void> delete(Long id) throws Exception;
}
