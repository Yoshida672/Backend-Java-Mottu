package br.com.fiap.monitoramentomottu.controller.rest;


import br.com.fiap.monitoramentomottu.controller.rest.impl.IRestFilialController;
import br.com.fiap.monitoramentomottu.dto.request.FilialRequest;
import br.com.fiap.monitoramentomottu.dto.response.FilialResponse;
import br.com.fiap.monitoramentomottu.service.FilialService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/filiais")
public class FilialRestController implements IRestFilialController {
    private final FilialService service;

    public FilialRestController(FilialService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FilialResponse> create(@RequestBody @Valid FilialRequest dto) throws Exception {
        FilialResponse response = service.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialResponse> getById(@PathVariable Long id) throws Exception {
        FilialResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<FilialResponse>> getAll(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page,5, Sort.by("nome").ascending().and(Sort.by("cnpj").ascending()));
        return new ResponseEntity<>(service.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilialResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid FilialRequest dto) throws Exception {
        FilialResponse response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
