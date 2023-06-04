/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.services;

import fut5app.models.Equipo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jesé
 */
public class Fut5App {

    private List<Equipo> equipos;
    private Scanner scanner;
    private GestorArchivos gestorArchivos;
    private EquipoService equipoService;
    private JugadorService jugadorService;

    public Fut5App() {
        equipos = new ArrayList<>();
        scanner = new Scanner(System.in);
        gestorArchivos = new GestorArchivos();
        equipoService = new EquipoService();
        jugadorService = new JugadorService();
    }

    private void mostrarMenu() {
        System.out.println("Ingrese la opción deseada:");
        System.out.println("1. Crear equipo");
        System.out.println("2. Buscar jugador por nombre");
        System.out.println("3. Buscar equipo por nombre (información básica)");
        System.out.println("4. Buscar equipo por nombre (mostrar jugadores)");
        System.out.println("5. Eliminar equipo por nombre");
        System.out.println("6. Buscar equipo por nombre (mostrar jugadores ordenados por nombre)");
        System.out.println("7. Buscar equipo por nombre (mostrar jugadores ordenados por número de camiseta)");
        System.out.println("8. Buscar equipo por nombre (mostrar jugadores ordenados por posición y número de camiseta)");
        System.out.println("9. Importar jugadores desde archivo");
        System.out.println("10. Exportar jugadores a archivo");
        System.out.println("0. Salir");
        System.out.println("Ingrese su opción: ");
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Equipo newEquipo = equipoService.crearEquipo();
                    equipos.add(newEquipo);
                    break;
                case 2:
                    jugadorService.buscarJugadorPorNombre(equipos);
                    break;
                case 3:
                    equipoService.buscarEquipoPorNombreInformacionBasica(equipos);
                    break;
                case 4:
                    equipoService.buscarEquipoPorNombreMostrarJugadores(equipos);
                    break;
                case 5:
                    equipoService.eliminarEquipoPorNombre(equipos);
                    break;
                case 6:
                    equipoService.buscarEquipoPorNombreMostrarJugadoresOrdenadosPorNombre(equipos);
                    break;
                case 7:
                    equipoService.buscarEquipoPorNombreMostrarJugadoresOrdenadosPorCamiseta(equipos);
                    break;
                case 8:
                    equipoService.buscarEquipoPorNombreMostrarJugadoresOrdenadosPorPosicionYCamiseta(equipos);
                    break;
                case 9:
                    gestorArchivos.importarJugadoresDesdeArchivo(equipos);
                    break;
                case 10:
                    gestorArchivos.exportarJugadoresAArchivo(equipos);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }
}
