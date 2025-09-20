package br.com.fiap.monitoramentomottu.controller.rest.impl;

import br.com.fiap.monitoramentomottu.dto.request.UwbRequest;
import br.com.fiap.monitoramentomottu.dto.response.UwbResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestUwbController {
    ResponseEntity<UwbResponse> create(UwbRequest dto) throws Exception;

    ResponseEntity<UwbResponse> getById( Long id) throws Exception;

    ResponseEntity<Page<UwbResponse>> getAll(Integer page);

    ResponseEntity<UwbResponse> update(Long id, UwbRequest dto) throws Exception;

    ResponseEntity<Void> delete(Long id) throws Exception;
}
