/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.services;

import fut5app.models.Entrenador;
import fut5app.models.Equipo;
import fut5app.models.Jugador;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jesé
 */
public class EquipoService {

    Scanner scanner = new Scanner(System.in);
    EntrenadorService entrenadorService = new EntrenadorService();
    JugadorService jugadorService = new JugadorService();

    protected Equipo crearEquipo() {
        String nombreEquipo = obtenerNombreEquipo();
        String fechaCreacion = obtenerFechaCreacion();
        System.out.println("----- ENTRENADOR -----");
        Entrenador entrenador = entrenadorService.crearEntranador();
        System.out.println("----- JUGADORES -----");
        Equipo equipo = new Equipo(nombreEquipo, fechaCreacion, entrenador);
        crearJugadores(equipo);
        System.out.println("Equipo creado exitosamente.");
        return equipo;
    }

    private String obtenerNombreEquipo() {
        String nombreEquipo;
        while (true) {
            System.out.println("Ingrese el nombre del equipo: ");
            String inputNombre = scanner.nextLine();
            if (!inputNombre.isEmpty()) {
                nombreEquipo = inputNombre;
                break;
            }
            System.out.println("El nombre del equipo no puede estar vacío. Por favor, ingrese un nombre válido.");
        }
        return nombreEquipo;
    }

    private String obtenerFechaCreacion() {
        String fechaCreacion;
        while (true) {
            System.out.println("Ingrese la fecha de creación del equipo (dd/mm/aaaa): ");
            String inputFecha = scanner.nextLine();
            if (!inputFecha.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);
                try {
                    dateFormat.parse(inputFecha);
                    fechaCreacion = inputFecha;
                    break;
                } catch (ParseException e) {
                    System.out.println("Fecha de creación inválida. Por favor, ingrese una fecha en el formato dd/mm/aaaa: ");
                    e.printStackTrace();
                }
            } else {
                System.out.println("La fecha de creación no puede estar vacía. Por favor, ingrese una fecha válida: ");
            }
        }
        return fechaCreacion;
    }

    private void crearJugadores(Equipo equipo) {
        boolean opcionJugador;
        do {
            Jugador jugador = jugadorService.crearJugador(equipo);
            equipo.agregarJugador(jugador);
            System.out.println("¿Desea agregar otro jugador? (s/n)): ");
            opcionJugador = scanner.nextLine().equalsIgnoreCase("s");
        } while (opcionJugador);
    }

    protected void buscarEquipoPorNombreInformacionBasica(List<Equipo> equipos) {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (INFORMACIÓN BÁSICA) -----");

        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese el nombre del equipo: ");
            String nombreEquipo = scanner.nextLine();
            if (!nombreEquipo.isEmpty()) {
                for (Equipo equipo : equipos) {
                    if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                        System.out.println("Nombre: " + equipo.getNombre());
                        System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                        System.out.println("Capitán: " + equipo.getCapitan().getNombreCompleto());
                        flag = false;
                        return;
                    }
                }
                System.out.println("No se encontró ningún equipo con ese nombre.");
                flag = false;
                return;
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    protected void buscarEquipoPorNombreMostrarJugadores(List<Equipo> equipos) {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES) -----");

        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese el nombre del equipo: ");
            String nombreEquipo = scanner.nextLine();
            if (!nombreEquipo.isEmpty()) {
                for (Equipo equipo : equipos) {
                    if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                        System.out.println("Nombre: " + equipo.getNombre());
                        System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                        System.out.println("Jugadores:");
                        for (Jugador jugador : equipo.getJugadores()) {
                            System.out.println("- " + jugador.getInfoLine());
                        }
                        flag = false;
                        return;
                    }
                }
                System.out.println("No se encontró ningún equipo con ese nombre.");
                flag = false;
                return;
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    protected void eliminarEquipoPorNombre(List<Equipo> equipos) {
        System.out.println("----- ELIMINAR EQUIPO POR NOMBRE -----");

        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese el nombre del equipo a eliminar: ");
            String nombreEquipo = scanner.nextLine();
            if (!nombreEquipo.isEmpty()) {
                for (int i = 0; i < equipos.size(); i++) {
                    if (equipos.get(i).getNombre().equalsIgnoreCase(nombreEquipo)) {
                        equipos.remove(i);
                        System.out.println("Equipo eliminado exitosamente.");
                        flag = false;
                        return;
                    }
                }
                System.out.println("No se encontró ningún equipo con ese nombre.");
                flag = false;
                return;
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    protected void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorNombre(List<Equipo> equipos) {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES ORDENADOS POR NOMBRE) -----");
        buscarEquipoPorNombreMostrarJugadoresOrdenados(equipos, Comparator.comparing(Jugador::getNombre));
    }

    protected void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorCamiseta(List<Equipo> equipos) {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES ORDENADOS POR NÚMERO DE CAMISETA) -----");
        buscarEquipoPorNombreMostrarJugadoresOrdenados(equipos, Comparator.comparingInt(Jugador::getNumeroCamiseta));
    }

    protected void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorPosicionYCamiseta(List<Equipo> equipos) {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES ORDENADOS POR POSICIÓN Y NÚMERO DE CAMISETA) -----");
        buscarEquipoPorNombreMostrarJugadoresOrdenados(equipos, Comparator.comparing(Jugador::getPosicion).thenComparingInt(Jugador::getNumeroCamiseta));
    }

    private void buscarEquipoPorNombreMostrarJugadoresOrdenados(List<Equipo> equipos, Comparator<Jugador> comparator) {
        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese el nombre del equipo: ");
            String nombreEquipo = scanner.nextLine();
            if (!nombreEquipo.isEmpty()) {
                for (Equipo equipo : equipos) {
                    if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                        System.out.println("Nombre: " + equipo.getNombre());
                        System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                        System.out.println("Jugadores:");
                        List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                        jugadoresOrdenados.sort(comparator);
                        for (Jugador jugador : jugadoresOrdenados) {
                            System.out.println("- " + jugador.getInfoLine());
                        }
                        flag = false;
                        return;
                    }
                }
                System.out.println("No se encontró ningún equipo con ese nombre.");
                flag = false;
                return;
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }
}
