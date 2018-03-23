class Persona {
    String nombre;
    int edad;
    String genero;

    public String saludar() {
        return String.format("Hola me llamo %s", this.nombre);
    }

    public boolean esMayorEdad() {
        return this.edad >= 18;
    }

    public boolean esAdultoMayor() {
        return this.edad >= 65;
    }

    public static void main(String[] args) {
        // Creamos dos instancias de la clase Persona (personaA, personaB)
        Persona personaA = new Persona();
        Persona personaB = new Persona();

        // Ajustamos los atributos de la personaB
        personaB.nombre = "Pepe";
        personaB.edad = 63;
        personaB.genero = "Hombre";

        // Imprimimos el resultado de la llamada a los métodos
        
        System.out.println("Persona A");
        System.out.println(String.format("Saludo: %s", personaA.saludar()));
        System.out.println(String.format("Mayor de Edad: %s", personaA.esMayorEdad()));
        System.out.println(String.format("Adulto Mayor: %s", personaA.esAdultoMayor()));
        
        System.out.println("Persona B");
        System.out.println(String.format("Saludo: %s", personaB.saludar()));
        System.out.println(String.format("Mayor de Edad: %s", personaB.esMayorEdad()));
        System.out.println(String.format("Adulto Mayor: %s", personaB.esAdultoMayor()));
    }
}