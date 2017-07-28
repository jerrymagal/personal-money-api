package br.com.personal.money.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.money.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
