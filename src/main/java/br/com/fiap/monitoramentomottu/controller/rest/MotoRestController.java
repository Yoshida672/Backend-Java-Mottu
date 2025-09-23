package br.com.fiap.monitoramentomottu.controller.rest;

import br.com.fiap.monitoramentomottu.controller.rest.impl.IRestMotoController;
import br.com.fiap.monitoramentomottu.dto.request.MotoRequest;
import br.com.fiap.monitoramentomottu.dto.response.MotoResponse;
import br.com.fiap.monitoramentomottu.service.MotoService;
import br.com.fiap.monitoramentomottu.service.processor.search.TiposBuscaMoto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoRestController implements IRestMotoController {

    private final MotoService service;

    public MotoRestController(MotoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MotoResponse> create(@RequestBody @Valid MotoRequest dto) throws Exception {
        MotoResponse response = service.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponse> getById(@PathVariable Long id) throws Exception {
        MotoResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<MotoResponse>> getAll(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 5,Sort.by("placa"));
        return new ResponseEntity<>(service.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid MotoRequest dto) throws Exception {
        MotoResponse response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<MotoResponse>> buscarMotos(
            @RequestParam(required = true) TiposBuscaMoto tipoBusca,
            @RequestParam(required = false)  String modelo,
            @RequestParam(required = false) String condicao) {

        List<MotoResponse> resposta = service.buscarMotos(tipoBusca,modelo, condicao);
        return ResponseEntity.ok(resposta);
    }


}