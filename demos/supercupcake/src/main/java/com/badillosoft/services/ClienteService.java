package com.badillosoft.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badillosoft.beans.ClienteBean;
import com.badillosoft.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	public ClienteBean crear(String nombre, String correo) {
		ClienteBean cliente = new ClienteBean();
		cliente.setNombre(nombre);
		cliente.setCorreo(correo);
		
		try {
			clienteRepository.insertar(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return cliente;
	}
	
}
