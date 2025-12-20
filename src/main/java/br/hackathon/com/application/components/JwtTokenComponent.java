package br.hackathon.com.application.components;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenComponent {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public Date getExpirationDate() {
		var dataAtual = new Date();
		
		return new Date(dataAtual.getTime() + expiration);
	}
	
	public String getToken(UUID id, String email, String perfil) {
		
		return Jwts.builder()
				.setSubject(id.toString())
				.claim("email", email)
				.claim("perfil", perfil)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, secret)
				.setExpiration(getExpirationDate())
				.compact();
				
	}
}
