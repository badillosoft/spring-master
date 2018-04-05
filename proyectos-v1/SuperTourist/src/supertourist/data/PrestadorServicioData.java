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
public class PrestadorServicioData {
    private int id;
    private String name;
    private String service;

    public PrestadorServicioData(int id, String name, String service) {
        this.id = id;
        this.name = name;
        this.service = service;
    }

    public PrestadorServicioData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getService() {
        return service;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setService(String service) {
        this.service = service;
    }
    
    
}
