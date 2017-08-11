package br.com.personal.money.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.money.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	List<Pessoa> findByNomeLike(String nome);

}
