/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.models;

/**
 *
 * @author Jesé
 */
public class Entrenador extends Persona {

    private int edad;

    public Entrenador(String nombre, String apellido, int edad) {
        super(nombre, apellido);
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }
    
    public String getInfoLine(){
        return getNombre() + "," + getApellido() + "," + getEdad();
    }
}
