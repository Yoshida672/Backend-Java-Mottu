package br.com.fiap.monitoramentomottu.controller.Interface;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestController<TRequest, TResponse, ID> {

    ResponseEntity<TResponse> create(TRequest dto) throws Exception;

    ResponseEntity<TResponse> getById( ID id) throws Exception;

    ResponseEntity<Page<TResponse>> getAll( Integer page);

    ResponseEntity<TResponse> update(ID id, TRequest dto) throws Exception;

    ResponseEntity<Void> delete(ID id) throws Exception;}
