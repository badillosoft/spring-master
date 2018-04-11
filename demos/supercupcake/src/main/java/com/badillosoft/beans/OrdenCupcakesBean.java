package com.badillosoft.beans;

public class OrdenCupcakesBean {

	Integer id;
	OrdenBean orden;
	CupcakeBean cupcake;
    Integer multiplicador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrdenBean getOrden() {
		return orden;
	}

	public void setOrden(OrdenBean orden) {
		this.orden = orden;
	}

	public CupcakeBean getCupcake() {
		return cupcake;
	}

	public void setCupcake(CupcakeBean cupcake) {
		this.cupcake = cupcake;
	}

	public Integer getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(Integer multiplicador) {
        this.multiplicador = multiplicador;
    }
	
}
