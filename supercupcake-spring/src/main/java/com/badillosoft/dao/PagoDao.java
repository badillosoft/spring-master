package com.badillosoft.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Pago;

@Transactional
public interface PagoDao extends CrudRepository<Pago, Long> {
	
}
