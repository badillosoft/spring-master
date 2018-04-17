package com.badillosoft.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.badillosoft.dao.ClienteDao;
import com.badillosoft.dto.Cliente;

@Controller
public class ClienteView {
	@Autowired
	ClienteDao clienteDao;
	
	@GetMapping("/ver/cliente/{id}")
	public String verCliente(@PathVariable("id") Long idCliente, Model model) {
		Cliente cliente = clienteDao.findById(idCliente).get();
		
		model.addAttribute("titulo", cliente.getNombre() + " " + cliente.getCorreo());
		return "cliente";
	}
	
	@GetMapping("/ver/cliente/login")
	public String clienteLogin() {
		return "clienteLogin";
	}
	
}
