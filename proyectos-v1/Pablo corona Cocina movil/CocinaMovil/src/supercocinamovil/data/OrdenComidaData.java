
package supercocinamovil.data;

import java.util.List;

/**
 *
 * @author Pablo
 */
public class OrdenComidaData {
    
    String id;
    ClienteData cliente;
    StatusData registro;
    PagoData pago;
    CobroData cobro;
    List<MenuData> menus;
    CocinaData cocina;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public PagoData getPago() {
        return pago;
    }

    public void setPago(PagoData pago) {
        this.pago = pago;
    }

    public CobroData getCobro() {
        return cobro;
    }

    public void setCobro(CobroData cobro) {
        this.cobro = cobro;
    }

   public List<MenuData> getMenu() {
        return menus;
    }

    public void setMenu(List<MenuData> menu) {
        this.menus = menu;
    }

    public CocinaData getCocina() {
        return cocina;
    }

    public void setCocina(CocinaData cocina) {
        this.cocina = cocina;
    }  
    
}
