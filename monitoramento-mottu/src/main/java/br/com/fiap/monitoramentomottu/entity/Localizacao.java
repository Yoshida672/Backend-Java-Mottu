package br.com.fiap.monitoramentomottu.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "localizacao")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tag")
    private Uwb tag;

    @OneToOne
    @JoinColumn(name = "id_moto")
    private Moto moto;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;
    @Column(name="coordenada_x", nullable = false)
    private Double xCoord;
    @Column(name="coordenada_y", nullable = false)
    private Double yCoord;

    public Localizacao() {
    }

    public Localizacao(Long id, Uwb tag, Moto moto, LocalDateTime timestamp, Double xCoord, Double yCoord) {
        this.id = id;
        this.tag = tag;
        this.moto = moto;
        this.timestamp = timestamp;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Uwb getTag() {
        return tag;
    }

    public void setTag(Uwb tag) {
        this.tag = tag;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getxCoord() {
        return xCoord;
    }

    public void setxCoord(Double xCoord) {
        this.xCoord = xCoord;
    }

    public Double getyCoord() {
        return yCoord;
    }

    public void setyCoord(Double yCoord) {
        this.yCoord = yCoord;
    }

}
