package br.com.personal.money.repository.specification;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import br.com.personal.money.model.Lancamento;
import br.com.personal.money.model.Lancamento_;
import br.com.personal.money.model.filter.LancamentoFilter;

public class LancamentoSpecification {

	private LancamentoFilter filter;

	public LancamentoSpecification(LancamentoFilter filter) {
		this.filter = filter;
	}

	private Specification<Lancamento> descricao(String descricao) {
		return (root, query, builder) -> builder.like(root.get(Lancamento_.descricao), "%" + descricao + "%");
	}

	private Specification<Lancamento> vencimentoDe(LocalDate vencimento) {
		return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), vencimento);
	}

	private Specification<Lancamento> vencimentoAte(LocalDate vencimento) {
		return (root, query, builder) -> builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), vencimento);
	}

	public Specification<Lancamento> build() {

		Specification<Lancamento> where = Specifications.where(null);

		if (!StringUtils.isEmpty(filter.getDescricao())) {
			where = Specifications.where(where).and(descricao(filter.getDescricao()));
		}

		if (filter.getDataVencimentoDe() != null) {
			where = Specifications.where(where).and(vencimentoDe(filter.getDataVencimentoDe()));
		}

		if (filter.getDataVencimentoAte() != null) {
			where = Specifications.where(where).and(vencimentoAte(filter.getDataVencimentoAte()));
		}

		return where;
	}

}
