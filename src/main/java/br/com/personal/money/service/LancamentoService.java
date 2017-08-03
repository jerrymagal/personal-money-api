package br.com.personal.money.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.personal.money.model.Lancamento;
import br.com.personal.money.model.Pessoa;
import br.com.personal.money.model.filter.LancamentoFilter;
import br.com.personal.money.repository.LancamentoRepository;
import br.com.personal.money.repository.PessoaRepository;
import br.com.personal.money.service.exception.PessoaInexistenteOuInativaExcpetion;

@Service
public class LancamentoService extends GenericService<Lancamento, Long> {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	public LancamentoService(LancamentoRepository repository) {
		super(repository);
	}

	@Override
	public Lancamento salvar(Lancamento lancamento, HttpServletResponse response) {
		
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaExcpetion();
		}
		
		return super.salvar(lancamento, response);
	}
	
	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
		LancamentoRepository repository = (LancamentoRepository) getRepository();
		return repository.filtrar(filter, pageable);
	}

}
