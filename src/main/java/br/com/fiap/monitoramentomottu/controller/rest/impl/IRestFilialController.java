package br.com.fiap.monitoramentomottu.controller.rest.impl;

import br.com.fiap.monitoramentomottu.dto.request.FilialRequest;
import br.com.fiap.monitoramentomottu.dto.response.FilialResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestFilialController {
    ResponseEntity<FilialResponse> create(FilialRequest dto) throws Exception;

    ResponseEntity<FilialResponse> getById( Long id) throws Exception;

    ResponseEntity<Page<FilialResponse>> getAll(Integer page);

    ResponseEntity<FilialResponse> update(Long id, FilialRequest dto) throws Exception;

    ResponseEntity<Void> delete(Long id) throws Exception;
}
