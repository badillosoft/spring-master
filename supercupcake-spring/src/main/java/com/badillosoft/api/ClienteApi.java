package com.badillosoft.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.badillosoft.dao.ClienteDao;
import com.badillosoft.dao.OrdenDao;
import com.badillosoft.dao.TokenDao;
import com.badillosoft.dto.Cliente;
import com.badillosoft.dto.Orden;
import com.badillosoft.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteApi {
	
	@Autowired
	ClienteService clienteService;

	@Autowired
	ClienteDao clienteDao;
	
	@Autowired
	OrdenDao ordenDao;

	@Autowired
	TokenDao tokenDao;

	@RequestMapping(value="/crear")
	@ResponseBody
	public Cliente crearOrden(@RequestParam String nombre, @RequestParam String correo) {
		return clienteService.crearCliente(nombre, correo);
	}
	
	@RequestMapping(value="/acceder", method=RequestMethod.POST)
	@ResponseBody
	public String iniciarSesion(@RequestParam String correo, String clave) throws ServletException {
		return clienteService.iniciarSesion(correo, clave);
	}
	
	@RequestMapping(value="/{id}/ordenes")
	@ResponseBody
	public List<Orden> crearOrden(@PathVariable("id") Long idCliente, @RequestParam String token) {
		if (!clienteService.validarToken(idCliente, token)) {
			return null;
		}
		
		return ordenDao.obtenerOrdenes(idCliente);
	}
	
	@RequestMapping(value="/{id}/activo")
	@ResponseBody
	public Boolean activo(@PathVariable("id") Long idCliente) {
		Optional<Cliente> clienteOpt = clienteDao.findById(idCliente);
		
		if (!clienteOpt.isPresent()) {
			return false;
		}
		
		String token = clienteOpt.get().getToken().getValue();
		
		return tokenDao.validarToken(token).isPresent();
	}

}
