package com.badillosoft.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badillosoft.dao.ClienteDao;
import com.badillosoft.dao.TelefonoDao;
import com.badillosoft.dto.Cliente;
import com.badillosoft.dto.Telefono;
import com.badillosoft.dto.Token;

@Service
public class ClienteService {

	@Autowired
	ClienteDao clienteDao;

	@Autowired
	TelefonoDao telefonoDao;
	
	@Autowired
	TokenService tokenService;
	
	public Cliente crearCliente(String nombre, String correo) {
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setCorreo(correo);
		
		//Set<Telefono> telefonos = new HashSet<>();
		
		//cliente.setTelefonos(telefonos);
		
		clienteDao.save(cliente);
		
		Telefono telefono = new Telefono();
		telefono.setCliente(cliente);
		telefono.setNumero("5512345678");
		
		telefonoDao.save(telefono);
		
		//telefonos.add(telefono);
		
		return clienteDao.findById(cliente.getId()).get();
	}
	
	public Boolean validarToken(Long idCliente, String token) {
		Optional<Cliente> clienteOpt = clienteDao.findById(idCliente);
		
		if (!clienteOpt.isPresent()) {
			return false;
		}
		
		Cliente cliente = clienteOpt.get();
		
		if (cliente.getToken() == null) {
			return false;
		}
		
		if (!cliente.getToken().getValue().equals(token)) {
			return false;
		}
		
		if (!cliente.getToken().getRol().equals("CLIENTE")) {
			return false;
		}
		
		return true;
	}
	
	public String iniciarSesion(String correo, String clave) throws ServletException {
		Optional<Cliente> clienteOpt = clienteDao.buscarPorCorreoClave(correo, clave);
		
		if (!clienteOpt.isPresent()) {
			throw new ServletException("No autorizado");
		}
		
		Cliente cliente = clienteOpt.get();
		
		Token token = null;
		
		if (cliente.getToken() == null) {
			token = tokenService.crear("CLIENTE");
			cliente.setToken(token);
			clienteDao.save(cliente);
		} else {
			token = tokenService.actualizar(cliente.getToken());
		}  
		
		return token.getValue();
	}
	
}
