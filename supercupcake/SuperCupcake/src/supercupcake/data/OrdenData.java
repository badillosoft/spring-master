package supercupcake.data;

import java.util.List;

public class OrdenData {
    
    int id;
    ClienteData cliente;
    VendedorData vendedor;
    CocinaData cocina;
    List<CupcakeData> cupcakes;
    EstatusData estatus;
    CobroData cobro;
    PagoData pago;

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

    public VendedorData getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorData vendedor) {
        this.vendedor = vendedor;
    }

    public CocinaData getCocina() {
        return cocina;
    }

    public void setCocina(CocinaData cocina) {
        this.cocina = cocina;
    }

    public List<CupcakeData> getCupcakes() {
        return cupcakes;
    }

    public void setCupcakes(List<CupcakeData> cupcakes) {
        this.cupcakes = cupcakes;
    }

    public EstatusData getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusData estatus) {
        this.estatus = estatus;
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
    
}
