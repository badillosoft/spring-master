package com.badillosoft.beans;

import java.util.List;

public class OrdenBean {

	Integer id;
    ClienteBean cliente;
    VendedorBean vendedor;
    CocinaBean cocina;
    List<CupcakeBean> cupcakes;
    EstatusBean estatus;
    CobroBean cobro;
    PagoBean pago;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public VendedorBean getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorBean vendedor) {
        this.vendedor = vendedor;
    }

    public CocinaBean getCocina() {
        return cocina;
    }

    public void setCocina(CocinaBean cocina) {
        this.cocina = cocina;
    }

    public List<CupcakeBean> getCupcakes() {
        return cupcakes;
    }

    public void setCupcakes(List<CupcakeBean> cupcakes) {
        this.cupcakes = cupcakes;
    }

    public EstatusBean getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusBean estatus) {
        this.estatus = estatus;
    }
    
    public CobroBean getCobro() {
        return cobro;
    }

    public void setCobro(CobroBean cobro) {
        this.cobro = cobro;
    }

    public PagoBean getPago() {
        return pago;
    }

    public void setPago(PagoBean pago) {
        this.pago = pago;
    }
	
}
