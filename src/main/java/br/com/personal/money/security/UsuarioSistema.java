package br.com.personal.money.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.personal.money.model.Usuario;

public class UsuarioSistema extends User {
	
	private static final long serialVersionUID = -8322218307242310973L;
	private Usuario usuario;
	
	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> autorizacoes) {
		super(usuario.getEmail(), usuario.getSenha(), autorizacoes);
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}
