package com.badillosoft.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badillosoft.beans.*;
import com.badillosoft.repositories.*;

@Service
public class OrdenCupcakesService {

	@Autowired
	OrdenCupcakesRepository ordenCupcakesRepository;
	@Autowired
	EstatusRepository estatusRepository;
	@Autowired
	OrdenRepository ordenRepository;
	
	public OrdenCupcakesBean agregarCupcake(OrdenBean orden, CupcakeBean cupcake, Integer multiplicador) throws SQLException {
		OrdenCupcakesBean ordenCupcakes = new OrdenCupcakesBean();
		ordenCupcakes.setOrden(orden);
		ordenCupcakes.setCupcake(cupcake);
		ordenCupcakes.setMultiplicador(multiplicador);
		
		ordenCupcakesRepository.insertar(ordenCupcakes);
		
		EstatusBean estatus = estatusRepository.buscarPorId(2);
		orden.setEstatus(estatus);
		
		ordenRepository.actualizar(orden);
		
		return ordenCupcakes;
	}
	
}
