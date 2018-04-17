package com.badillosoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badillosoft.dao.ClienteDao;
import com.badillosoft.dto.Cliente;

@Service
public class ClienteService {

	@Autowired
	ClienteDao clienteDao;
	
	public Cliente crearCliente(String nombre, String correo) {
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setCorreo(correo);
		
		clienteDao.save(cliente);
		
		return cliente;
	}
	
}
