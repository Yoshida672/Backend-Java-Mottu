package br.com.fiap.monitoramentomottu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qtdMoto;
    private int areaPatio;
    private int capacidadeMoto;
    private Filial filial;
}
