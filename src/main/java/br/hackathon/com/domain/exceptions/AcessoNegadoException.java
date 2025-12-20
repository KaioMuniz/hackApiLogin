package br.hackathon.com.domain.exceptions;
@SuppressWarnings("serial")
public class AcessoNegadoException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Acesso Negado. por favor, verifique suas credenciais.";
	}
}
