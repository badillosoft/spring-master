package com.naat.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class ClienteBean {

	Integer id;
	String nombre;
	@Autowired
	DireccionBean direccion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public DireccionBean getDireccion() {
		return direccion;
	}
	public void setDireccion(DireccionBean direccion) {
		this.direccion = direccion;
	}
	
	
}
