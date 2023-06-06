/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.services.interfaces;

import fut5app.models.Equipo;

import java.util.List;

/**
 *
 * @author Jesé
 */
public interface IEquipoService {

    Equipo crearEquipo();

    void buscarEquipoPorNombreInformacionBasica(List<Equipo> equipos);

    void buscarEquipoPorNombreMostrarJugadores(List<Equipo> equipos);

    void eliminarEquipoPorNombre(List<Equipo> equipos);

    void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorNombre(List<Equipo> equipos);

    void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorCamiseta(List<Equipo> equipos);

    void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorPosicionYCamiseta(List<Equipo> equipos);

}
