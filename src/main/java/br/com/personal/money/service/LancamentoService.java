package br.com.personal.money.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.personal.money.model.Lancamento;
import br.com.personal.money.model.Pessoa;
import br.com.personal.money.model.filter.LancamentoFilter;
import br.com.personal.money.model.projection.ResumoLancamento;
import br.com.personal.money.repository.LancamentoRepository;
import br.com.personal.money.repository.PessoaRepository;
import br.com.personal.money.repository.specification.LancamentoSpecification;
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
		validarPessoa(lancamento);
		return super.salvar(lancamento, response);
	}
	
	@Override
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		
		Lancamento lancamentoBanco = buscarPorCodigo(codigo);
		verificarExistenciaEntidade(lancamentoBanco);

		if(lancamento.getPessoa().equals(lancamentoBanco.getPessoa())) {
			validarPessoa(lancamento);
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoBanco, "codigo");
		
		return getRepository().save(lancamentoBanco);
	}
	
	private void validarPessoa(Lancamento lancamento) {

		Pessoa pessoa = null;
		Long codigoPessoa = lancamento.getPessoa().getCodigo();
		
		if(codigoPessoa != null) {
			pessoa = pessoaRepository.findOne(codigoPessoa);
		}
		
		verificarExistenciaPessoa(pessoa);
	}

	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
		LancamentoSpecification lancamentoSpecification = new LancamentoSpecification(filter);
		return getRepository().findAll(lancamentoSpecification, pageable);
	}

	public Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable) {
		LancamentoSpecification lancamentoSpecification = new LancamentoSpecification(filter);
		return getRepository().findAll(lancamentoSpecification, ResumoLancamento.class, pageable);
	}
	
	@Override
	protected LancamentoRepository getRepository() {
		return (LancamentoRepository) super.getRepository();
	}
		
	private void verificarExistenciaPessoa(Pessoa pessoa) {
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaExcpetion();
		}
	}
}
