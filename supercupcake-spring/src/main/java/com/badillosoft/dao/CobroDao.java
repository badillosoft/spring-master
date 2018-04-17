package com.badillosoft.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Cobro;

@Transactional
public interface CobroDao extends CrudRepository<Cobro, Long> {
	
}
