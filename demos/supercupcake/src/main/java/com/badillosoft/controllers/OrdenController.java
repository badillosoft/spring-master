package com.badillosoft.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.badillosoft.beans.*;
import com.badillosoft.repositories.*;
import com.badillosoft.services.*;

@RestController
@RequestMapping("/orden")
public class OrdenController {

	@Autowired
	OrdenService ordenService;
	@Autowired
	OrdenCupcakesService ordenCupcakesService;
	@Autowired
	OrdenRepository ordenRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	VendedorRepository vendedorRepository;
	@Autowired
	CupcakeRepository cupcakeRepository;
	
	@RequestMapping(value="iniciar", produces="application/json")
	public OrdenBean iniciar() {
		OrdenBean orden = null;
		
		try {
			orden = ordenService.iniciar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orden;
	}
	
	@RequestMapping(value="{id}", produces="application/json")
	public OrdenBean ver(@PathVariable("id") Integer id) {
		System.out.printf("ID: %d\n", id);
		try {
			return ordenRepository.buscarPorId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="{idOrden}/asignar/cliente", produces="application/json")
	public OrdenBean asignarCliente(@PathVariable("idOrden") Integer idOrden, @RequestParam Integer idCliente) {
		try {
			ClienteBean cliente = clienteRepository.buscarPorId(idCliente);
			OrdenBean orden = ordenRepository.buscarPorId(idOrden);
			return ordenService.asignarCliente(orden, cliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="{idOrden}/asignar/vendedor", produces="application/json")
	public OrdenBean asignarVendedor(@PathVariable("idOrden") Integer idOrden, @RequestParam(required=false) Integer idVendedor) {
		try {
			VendedorBean vendedor = null;
			if (idVendedor != null) {
				vendedor = vendedorRepository.buscarPorId(idVendedor);
			} else {
				vendedor = vendedorRepository.buscarAleatorio();
			}
			OrdenBean orden = ordenRepository.buscarPorId(idOrden);
			return ordenService.asignarVendedor(orden, vendedor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="{idOrden}/agregar/cupcake", produces="application/json")
	public OrdenBean agregarCupcake(@PathVariable("idOrden") Integer idOrden, @RequestParam Integer idCupcake, @RequestParam(defaultValue="1") Integer multiplicador) {
		try {
			OrdenBean orden = ordenRepository.buscarPorId(idOrden);
			CupcakeBean cupcake = cupcakeRepository.buscarPorId(idCupcake);
			ordenCupcakesService.agregarCupcake(orden, cupcake, multiplicador);
			OrdenBean ordenFinal = ordenRepository.buscarPorId(idOrden);
			return ordenFinal;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
