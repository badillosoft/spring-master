package ejercicio_02;

import java.util.Scanner;

public class Program {
    
    public static void main(String[] args) {
        
        DataBaseService db = new DataBaseService();
        
        LoginService loginService = new LoginService(db);
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Id Usuario: ");
        while(sc.hasNextLine()) {
            String id = sc.nextLine();
            System.out.print("Clave Usuario: ");
            if (sc.hasNextLine()) {
                String clave = sc.nextLine();
                
                UserLoginData userLoginData = new UserData(id, clave);
                
                if (loginService.login(userLoginData)) {
                    System.out.println("ACCESO VÁLIDO");
                } else {
                    System.out.println("ACCESO NO VÁLIDO");
                }
            }
            
            System.out.print("ID Usuario: ");
        }
    }
    
}
