package com.badillosoft.api;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.badillosoft.dao.TelefonoDao;
import com.badillosoft.dao.TokenDao;
import com.badillosoft.dto.Cliente;
import com.badillosoft.dto.Orden;
import com.badillosoft.dto.Telefono;
import com.badillosoft.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteApi {
	
	@Autowired
	ClienteService clienteService;

	@Autowired
	ClienteDao clienteDao;
	
	@Autowired
	TelefonoDao telefonoDao;
	
	@Autowired
	OrdenDao ordenDao;

	@Autowired
	TokenDao tokenDao;
	
	public void cors(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type, Content-Range, Content-Disposition, Content-Description");
	}

	@RequestMapping(value="/telefono/{id}")
	@ResponseBody
	public Telefono verCliente(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Optional<Telefono> telefonoOpt = telefonoDao.findById(id);
		
		if (!telefonoOpt.isPresent()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), "No existe el teléfono");
			return null;
		}
		
		return telefonoOpt.get();
	}
	
	@RequestMapping(value="/{id}")
	@ResponseBody
	public Optional<Cliente> verTelefono(@PathVariable Long id) {
		return clienteDao.findById(id);
	}
	
	@RequestMapping(value="/buscarPorCorreo")
	@ResponseBody
	public Cliente buscarPorCorreo(@RequestParam String correo,
			HttpServletResponse response) {
		cors(response);
		Optional<Cliente> clienteOptional = clienteDao.findByCorreo(correo);
		
		if (!clienteOptional.isPresent()) {
			return null;
		}
		
		return clienteOptional.get();
	}
	
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
