package ejercicio_02;

public class LoginService {
    
    DataBaseService db;
    
    public LoginService(DataBaseService db) {
        this.db = db;
    }
    
    public boolean login(UserLoginData userLoginData) {
        
        if (userLoginData == null) {
            System.out.println("advertencia: los datos de ingreso son nulos");
            return false;
        }
        
        UserData userData = this.db.loginUser(userLoginData);
        
        if (userData == null) {
            return false;
        }
        
        userData.setToken("ABC123");
        
        return this.db.updateTokenUser(userData);
        
    }
    
}
