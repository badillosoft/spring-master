package com.badillosoft.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Cliente;

@Transactional
public interface ClienteDao extends CrudRepository<Cliente, Long> {
	
	@Query(value="select * from cliente where correo=:correo and clave=:clave limit 1", nativeQuery=true)
	public Optional<Cliente> buscarPorCorreoClave(@Param("correo") String correo, @Param("clave") String clave);
	
	public Optional<Cliente> findByCorreo(String correo);
	
}
