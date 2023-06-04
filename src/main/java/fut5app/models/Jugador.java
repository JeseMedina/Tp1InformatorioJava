/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.models;

import java.util.UUID;
import fut5app.utils.Utils;

/**
 *
 * @author Jesé
 */
public class Jugador extends Persona {

    private String id;
    private int altura;
    private String posicion;
    private int goles;
    private int partidos;
    private boolean capitan;
    private int numeroCamiseta;
    private Equipo equipo;
    
    Utils utils = new Utils();
    
    public Jugador(String nombre, String apellido, int altura, String posicion, int goles, int partidos, boolean capitan, int numeroCamiseta, Equipo equipo) {
        super(nombre, apellido);
        this.id = UUID.randomUUID().toString();
        this.altura = altura;
        this.posicion = utils.capitalizeWords(posicion);
        this.goles = goles;
        this.partidos = partidos;
        this.capitan = capitan;
        this.numeroCamiseta = numeroCamiseta;
        this.equipo = equipo;
    }

    public String getId() {
        return id;
    }

    public int getAltura() {
        return altura;
    }

    public String getPosicion() {
        return posicion;
    }

    public int getGoles() {
        return goles;
    }

    public int getPartidos() {
        return partidos;
    }

    public boolean isCapitan() {
        return capitan;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public String getInfoLine() {
        return getNombre() + " " + getApellido() + ", " + getPosicion() + "(" + getNumeroCamiseta() + ") " + (isCapitan() ? "C" : "");
    }

    public String getLine() {
        return getNombre() + "," + getApellido() + "," + getAltura() + "," + getPosicion() + "," + getGoles() + "," + getPartidos() + "," + isCapitan() + "," + getNumeroCamiseta() + "," + equipo.getNombre() + "," + equipo.getFechaCreacion() + "," + equipo.getEntrenadorLine();
    }

    public String getInfo() {
        String info = ("Nombre: " + getNombre() + "\n"
                + "Apellido: " + getApellido() + "\n"
                + "Posición: " + getPosicion() + "\n"
                + "Capitán: " + (isCapitan() ? "Capitan" : "") + "\n"
                + "Equipo: " + equipo.getNombre());
        return info;
    }
}
