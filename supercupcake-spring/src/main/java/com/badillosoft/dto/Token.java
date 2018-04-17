package com.badillosoft.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Token {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	String value;
	
	Timestamp creado;
	
	Timestamp expira;
	
	@Column(length=10)
	String rol;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Timestamp getCreado() {
		return creado;
	}

	public void setCreado(Timestamp creado) {
		this.creado = creado;
	}

	public Timestamp getExpira() {
		return expira;
	}

	public void setExpira(Timestamp expira) {
		this.expira = expira;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
