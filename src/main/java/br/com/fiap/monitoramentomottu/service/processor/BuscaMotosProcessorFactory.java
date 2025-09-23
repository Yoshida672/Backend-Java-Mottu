package br.com.fiap.monitoramentomottu.service.processor;

import br.com.fiap.monitoramentomottu.service.impl.BuscaMotoProcessor;
import br.com.fiap.monitoramentomottu.service.processor.search.BuscaAmbosProcessor;
import br.com.fiap.monitoramentomottu.service.processor.search.TiposBuscaMoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class BuscaMotosProcessorFactory {
    private final Map<TiposBuscaMoto, BuscaMotoProcessor> processorMap = new EnumMap<>(TiposBuscaMoto.class);
    private final BuscaAmbosProcessor defaultProcessor;
    public BuscaMotosProcessorFactory(BuscaAmbosProcessor defaultProcessor) {
        this.defaultProcessor = defaultProcessor;
    }

    @Autowired
    public BuscaMotosProcessorFactory(List<BuscaMotoProcessor> processors,
                                      BuscaAmbosProcessor defaultProcessor) {
        this.defaultProcessor = defaultProcessor;
        for (BuscaMotoProcessor processor : processors) {
            for (TiposBuscaMoto type : processor.getSupportedTypes()) {
                processorMap.put(type, processor);
            }
        }
    }


    public BuscaMotoProcessor getProcessor(TiposBuscaMoto type) {
        return processorMap.getOrDefault(type, defaultProcessor);
    }
}
