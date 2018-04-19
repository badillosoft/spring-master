package com.badillosoft.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Telefono;

@Transactional
public interface TelefonoDao extends CrudRepository<Telefono, Long> {
	
}
