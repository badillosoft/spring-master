/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaadomicilio.data;

/**
 *
 * @author Pablo
 */
public class OrdenEventoData {
    
    int id;
    ClienteData cliente;
    StatusData registro;
    OradorData orador;
    CobroData cobro;
    PagoPlataformaData pago_plataforma;
    PagoOradorData pago_orador;

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

    public StatusData getRegistro() {
        return registro;
    }

    public void setRegistro(StatusData registro) {
        this.registro = registro;
    }

    public OradorData getOrador() {
        return orador;
    }

    public void setOrador(OradorData orador) {
        this.orador = orador;
    }

    public CobroData getCobro() {
        return cobro;
    }

    public void setCobro(CobroData cobro) {
        this.cobro = cobro;
    }

    public PagoPlataformaData getPago_plataforma() {
        return pago_plataforma;
    }

    public void setPago_plataforma(PagoPlataformaData pago_plataforma) {
        this.pago_plataforma = pago_plataforma;
    }

    public PagoOradorData getPago_orador() {
        return pago_orador;
    }

    public void setPago_orador(PagoOradorData pago_orador) {
        this.pago_orador = pago_orador;
    }  
    
}
