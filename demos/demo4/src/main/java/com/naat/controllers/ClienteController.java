package com.naat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naat.beans.ClienteBean;
import com.naat.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;

	@RequestMapping(value="/crear", method=RequestMethod.GET, produces="application/json")
	public ClienteBean crear(@RequestParam String nombre) {
		return clienteService.crearCliente(nombre);
	}
	
}
