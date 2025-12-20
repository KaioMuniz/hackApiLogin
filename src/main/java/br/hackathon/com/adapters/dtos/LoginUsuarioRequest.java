package br.hackathon.com.adapters.dtos;

import lombok.Data;

@Data
public class LoginUsuarioRequest {

	private String email;
	private String senha;
}
