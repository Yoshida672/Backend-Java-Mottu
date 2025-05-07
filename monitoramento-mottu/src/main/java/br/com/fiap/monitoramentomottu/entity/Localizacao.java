package br.com.fiap.monitoramentomottu.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Localizacao")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalizacao;

    @ManyToOne
    @JoinColumn(name = "id_tag")
    private Uwb tag;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;

    private Double xCoord;
    private Double yCoord;
    private Double zCoord;
}
