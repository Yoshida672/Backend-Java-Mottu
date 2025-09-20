package br.com.fiap.monitoramentomottu.dto.response;

import org.springframework.hateoas.Link;

import java.util.List;

public record FilialResponse(
         Long id,
         String nome,
         String cnpj,
         int ano,
         EnderecoResponse endereco,
         List<PatioResponse> patios,
         Link link) {
}
