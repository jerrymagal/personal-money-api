package br.com.personal.money.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.personal.money.model.Categoria_;
import br.com.personal.money.model.Lancamento;
import br.com.personal.money.model.Lancamento_;
import br.com.personal.money.model.Pessoa_;
import br.com.personal.money.model.filter.LancamentoFilter;
import br.com.personal.money.model.projection.ResumoLancamento;
import br.com.personal.money.repository.query.LancamentoRepositoryQuery;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteriaQuery = criteriaBuilder.createQuery(Lancamento.class);
		
		Root<Lancamento> from = criteriaQuery.from(Lancamento.class);
		
		Predicate[] restrictions = criarRestricoes(filter, criteriaBuilder, from);
		criteriaQuery.where(restrictions);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteriaQuery);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}
	
	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteriaQuery = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> from = criteriaQuery.from(Lancamento.class);
		
		criteriaQuery.select(builder.construct(ResumoLancamento.class, from.get(Lancamento_.codigo),
													 from.get(Lancamento_.descricao),
													 from.get(Lancamento_.dataVencimento),
													 from.get(Lancamento_.dataPagamento),
													 from.get(Lancamento_.valor),
													 from.get(Lancamento_.tipo),
													 from.get(Lancamento_.categoria).get(Categoria_.nome),
													 from.get(Lancamento_.pessoa).get(Pessoa_.nome)));
		
		Predicate[] restricoes = criarRestricoes(filter, builder, from);
		criteriaQuery.where(restricoes);
		
		TypedQuery<ResumoLancamento> query = manager.createQuery(criteriaQuery);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Long total(LancamentoFilter filter) {
		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		
		Root<Lancamento> from = criteriaQuery.from(Lancamento.class);
		
		Predicate[] predicates = criarRestricoes(filter, criteriaBuilder, from);
		criteriaQuery.where(predicates);
		
		criteriaQuery.select(criteriaBuilder.count(from));
		
		return manager.createQuery(criteriaQuery).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalPorPagina;
	
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalPorPagina);
	}

	private Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder criteriaBuilder, Root<Lancamento> from) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filter.getDescricao())) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(from.get(Lancamento_.descricao)),
							"%" + filter.getDescricao().toLowerCase() + "%"));
		}
		
		if(filter.getDataVencimentoDe() != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(from.get(Lancamento_.dataVencimento), filter.getDataVencimentoDe()));
		}

		if(filter.getDataVencimentoAte() != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(from.get(Lancamento_.dataVencimento), filter.getDataVencimentoDe()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
