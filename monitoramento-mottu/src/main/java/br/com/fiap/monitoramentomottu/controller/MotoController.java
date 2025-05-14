package br.com.fiap.monitoramentomottu.controller;

import br.com.fiap.monitoramentomottu.dto.MotoRequest;
import br.com.fiap.monitoramentomottu.dto.MotoResponse;
import br.com.fiap.monitoramentomottu.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoService service;

    public MotoController(MotoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MotoResponse> create(@RequestBody @Valid MotoRequest dto) throws Exception {
        MotoResponse response = service.create(dto);

        // Adiciona links HATEOAS
        response = adicionarLinks(response);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponse> getById(@PathVariable Long id) throws Exception {
        MotoResponse response = service.getById(id);
        response = adicionarLinks(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MotoResponse>> getAll() {
        List<MotoResponse> responses = service.getAll();
        responses = responses.stream()
                .map(this::adicionarLinksBasicos)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid MotoRequest dto) throws Exception {
        MotoResponse response = service.update(id, dto);
        response = adicionarLinks(response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares para HATEOAS
    private MotoResponse adicionarLinks(MotoResponse response) {
        List<Link> links = new ArrayList<>();
        links.add(Link.of("/motos/" + response.id(), "self"));
        links.add(Link.of("/motos/" + response.id(), "atualizar"));
        links.add(Link.of("/motos/" + response.id(), "deletar"));
        links.add(Link.of("/motos", "todas-motos"));
        return response.withLinks(links);
    }

    private MotoResponse adicionarLinksBasicos(MotoResponse response) {
        List<Link> links = new ArrayList<>();
        links.add(Link.of("/motos/" + response.id(), "self"));
        return response.withLinks(links);
    }
}