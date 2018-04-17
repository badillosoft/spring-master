package com.badillosoft.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badillosoft.dao.TokenDao;
import com.badillosoft.dto.Token;

@Service
public class TokenService {
	@Autowired
	TokenDao tokenDao;
	
	public Token crear(String rol) {
		Token token = new Token();
		token.setValue(UUID.randomUUID().toString());
		token.setCreado(Timestamp.valueOf(LocalDateTime.now()));
		token.setExpira(Timestamp.valueOf(LocalDateTime.now().plusMinutes(10)));
		token.setRol(rol);
		
		tokenDao.save(token);
		
		return token;
	}
	
	public Token actualizar(Token token) {
		token.setValue(UUID.randomUUID().toString());
		token.setExpira(Timestamp.valueOf(LocalDateTime.now().plusMinutes(10)));
		
		tokenDao.save(token);
		
		return token;
	}
	
}
