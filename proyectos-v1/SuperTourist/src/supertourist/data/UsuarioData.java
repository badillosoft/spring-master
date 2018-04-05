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
public class UsuarioData {
    private int id;
    private String name;
    private String email;

    public UsuarioData(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UsuarioData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
