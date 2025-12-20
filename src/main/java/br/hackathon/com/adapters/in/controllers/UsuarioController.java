package br.hackathon.com.adapters.in.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hackathon.com.adapters.dtos.CadastrarUsuarioRequest;
import br.hackathon.com.adapters.dtos.CadastrarUsuarioResponse;
import br.hackathon.com.adapters.dtos.LoginResponse;
import br.hackathon.com.adapters.dtos.LoginUsuarioRequest;
import br.hackathon.com.application.ports.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	@Autowired UsuarioService service;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginUsuarioRequest request) {
		return ResponseEntity.ok(service.login(request));
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<CadastrarUsuarioResponse> cadastrar(@RequestBody CadastrarUsuarioRequest request) {
		return ResponseEntity.ok(service.cadastrar(request));
	}
}
