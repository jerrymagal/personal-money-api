package br.com.personal.money.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.personal.money.model.Pessoa;
import br.com.personal.money.repository.PessoaRepository;

@Service
public class PessoaService extends GenericService<Pessoa, Long>{

	@Autowired
	public PessoaService(PessoaRepository repository) {
		super(repository);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaBanco = buscarPorCodigo(codigo);
		verificarExistenciaEntidade(pessoaBanco);
		pessoaBanco.setAtivo(ativo);
		getRepository().save(pessoaBanco);
	}
}
