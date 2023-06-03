/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.models;

import java.util.UUID;

/**
 *
 * @author Jesé
 */
public class Jugador {

    private String id;
    private String nombre;
    private String apellido;
    private int altura;
    private String posicion;
    private int goles;
    private int partidos;
    private boolean capitan;
    private int numeroCamiseta;
    private Equipo equipo;

    public Jugador(String nombre, String apellido, int altura,String posicion, int goles, int partidos, boolean capitan, int numeroCamiseta, Equipo equipo) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellido = apellido;
        this.altura = altura;
        this.posicion = posicion;
        this.goles = goles;
        this.partidos = partidos;
        this.capitan = capitan;
        this.numeroCamiseta = numeroCamiseta;
        this.equipo = equipo;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
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

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
