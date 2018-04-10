package com.badillosoft.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.badillosoft.beans.*;

@RestController
@RequestMapping("/client")
public class ClientController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ClientBean> root(@PathVariable Integer id) {
		ClientBean client = new ClientBean();
		
		client.setId(id);
		client.setName("Alan Badillo");
		
		return new ResponseEntity<ClientBean>(client, HttpStatus.OK);
	}
	
}
