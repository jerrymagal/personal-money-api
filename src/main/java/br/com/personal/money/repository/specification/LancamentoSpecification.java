package br.com.personal.money.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.personal.money.model.Lancamento;
import br.com.personal.money.model.Lancamento_;
import br.com.personal.money.model.filter.LancamentoFilter;

public class LancamentoSpecification implements Specification<Lancamento> {

	private LancamentoFilter filter;
	
	public LancamentoSpecification(LancamentoFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Lancamento> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		Predicate predicate = cb.disjunction();
		
		if(!StringUtils.isEmpty(filter.getDescricao())) {
			predicate.getExpressions().add(cb.like(root.get(Lancamento_.descricao), "%" + filter.getDescricao() + "%"));
		}
		
		if(filter.getDataVencimentoDe() != null) {
			predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), filter.getDataVencimentoDe()));
		}

		if(filter.getDataVencimentoAte() != null) {
			predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), filter.getDataVencimentoAte()));
		}
		
		return predicate;
	}

}
