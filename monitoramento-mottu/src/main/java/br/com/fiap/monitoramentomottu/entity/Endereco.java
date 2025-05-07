package br.com.fiap.monitoramentomottu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private String cep;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String complemento;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
