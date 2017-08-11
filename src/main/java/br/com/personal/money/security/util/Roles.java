package br.com.personal.money.security.util;

public final class Roles {
	
	public static final String ROLE_PESQUISAR_CATEGORIA = "hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')";
	public static final String ROLE_CADASTRAR_CATEGORIA = "hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')";

	public static final String ROLE_PESQUISAR_PESSOA = "hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')";
	public static final String ROLE_CADASTRAR_PESSOA = "hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')";
	public static final String ROLE_ALTERAR_PESSOA = "hasAuthority('ROLE_ALTERAR_PESSOA') and #oauth2.hasScope('write')";
	public static final String ROLE_REMOVER_PESSOA = "hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')";
	
	public static final String ROLE_PESQUISAR_LANCAMENTO = "hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')";
	public static final String ROLE_CADASTRAR_LANCAMENTO = "hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')";
	public static final String ROLE_ALTERAR_LANCAMENTO = "hasAuthority('ROLE_ALTERAR_LANCAMENTO') and #oauth2.hasScope('write')";
	public static final String ROLE_REMOVER_LANCAMENTO = "hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')";
}
