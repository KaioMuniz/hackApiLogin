package br.hackathon.com.adapters.dtos;

import lombok.Data;

@Data
public class CadastrarUsuarioRequest {

	private String email;
	private String senha;
	private String perfil;
}
