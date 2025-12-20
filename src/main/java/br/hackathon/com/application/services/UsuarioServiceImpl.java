package br.hackathon.com.application.services;

import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hackathon.com.adapters.dtos.CadastrarUsuarioRequest;
import br.hackathon.com.adapters.dtos.CadastrarUsuarioResponse;
import br.hackathon.com.adapters.dtos.LoginResponse;
import br.hackathon.com.adapters.dtos.LoginUsuarioRequest;
import br.hackathon.com.adapters.out.UsuarioRepository;
import br.hackathon.com.application.components.JwtTokenComponent;
import br.hackathon.com.application.ports.UsuarioService;
import br.hackathon.com.domain.exceptions.AcessoNegadoException;
import br.hackathon.com.domain.models.Perfil;
import br.hackathon.com.domain.models.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired UsuarioRepository repository;
	@Autowired JwtTokenComponent jwtTokenComponent;
	
	@Override
	public CadastrarUsuarioResponse cadastrar(CadastrarUsuarioRequest request) {

		if(repository.existsByEmail(request.getEmail())) {
			throw new AcessoNegadoException();
		}
		
		var usuario = new Usuario();
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());
		usuario.setPerfil(Perfil.valueOf(request.getPerfil()));
		repository.save(usuario);
		
		var resp = new CadastrarUsuarioResponse();
		
		resp.setId(usuario.getId());
		resp.setEmail(usuario.getEmail());
		resp.setDataCriacao(usuario.getDataCriacao());
		
		
		return resp;
	}

	@Override
	public LoginResponse login(LoginUsuarioRequest request) {
		
		var usuario = repository.findByEmailAndSenha(request.getEmail(), request.getSenha());
		if(usuario == null) {
			throw new AcessoNegadoException();
		}
		
		return toResponse(usuario);
	}
	
	private LoginResponse toResponse(Usuario usuario) {
		var resp = new LoginResponse();
		resp.setId(usuario.getId());
		resp.setEmail(usuario.getEmail());
		resp.setExpiracao(jwtTokenComponent.getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		resp.setToken(jwtTokenComponent.getToken
				(usuario.getId(), usuario.getEmail(), usuario.getPerfil().toString()));
		
		return resp;
	
	}

}
