
package RentaLibros.data;

import java.util.Date;

/**
 *
 * @author zaira
 */
public class MembresiaData {
    int id;
    int idCliente;
    String vigencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    } 
}
