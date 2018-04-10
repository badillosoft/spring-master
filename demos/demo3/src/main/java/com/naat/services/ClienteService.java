package com.naat.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naat.beans.ClienteBean;
import com.naat.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public ClienteBean crearCliente(String nombre) {
		ClienteBean cliente = new ClienteBean();
		cliente.setNombre(nombre);
		
		try {
			clienteRepository.insertar(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cliente;
	}
}
