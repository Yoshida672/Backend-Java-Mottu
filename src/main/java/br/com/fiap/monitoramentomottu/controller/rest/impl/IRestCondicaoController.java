package br.com.fiap.monitoramentomottu.controller.rest.impl;

import br.com.fiap.monitoramentomottu.dto.request.CondicaoRequest;
import br.com.fiap.monitoramentomottu.dto.response.CondicaoResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestCondicaoController {
    ResponseEntity<CondicaoResponse> create(CondicaoRequest dto) throws Exception;

    ResponseEntity<CondicaoResponse> getById( Long id) throws Exception;

    ResponseEntity<Page<CondicaoResponse>> getAll(Integer page);

    ResponseEntity<CondicaoResponse> update(Long id, CondicaoRequest dto) throws Exception;

    ResponseEntity<Void> delete(Long id) throws Exception;
}
