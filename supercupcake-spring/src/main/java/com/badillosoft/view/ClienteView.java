package com.badillosoft.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.badillosoft.dao.ClienteDao;
import com.badillosoft.dao.OrdenDao;
import com.badillosoft.dto.Cliente;
import com.badillosoft.dto.Orden;

@Controller
public class ClienteView {
	@Autowired
	ClienteDao clienteDao;
	
	@Autowired
	OrdenDao ordenDao;
	
	@GetMapping("/ver/cliente/{id}")
	public String verCliente(@PathVariable("id") Long idCliente, Model model) {
		Cliente cliente = clienteDao.findById(idCliente).get();
		
		Iterable<Orden> ordenes = ordenDao.findAll(); 
		
		model.addAttribute("ordenes", ordenes);
		return "cliente";
	}
	
	@GetMapping("/ver/cliente/login")
	public String clienteLogin() {
		return "clienteLogin";
	}
	
}
