package com.badillosoft.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Cocina;

@Transactional
public interface CocinaDao extends CrudRepository<Cocina, Long> {
	
}
