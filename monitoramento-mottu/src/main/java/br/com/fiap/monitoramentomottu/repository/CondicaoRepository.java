package br.com.fiap.monitoramentomottu.repository;

import br.com.fiap.monitoramentomottu.entity.Condicao;
import br.com.fiap.monitoramentomottu.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondicaoRepository  extends JpaRepository<Condicao, Long> {
}
