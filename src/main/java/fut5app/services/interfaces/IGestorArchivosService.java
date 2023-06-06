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
public interface IGestorArchivosService {

    void importarJugadoresDesdeArchivo(List<Equipo> equipo);

    void exportarJugadoresAArchivo(List<Equipo> equipo);

}
