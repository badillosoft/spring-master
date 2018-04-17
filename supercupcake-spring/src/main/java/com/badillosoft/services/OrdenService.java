package com.badillosoft.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badillosoft.dao.EstatusDao;
import com.badillosoft.dao.OrdenDao;
import com.badillosoft.dto.Cliente;
import com.badillosoft.dto.Cupcake;
import com.badillosoft.dto.Estatus;
import com.badillosoft.dto.Orden;

@Service
public class OrdenService {
	
	@Autowired
	OrdenDao ordenDao;
	
	@Autowired
	EstatusDao estatusDao;
	
	public Orden crearOrden () {
		Optional<Estatus> optEstatus = estatusDao.findById(1l);
		
		if (!optEstatus.isPresent()) {
			return null;
		}
		
		Orden orden = new Orden();
		
		orden.setEstatus(optEstatus.get());
		
		ordenDao.save(orden);
		
		return orden;
	}
	
	public Orden asignarCliente(Orden orden, Cliente cliente) {
		orden.setCliente(cliente);
		
		ordenDao.save(orden);
		
		return orden;
	}
	
	public Orden agregarCupcake(Orden orden, Cupcake cupcake) {
		List<Cupcake> cupcakes = orden.getCupcakes();
		
		if (cupcakes == null) {
			cupcakes = new ArrayList<Cupcake>();
			orden.setCupcakes(cupcakes);
		}
		
		cupcakes.add(cupcake);
		
		ordenDao.save(orden);
		
		return orden;
	}
	
}
