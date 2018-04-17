package com.badillosoft.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Orden {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@OneToOne
	Cliente cliente;
	
	@OneToOne
	Vendedor vendedor;
	
	@OneToOne
	Cocina cocina;
	
	@OneToOne
	Estatus estatus;
	
	@OneToOne
	Cobro cobro;
	
	@OneToOne
	Pago pago;
	
	@ManyToMany
	List<Cupcake> cupcakes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cocina getCocina() {
		return cocina;
	}

	public void setCocina(Cocina cocina) {
		this.cocina = cocina;
	}

	public Estatus getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public List<Cupcake> getCupcakes() {
		return cupcakes;
	}

	public void setCupcakes(List<Cupcake> cupcakes) {
		this.cupcakes = cupcakes;
	}
	
}
