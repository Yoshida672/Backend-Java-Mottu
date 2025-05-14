package br.com.fiap.monitoramentomottu.repository;

import br.com.fiap.monitoramentomottu.entity.Localizacao;
import br.com.fiap.monitoramentomottu.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository  extends JpaRepository<Localizacao, Long> {
}
