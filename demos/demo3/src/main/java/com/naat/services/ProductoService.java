package com.naat.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naat.beans.ProductoBean;
import com.naat.repositories.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;
	
	public ProductoBean crearProducto(String nombre, String sku) {
		ProductoBean producto = new ProductoBean();
		producto.setNombre(nombre);
		producto.setSku(sku);
		
		try {
			productoRepository.insertar(producto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return producto;
	}
}
