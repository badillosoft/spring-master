package com.badillosoft.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Cupcake;

@Transactional
public interface CupcakeDao extends CrudRepository<Cupcake, Long> {
	
}
