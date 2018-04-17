package com.badillosoft.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Vendedor;

@Transactional
public interface VendedorDao extends CrudRepository<Vendedor, Long> {
	
}
