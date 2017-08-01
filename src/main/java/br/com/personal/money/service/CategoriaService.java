package br.com.personal.money.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.personal.money.model.Categoria;
import br.com.personal.money.repository.CategoriaRepository;

@Service
public class CategoriaService extends GenericService<Categoria, Long>{

	@Autowired
	public CategoriaService(CategoriaRepository repository) {
		super(repository);
	}
}
