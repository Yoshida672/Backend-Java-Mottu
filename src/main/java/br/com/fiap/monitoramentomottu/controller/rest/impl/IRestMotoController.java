package br.com.fiap.monitoramentomottu.controller.rest.impl;


import br.com.fiap.monitoramentomottu.dto.request.MotoRequest;
import br.com.fiap.monitoramentomottu.dto.response.MotoResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestMotoController {

    ResponseEntity<MotoResponse> create(MotoRequest dto) throws Exception;

    ResponseEntity<MotoResponse> getById( Long Long) throws Exception;

    ResponseEntity<Page<MotoResponse>> getAll( Integer page);

    ResponseEntity<MotoResponse> update(Long Long, MotoRequest dto) throws Exception;

    ResponseEntity<Void> delete(Long Long) throws Exception;
}
