package br.com.fiap.monitoramentomottu.dto.response;

public record EnderecoResponse(
     int numero,
     String cep,
     String estado,
     String cidade,
     String bairro,
     String logradouro) {
}
