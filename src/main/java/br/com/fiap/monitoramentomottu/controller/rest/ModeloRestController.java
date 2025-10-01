package br.com.fiap.monitoramentomottu.controller.rest;

import br.com.fiap.monitoramentomottu.entity.Modelo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/modelos")
public class ModeloRestController {

    @GetMapping
    public List<String> getModelos() {
        return Arrays.stream(Modelo.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
