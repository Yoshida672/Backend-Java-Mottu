package br.com.fiap.monitoramentomottu.service.impl;

import br.com.fiap.monitoramentomottu.entity.Moto;
import br.com.fiap.monitoramentomottu.service.processor.search.TiposBuscaMoto;

import java.util.List;

public interface BuscaMotoProcessor {
    List<Moto> search(String modelo, String condicao);
    List<TiposBuscaMoto> getSupportedTypes();
}