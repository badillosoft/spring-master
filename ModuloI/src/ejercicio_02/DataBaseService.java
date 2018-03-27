package ejercicio_02;

public class DataBaseService {
    
    public UserData loginUser(UserLoginData userLoginData) {
        if (userLoginData.getId().equals("batman") && userLoginData.getClave().equals("robin")) {
            return new UserData("batman", "robin", "ABC123");
        }
        return null;
    }
    
    public boolean updateTokenUser(UserData userData) {
        return true;
    }
}
