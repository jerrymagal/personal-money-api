package br.com.personal.money.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.money.event.RecursoCriadoEvent;
import br.com.personal.money.model.BasicEntity;

public abstract class GenericService<T extends BasicEntity, Serializable> {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	private JpaRepository<T, Long> repository;

	public GenericService(JpaRepository<T, Long> repository) {
		this.repository = repository;
	}
	
	public List<T> buscarTodos() {
		return repository.findAll();
	}
	
	public T buscarPorCodigo(Long codigo) {
		T t = repository.findOne(codigo);
		verificarExistenciaEntidade(t);
		return t;
	}
	
	public T salvar(T t, HttpServletResponse response) {
		
		BasicEntity entity = (BasicEntity) t;
		repository.save(t);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, entity.getCodigo()));
		return t;
	}
	
	public T atualizar(Long codigo, T t) {
		
		T entidadeBanco = this.buscarPorCodigo(codigo);
		verificarExistenciaEntidade(entidadeBanco);
		
		BeanUtils.copyProperties(t, entidadeBanco, "codigo");
		repository.save(entidadeBanco);
		
		return entidadeBanco; 
	}
	
	public void remover(Long codigo) {
		repository.delete(codigo);
	}
	
	protected void verificarExistenciaEntidade(T t) {
		
		if(t == null) {
			throw new EmptyResultDataAccessException(1);
		}
	}

	protected JpaRepository<T, Long> getRepository() {
		return repository;
	}
}
