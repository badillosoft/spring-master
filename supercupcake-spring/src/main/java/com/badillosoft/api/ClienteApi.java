package com.badillosoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.badillosoft.dto.Cliente;
import com.badillosoft.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteApi {
	
	@Autowired
	ClienteService clienteService;

	@RequestMapping(value="/crear")
	@ResponseBody
	public Cliente crearOrden(@RequestParam String nombre, @RequestParam String correo) {
		return clienteService.crearCliente(nombre, correo);
	}
	
}
