package br.com.personal.money.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.money.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, JpaSpecificationExecutorProjection<Lancamento> {
	
}
