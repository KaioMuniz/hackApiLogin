package br.hackathon.com.adapters.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class LoginResponse {

	private UUID id;
	private String email;
	private String token;
	private LocalDateTime expiracao;
}
