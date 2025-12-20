package br.hackathon.com.application.ports;

import br.hackathon.com.adapters.dtos.CadastrarUsuarioRequest;
import br.hackathon.com.adapters.dtos.CadastrarUsuarioResponse;
import br.hackathon.com.adapters.dtos.LoginResponse;
import br.hackathon.com.adapters.dtos.LoginUsuarioRequest;

public interface UsuarioService {

	CadastrarUsuarioResponse cadastrar(CadastrarUsuarioRequest request);
	
	LoginResponse login(LoginUsuarioRequest request);
}
