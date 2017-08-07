package br.com.personal.money.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.money.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByEmail(String email);
	

}
