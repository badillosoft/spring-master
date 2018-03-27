package ejercicio_01;

public class Persona {
    
    String nombre;
    int edad;
    double estatura;
    double peso;
    
    public Persona(String nombre, int edad, double estatura, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.estatura = estatura;
        this.peso = peso;
    }
    
    public boolean esMayorA(int edad) {
        return this.edad >= edad;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%f / %f) [%d]", this.nombre, this.estatura, this.peso, this.edad);
    }
    
}
