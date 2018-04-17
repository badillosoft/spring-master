package com.badillosoft.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Estatus;

@Transactional
public interface EstatusDao extends CrudRepository<Estatus, Long> {
	
}
