package br.com.fiap.monitoramentomottu.service.processor.search;

import br.com.fiap.monitoramentomottu.entity.Moto;
import br.com.fiap.monitoramentomottu.repository.MotoRepository;
import br.com.fiap.monitoramentomottu.service.impl.BuscaMotoProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaCondicaoProcessor implements BuscaMotoProcessor {

    private final MotoRepository motoRepository;

    public BuscaCondicaoProcessor(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    @Override
    public List<Moto> search(String modelo, String condicao) {
        return motoRepository.findByCondicaoNomeIgnoreCase(condicao);
    }

    @Override
    public List<TiposBuscaMoto> getSupportedTypes() {
        return List.of(TiposBuscaMoto.CONDICAO);
    }
}