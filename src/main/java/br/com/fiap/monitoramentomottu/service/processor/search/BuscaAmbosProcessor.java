package br.com.fiap.monitoramentomottu.service.processor.search;

import br.com.fiap.monitoramentomottu.entity.Modelo;
import br.com.fiap.monitoramentomottu.entity.Moto;
import br.com.fiap.monitoramentomottu.repository.MotoRepository;
import br.com.fiap.monitoramentomottu.service.impl.BuscaMotoProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BuscaAmbosProcessor implements BuscaMotoProcessor {
    private final MotoRepository motoRepository;

    public BuscaAmbosProcessor(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    @Override
    public List<Moto> search(String modelo, String condicao) {
        try {
            Modelo modeloEnum = Modelo.valueOf(modelo.toUpperCase());
            return motoRepository.findByModeloAndCondicaoNomeIgnoreCase(modeloEnum, condicao);
        } catch (Exception e) {
            System.out.println("Nao foi possivel buscar por modelo e condição, erro: " + e);
            return List.of();
        }

    }

    @Override
    public List<TiposBuscaMoto> getSupportedTypes() {
        return List.of(TiposBuscaMoto.AMBOS);
    }
}
