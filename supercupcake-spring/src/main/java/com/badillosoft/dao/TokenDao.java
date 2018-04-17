package com.badillosoft.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Token;

@Transactional
public interface TokenDao extends CrudRepository<Token, Long> {
	
	@Query(value="select * from token where NOW() <= expira AND value=:value", nativeQuery=true)
	public Optional<Token> validarToken(@Param("value") String value);
}
