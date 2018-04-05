/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superjuices.data;

import java.util.List;

/**
 *
 * @author alejandro
 */
public class OrdenData {
    
    int id;
    ClienteData cliente;
    SucursalData sucursal;
    RepartidorData repartidor;
    List<JugoData> jugos;
    CobroData cobro;
    PagoData pago;
    EstatusData estatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClienteData getCliente() {
        return cliente;
    }

    public void setCliente(ClienteData cliente) {
        this.cliente = cliente;
    }

    public SucursalData getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalData sucursal) {
        this.sucursal = sucursal;
    }

    public RepartidorData getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(RepartidorData repartidor) {
        this.repartidor = repartidor;
    }

    public List<JugoData> getJugos() {
        return jugos;
    }

    public void setJugos(List<JugoData> jugos) {
        this.jugos = jugos;
    }

    public CobroData getCobro() {
        return cobro;
    }

    public void setCobro(CobroData cobro) {
        this.cobro = cobro;
    }

    public PagoData getPago() {
        return pago;
    }

    public void setPago(PagoData pago) {
        this.pago = pago;
    }

    public EstatusData getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusData estatus) {
        this.estatus = estatus;
    }
    
}
