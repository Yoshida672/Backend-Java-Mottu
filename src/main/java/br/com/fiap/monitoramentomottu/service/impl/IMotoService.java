package br.com.fiap.monitoramentomottu.service.impl;
import br.com.fiap.monitoramentomottu.dto.request.MotoRequest;
import br.com.fiap.monitoramentomottu.dto.response.MotoResponse;
import br.com.fiap.monitoramentomottu.service.processor.search.TiposBuscaMoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMotoService {

    MotoResponse create(MotoRequest dto) throws Exception;

    MotoResponse getById(Long id) throws Exception;

    Page<MotoResponse> getAll(Pageable pageable) throws Exception;

    MotoResponse update(Long id, MotoRequest dto) throws Exception;

    void delete(Long id) throws Exception;
    List<MotoResponse> buscarMotos(TiposBuscaMoto tiposBuscaMoto, String modelo, String condicao);
}
