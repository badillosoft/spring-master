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
public class AlmacenData {
    private int id;
    private String sucursal;
    private String address;

    public AlmacenData(int id, String sucursal, String address) {
        this.id = id;
        this.sucursal = sucursal;
        this.address = address;
    }

    public AlmacenData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getSucursal() {
        return sucursal;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
     
}
