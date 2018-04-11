package com.badillosoft.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badillosoft.beans.*;
import com.badillosoft.repositories.EstatusRepository;
import com.badillosoft.repositories.OrdenRepository;

@Service
public class OrdenService {

	@Autowired
	OrdenRepository ordenRepository;
	@Autowired
	EstatusRepository estatusRepository;
	
	public OrdenBean iniciar() throws SQLException {
		OrdenBean orden = new OrdenBean();
		
		EstatusBean estatus = estatusRepository.buscarPorId(1);
		orden.setEstatus(estatus);
		
		ordenRepository.insertar(orden);
		
		return orden;
	}
	
	public OrdenBean asignarCliente(OrdenBean orden, ClienteBean cliente) throws SQLException {
		orden.setCliente(cliente);
		EstatusBean estatus = estatusRepository.buscarPorId(13);
		orden.setEstatus(estatus);
		ordenRepository.actualizar(orden);
		return orden;
	}
	
	public OrdenBean asignarVendedor(OrdenBean orden, VendedorBean vendedor) throws SQLException {
		orden.setVendedor(vendedor);
		EstatusBean estatus = estatusRepository.buscarPorId(14);
		orden.setEstatus(estatus);
		ordenRepository.actualizar(orden);
		return orden;
	}
	
}
