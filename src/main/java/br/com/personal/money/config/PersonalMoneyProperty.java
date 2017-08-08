package br.com.personal.money.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("personalmoney")
public class PersonalMoneyProperty {

	private String origemPermitida = "http://localhost:8080";

	private final Seguranca seguranca = new Seguranca();

	public String getOrigemPermitida() {
		return origemPermitida;
	}

	public void setOrigemPermitida(String origemPermitida) {
		this.origemPermitida = origemPermitida;
	}

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public static class Seguranca {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	}

}
