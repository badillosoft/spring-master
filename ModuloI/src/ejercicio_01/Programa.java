package ejercicio_01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Programa {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        ArrayList<Persona> personas = new ArrayList<>();
        
        FileInputStream in = new FileInputStream("/Users/alan/Desktop/personas.txt");
        
        Scanner sc = new Scanner(in);
        
        while (sc.hasNextLine()) {
            String nombre = sc.nextLine();
            int edad = sc.nextInt();
            double estatura = sc.nextDouble();
            double peso = sc.nextDouble();
            
            Persona p = new Persona(nombre, edad, estatura, peso);
            
            if (p.esMayorA(18)) {
                personas.add(p);
            }
            
            sc.nextLine();
        }
        
        for (Persona p : personas) {
            System.out.println(p);
        }
        
    }
    
}
