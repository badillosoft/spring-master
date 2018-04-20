package com.badillosoft.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.badillosoft.dao.ClienteDao;
import com.badillosoft.dao.CupcakeDao;
import com.badillosoft.dao.OrdenDao;
import com.badillosoft.dto.Cliente;
import com.badillosoft.dto.Cupcake;
import com.badillosoft.dto.Orden;
import com.badillosoft.services.OrdenService;

@RestController
@RequestMapping("/orden")
public class OrdenApi {

	@Autowired
	OrdenService ordenService;
	
	@Autowired
	OrdenDao ordenDao;
	
	@Autowired
	ClienteDao clienteDao;
	
	@Autowired
	CupcakeDao cupcakeDao;
	
	public void cors(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type, Content-Range, Content-Disposition, Content-Description");
	}
	
	@RequestMapping("/crear")
	@ResponseBody
	public Orden crearOrden(HttpServletResponse response) {
		cors(response);
		
		return ordenService.crearOrden();
	}
	
	@RequestMapping("")
	@ResponseBody
	public Iterable<Orden> verOrdenes(HttpServletResponse response) {
		cors(response);
		
		Iterable<Orden> ordenes = ordenDao.findAll();
		
		return ordenes;
	}
	
	@RequestMapping("/{id}/asignar/cliente")
	@ResponseBody
	public Orden asignarCliente(@PathVariable("id") Long idOrden, 
			@RequestParam(name="id") Long idCliente,
			HttpServletResponse response) {
		cors(response);
		
		Optional<Orden> ordenOpt = ordenDao.findById(idOrden);
		
		if (!ordenOpt.isPresent()) {
			return null;
		}
		
		Optional<Cliente> clienteOpt = clienteDao.findById(idCliente);
		
		if (!clienteOpt.isPresent()) {
			return null;
		}
		
		return ordenService.asignarCliente(ordenOpt.get(), clienteOpt.get());
	}
	
	@RequestMapping(value="/{id}/agregar/cupcake", method=RequestMethod.POST)
	@ResponseBody
	public Orden agregarCupcake(@PathVariable("id") Long idOrden, @RequestParam(name="id") Long idCupcake) {
		Optional<Orden> ordenOpt = ordenDao.findById(idOrden);
		
		if (!ordenOpt.isPresent()) {
			return null;
		}
		
		Optional<Cupcake> cupcakeOpt = cupcakeDao.findById(idCupcake);
		
		if (!cupcakeOpt.isPresent()) {
			return null;
		}
		
		return ordenService.agregarCupcake(ordenOpt.get(), cupcakeOpt.get());
	}
	
	@RequestMapping("/{id}/total")
	@ResponseBody
	public Double agregarCupcake(@PathVariable("id") Long idOrden) {
		return ordenDao.calcularTotal(idOrden);
	}
	
	@RequestMapping("/{id}/cupcakes")
	@ResponseBody
	public List<Cupcake> obtenerCupcakes(@PathVariable("id") Long idOrden, @RequestParam(name="precioMayor", defaultValue="0.0") Double precioMayor) {
		return ordenDao.obtenerCupcakes(idOrden, precioMayor);
	}
	
}
