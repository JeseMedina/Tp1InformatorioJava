/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.models;

import java.util.ArrayList;
import java.util.List;
import fut5app.utils.Utils;

/**
 *
 * @author Jesé
 */
public class Equipo {

    private String nombre;
    private String fechaCreacion;
    private Entrenador entrenador;
    private List<Jugador> jugadores;
    
    Utils utils = new Utils();

    public Equipo(String nombre, String fechaCreacion, Entrenador entrenador) {
        this.nombre = utils.capitalizeWords(nombre);
        this.fechaCreacion = fechaCreacion;
        this.entrenador = entrenador;
        this.jugadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador getCapitan() {
        for (Jugador jugador : jugadores) {
            if (jugador.isCapitan()) {
                return jugador;
            }
        }
        return null;
    }

    public void agregarJugador(Jugador jugador) {
        if (jugadores == null) {
            jugadores = new ArrayList<>();
        }
        jugadores.add(jugador);
    }

    public String getEntrenadorLine() {
        return entrenador.getInfoLine();
    }

    public boolean existeNumeroCamiseta(int numeroCamiseta) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNumeroCamiseta() == numeroCamiseta) {
                return true;
            }
        }
        return false;
    }

    public boolean existeCapitan() {
        for (Jugador jugador : jugadores) {
            if (jugador.isCapitan()) {
                return true;
            }
        }
        return false;
    }
}
