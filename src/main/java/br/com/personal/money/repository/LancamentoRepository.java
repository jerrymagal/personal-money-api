package br.com.personal.money.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.money.model.Lancamento;
import br.com.personal.money.repository.query.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
