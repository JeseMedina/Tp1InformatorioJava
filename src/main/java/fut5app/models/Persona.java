/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.models;

import fut5app.services.Utils;

/**
 *
 * @author Jesé
 */
public class Persona {

    private String nombre;
    private String apellido;

    Utils utils = new Utils();
    
    public Persona(String nombre, String apellido) {
        this.nombre = utils.capitalizeWords(nombre);
        this.apellido = utils.capitalizeWords(apellido);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
