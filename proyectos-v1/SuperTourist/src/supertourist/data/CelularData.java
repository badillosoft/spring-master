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
public class CelularData {
    private int id;
    private String model;

    public CelularData(int id, String model) {
        this.id = id;
        this.model = model;
    }

    public CelularData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    
}
