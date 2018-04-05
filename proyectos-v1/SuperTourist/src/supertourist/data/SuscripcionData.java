/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supertourist.data;

/**
 *
 * @author diego
 */
public class SuscripcionData {
    private int id;
    private int usuario;

    public SuscripcionData(int id, int usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public SuscripcionData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
    
}
