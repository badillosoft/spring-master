package com.badillosoft.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.badillosoft.beans.ClientBean;

@RestController
@RequestMapping("/client")
public class ClientController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ClientBean viewById(@PathVariable Integer id) {
		return new ClientBean(id, "John Doe");
	}
	
}
