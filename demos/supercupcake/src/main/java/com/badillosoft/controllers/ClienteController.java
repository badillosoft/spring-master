package com.badillosoft.controllers;

import com.badillosoft.beans.*;
import com.badillosoft.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(value="/crear", produces="application/json")
	public ClienteBean crear(@RequestParam String nombre, @RequestParam String correo) {
		return clienteService.crear(nombre, correo);
	}

}
