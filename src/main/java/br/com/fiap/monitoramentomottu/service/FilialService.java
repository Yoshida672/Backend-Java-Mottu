package br.com.fiap.monitoramentomottu.service;

import br.com.fiap.monitoramentomottu.dto.request.EnderecoRequest;
import br.com.fiap.monitoramentomottu.dto.request.FilialRequest;
import br.com.fiap.monitoramentomottu.dto.response.FilialResponse;
import br.com.fiap.monitoramentomottu.entity.Endereco;
import br.com.fiap.monitoramentomottu.entity.Filial;
import br.com.fiap.monitoramentomottu.mappers.EnderecoMapper;
import br.com.fiap.monitoramentomottu.mappers.FilialMapper;
import br.com.fiap.monitoramentomottu.repository.EnderecoRepository;
import br.com.fiap.monitoramentomottu.repository.FilialRepository;
import br.com.fiap.monitoramentomottu.service.impl.IFilialService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilialService implements IFilialService {

    private final FilialRepository filialRepository;
    private final FilialMapper filialMapper;
    private final EnderecoMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;


    public FilialService(FilialRepository filialRepository, FilialMapper filialMapper, EnderecoMapper enderecoMapper, EnderecoRepository enderecoRepository) {
        this.filialRepository = filialRepository;
        this.filialMapper = filialMapper;
        this.enderecoMapper = enderecoMapper;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public FilialResponse create(FilialRequest dto) throws Exception {
        Filial filial = filialMapper.RequestToFilial(dto);
        filial = filialRepository.save(filial);
        return filialMapper.FilialToResponse(filial, true);
    }

    public FilialResponse getById(Long id) throws Exception {
        System.out.println("Buscando filial com ID: " + id);
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new Exception("Filial não encontrada"));
        return filialMapper.FilialToResponse(filial, true);
    }

    @Transactional(readOnly = true)
    public Page<FilialResponse> getAll(Pageable pageable) {
        return filialRepository.findAll(pageable)
                .map(filial -> filialMapper.FilialToResponse(filial, true));
    }
    @Transactional(readOnly = true)
    public List<FilialResponse> getAllFiliais() {
        return filialRepository.findAll()
                .stream()
                .map(filialMapper::toSimpleResponse)
                .collect(Collectors.toList());
    }

    @Transactional

    public FilialResponse update(Long id, FilialRequest dto) throws Exception {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new Exception("Filial não encontrada"));

        if (dto.nome() != null) filial.setNome(dto.nome());
        if (dto.ano() != 0) filial.setAno(dto.ano());
        if (dto.cnpj() != null) filial.setCnpj(dto.cnpj());
        filial = filialRepository.save(filial);
        return filialMapper.FilialToResponse(filial, true);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        if (!filialRepository.existsById(id)) {
            throw new Exception("Filial não encontrada");
        }
        filialRepository.deleteById(id);
    }

    public void addEndereco(Long filialId, EnderecoRequest enderecoRequest) throws Exception {
        Filial filial = filialRepository.findById(filialId)
                .orElseThrow(() -> new Exception("Filial não encontrada"));

        Endereco endereco = enderecoMapper.RequestToEndereco(enderecoRequest);
        enderecoRepository.save(endereco);

        filial.setEndereco(endereco);
        filialRepository.save(filial);
    }

}
