package br.com.personal.money.model.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.personal.money.enums.TipoLancamento;

public interface ResumoLancamento {

	Long getCodigo();

	String getDescricao();

	LocalDate getDataVencimento();

	LocalDate getDataPagamento();

	BigDecimal getValor();

	TipoLancamento getTipo();

	ResumoCategoria getCategoria();

	ResumoPessoa getPessoa();

}
