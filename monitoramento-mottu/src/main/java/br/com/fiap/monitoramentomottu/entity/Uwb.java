package br.com.fiap.monitoramentomottu.entity;

import jakarta.persistence.*;

@Entity
public class Uwb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTag;

    @Column(unique = true, nullable = false)
    private String codigo;

    private String status;

    @OneToOne
    @JoinColumn(name = "id_moto")
    private Moto moto;
}
