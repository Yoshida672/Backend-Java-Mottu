package br.com.fiap.monitoramentomottu.controller.rest;

import br.com.fiap.monitoramentomottu.dto.request.PatioRequest;
import br.com.fiap.monitoramentomottu.dto.response.PatioResponse;
import br.com.fiap.monitoramentomottu.service.PatioService;
import br.com.fiap.monitoramentomottu.controller.rest.impl.IRestPatioController;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patios")
public class PatioRestController implements IRestPatioController {
    private final PatioService service;

    public PatioRestController(PatioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PatioResponse> create(@RequestBody @Valid PatioRequest dto) throws Exception {
        PatioResponse response = service.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioResponse> getById(@PathVariable Long id) throws Exception {
        PatioResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<PatioResponse>> getAll(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page,5, Sort.by("filial").ascending().and(Sort.by("capacidadeMoto")));
        return new ResponseEntity<>(service.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatioResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid PatioRequest dto) throws Exception {
        PatioResponse response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
