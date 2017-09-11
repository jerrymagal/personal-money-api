package br.com.personal.money.repository.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import br.com.personal.money.model.Pessoa;
import br.com.personal.money.model.Pessoa_;
import br.com.personal.money.model.filter.PessoaFilter;

public class PessoaSpecification {

	private PessoaFilter filter;

	public PessoaSpecification(PessoaFilter filter) {
		this.filter = filter;
	}

	private Specification<Pessoa> nome(String nome) {
		return (root, query, builder) -> builder.like(root.get(Pessoa_.nome), "%" + nome + "%");
	}

	public Specification<Pessoa> build() {

		Specification<Pessoa> where = Specifications.where(null);

		if (!StringUtils.isEmpty(filter.getNome())) {
			where = Specifications.where(where).and(nome(filter.getNome()));
		}

		return where;
	}

}
