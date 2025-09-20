package br.com.fiap.monitoramentomottu.service.impl;
import br.com.fiap.monitoramentomottu.dto.request.LocalizacaoRequest;
import br.com.fiap.monitoramentomottu.dto.request.UwbRequest;
import br.com.fiap.monitoramentomottu.dto.response.UwbResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUwbService {

    UwbResponse create(UwbRequest dto) throws Exception;

    UwbResponse getById(Long id) throws Exception;

    Page<UwbResponse> getAll(Pageable pageable) throws Exception;

    UwbResponse update(Long id, UwbRequest dto) throws Exception;

    void delete(Long id) throws Exception;
    UwbResponse updateLocalizacao(Long id, LocalizacaoRequest dto) throws Exception;
}
