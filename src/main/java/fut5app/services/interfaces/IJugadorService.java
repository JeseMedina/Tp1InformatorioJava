/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.services.interfaces;

import fut5app.models.Equipo;
import fut5app.models.Jugador;
import java.util.List;

/**
 *
 * @author Jesé
 */
public interface IJugadorService {

    Jugador crearJugador(Equipo equipo);

    void buscarJugadorPorNombre(List<Equipo> equipos);
}
