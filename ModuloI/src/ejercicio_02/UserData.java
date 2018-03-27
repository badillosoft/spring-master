package ejercicio_02;

public class UserData implements UserLoginData {
    
    String id;
    String clave;
    String token;
    
    public UserData(String id, String clave) {
        this.id = id;
        this.clave = clave;
    }
    
    public UserData(String id, String clave, String token) {
        this.id = id;
        this.clave = clave;
        this.token = token;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
