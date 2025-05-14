package br.com.fiap.monitoramentomottu.entity;

import br.com.fiap.monitoramentomottu.entity.Condicao;
import br.com.fiap.monitoramentomottu.entity.Localizacao;
import br.com.fiap.monitoramentomottu.entity.Modelo;
import br.com.fiap.monitoramentomottu.entity.Uwb;
import jakarta.persistence.*;

@Entity
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String placa;

    @Enumerated(EnumType.STRING)
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "id_condicao", nullable = false)
    private Condicao condicao;

    @OneToOne(mappedBy = "moto", cascade = CascadeType.ALL)
    private Uwb uwb;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;

    public Moto() {
    }

    public Moto(Long id, String placa, Modelo modelo, Condicao condicao, Uwb uwb, Localizacao localizacao) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.condicao = condicao;
        this.uwb = uwb;
        this.localizacao = localizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Condicao getCondicao() {
        return condicao;
    }

    public void setCondicao(Condicao condicao) {
        this.condicao = condicao;
    }

    public Uwb getUwb() {
        return uwb;
    }

    public void setUwb(Uwb uwb) {
        this.uwb = uwb;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}