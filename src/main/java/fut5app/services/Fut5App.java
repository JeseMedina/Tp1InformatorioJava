/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.services;

import fut5app.models.Entrenador;
import fut5app.models.Equipo;
import fut5app.models.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jesé
 */
public class Fut5App {

    private List<Equipo> equipos;
    Scanner scanner = new Scanner(System.in);

    public Fut5App() {
        equipos = new ArrayList<>();
    }

    private void mostrarMenu() {
        System.out.println("Ingrese la opción deseada:");
        System.out.println("1. Crear equipo");
        System.out.println("2. Buscar jugador por nombre");
        System.out.println("3. Buscar equipo por nombre (información básica)");
        System.out.println("4. Buscar equipo por nombre (mostrar jugadores)");
        System.out.println("5. Eliminar equipo por nombre");
        System.out.println("6. Importar jugadores desde archivo");
        System.out.println("7. Exportar jugadores a archivo");
        System.out.println("8. Buscar equipo por nombre (mostrar jugadores ordenados por nombre)");
        System.out.println("9. Buscar equipo por nombre (mostrar jugadores ordenados por número de camiseta)");
        System.out.println("10. Buscar equipo por nombre (mostrar jugadores ordenados por posición y número de camiseta)");
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
                    crearEquipo();
                    break;
                case 2:
                    buscarJugadorPorNombre();
                    break;
                case 3:
                    buscarEquipoPorNombreInformacionBasica();
                    break;
                case 4:
                    buscarEquipoPorNombreMostrarJugadores();
                    break;
                case 5:
                    eliminarEquipoPorNombre();
                    break;
                case 6:
                    importarJugadoresDesdeArchivo();
                    break;
                case 7:
                    exportarJugadoresAArchivo();
                    break;
                case 8:
                    buscarEquipoPorNombreMostrarJugadoresOrdenadosPorNombre();
                    break;
                case 9:
                    buscarEquipoPorNombreMostrarJugadoresOrdenadosPorCamiseta();
                    break;
                case 10:
                    buscarEquipoPorNombreMostrarJugadoresOrdenadosPorPosicionYCamiseta();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }

    private void crearEquipo() {
        System.out.println("----- CREAR EQUIPO -----");
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();
        System.out.println("Ingrese la fecha de creación del equipo: ");
        String fechaCreacion = scanner.nextLine();

        System.out.println("----- ENTRENADOR -----");
        Entrenador entrenador = crearEntranador();
        Equipo equipo = new Equipo(nombreEquipo, fechaCreacion, entrenador);

        System.out.println("----- JUGADORES -----");
        boolean opcionJugador;
        do {

            Jugador jugador = crearJugador(equipo);
            equipo.agregarJugador(jugador);

            System.out.println("¿Desea agregar otro jugador? (s/n)): ");
            opcionJugador = scanner.nextLine().equalsIgnoreCase("s");
        } while (opcionJugador);

        equipos.add(equipo);

        System.out.println("Equipo creado exitosamente.");
    }

    private Entrenador crearEntranador() {
        System.out.println("Ingrese el nombre del entrenador: ");
        String nombreEntrenador = scanner.nextLine();
        System.out.println("Ingrese el apellido del entrenador: ");
        String apellidoEntrenador = scanner.nextLine();
        System.out.println("Ingrese la edad del entrenador: ");
        int edadEntrenador = scanner.nextInt();
        scanner.nextLine();

        Entrenador entrenador = new Entrenador(nombreEntrenador, apellidoEntrenador, edadEntrenador);
        return entrenador;
    }

    private Jugador crearJugador(Equipo equipo) {
        System.out.println("Ingrese el nombre del jugador: ");
        String nombreJugador = scanner.nextLine();
        System.out.println("Ingrese el apellido del jugador: ");
        String apellidoJugador = scanner.nextLine();
        System.out.println("Ingrese la altura del jugador (Cm): ");
        int alturaJugador = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la posición del jugador (Arquero, Defensor, Mediocampista, Delantero): ");
        String posicionJugador = scanner.nextLine();
        System.out.println("Ingrese la cantidad de goles del jugador: ");
        int golesJugador = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la cantidad de partidos del jugador: ");
        int partidosJugador = scanner.nextInt();
        scanner.nextLine();
        System.out.println("¿El jugador es capitán? (s/n): ");
        boolean esCapitan = scanner.nextLine().equalsIgnoreCase("s");
        System.out.println("Ingrese el número de camiseta del jugador: ");
        int numeroCamiseta = scanner.nextInt();
        scanner.nextLine();

        Jugador jugador = new Jugador(nombreJugador, apellidoJugador, alturaJugador, posicionJugador, golesJugador, partidosJugador, esCapitan, numeroCamiseta, equipo);
        return jugador;
    }

    private void buscarJugadorPorNombre() {
        System.out.println("----- BUSCAR JUGADOR POR NOMBRE -----");
        System.out.println("Ingrese el nombre del jugador: ");
        String nombreJugador = scanner.nextLine();

        for (Equipo equipo : equipos) {
            for (Jugador jugador : equipo.getJugadores()) {
                if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                    System.out.println("Nombre: " + jugador.getNombre());
                    System.out.println("Apellido: " + jugador.getApellido());
                    System.out.println("Posición: " + jugador.getPosicion());
                    System.out.println("Capitán: " + (jugador.isCapitan() ? "Sí" : "No"));
                    System.out.println("Equipo: " + equipo.getNombre());
                    return;
                }
            }
        }

        System.out.println("No se encontró ningún jugador con ese nombre.");
    }

    private void buscarEquipoPorNombreInformacionBasica() {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (INFORMACIÓN BÁSICA) -----");
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre: " + equipo.getNombre());
                System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Capitán: " + equipo.getCapitan().getNombreCompleto());
                return;
            }
        }

        System.out.println("No se encontró ningún equipo con ese nombre.");
    }

    private void buscarEquipoPorNombreMostrarJugadores() {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES) -----");
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre: " + equipo.getNombre());
                System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Jugadores:");
                for (Jugador jugador : equipo.getJugadores()) {
                    System.out.println("- " + jugador.getNombreCompleto());
                }
                return;
            }
        }

        System.out.println("No se encontró ningún equipo con ese nombre.");
    }

    private void eliminarEquipoPorNombre() {
        System.out.println("----- ELIMINAR EQUIPO POR NOMBRE -----");
        System.out.println("Ingrese el nombre del equipo a eliminar: ");
        String nombreEquipo = scanner.nextLine();

        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getNombre().equalsIgnoreCase(nombreEquipo)) {
                equipos.remove(i);
                System.out.println("Equipo eliminado exitosamente.");
                return;
            }
        }

        System.out.println("No se encontró ningún equipo con ese nombre.");
    }

    private void importarJugadoresDesdeArchivo() {
        System.out.println("----- IMPORTAR JUGADORES DESDE ARCHIVO -----");
        System.out.println("Ingrese la ruta del archivo (Ej. E:\\datos\\Desktop\\Fut5App.txt): ");
        String rutaArchivo = scanner.nextLine();

        Map<String, Equipo> equiposDiccionario = new HashMap<>();
        for (Equipo equipo : equipos) {
            equiposDiccionario.put(equipo.getNombre(), equipo);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datosJugador = linea.split(",");
                String nombre = datosJugador[0];
                String apellido = datosJugador[1];
                int altura = Integer.parseInt(datosJugador[2]);
                String posicion = datosJugador[3];
                int goles = Integer.parseInt(datosJugador[4]);
                int partidos = Integer.parseInt(datosJugador[5]);
                boolean esCapitan = Boolean.parseBoolean(datosJugador[6]);
                int numero = Integer.parseInt(datosJugador[7]);
                String nombreEquipo = datosJugador[8];

                Equipo equipo = equiposDiccionario.get(nombreEquipo);
                if (equipo != null) {
                    Jugador jugador = new Jugador(nombre, apellido, altura, posicion, goles, partidos, esCapitan, numero, equipo);
                    equipo.agregarJugador(jugador);
                } else {
                    System.out.println("No se encontró el equipo con el nombre: " + nombreEquipo);
                }
            }
            System.out.println("Jugadores importados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al importar jugadores desde el archivo.");
            e.printStackTrace();
        }
    }

    private void exportarJugadoresAArchivo() {
        System.out.println("----- EXPORTAR JUGADORES A ARCHIVO -----");
        System.out.println("Ingrese la ruta del archivo de destino (Ej. E:\\datos\\Desktop\\Fut5App.txt): ");
        String rutaArchivo = scanner.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Equipo equipo : equipos) {
                for (Jugador jugador : equipo.getJugadores()) {
                    String linea = jugador.getNombre() + "," + jugador.getApellido() + "," + jugador.getAltura() + "," + jugador.getPosicion() + "," + jugador.getGoles() + "," + jugador.getPartidos() + "," + jugador.isCapitan() + "," + jugador.getNumeroCamiseta() + "," + equipo.getNombre();
                    bw.write(linea);
                    bw.newLine();
                }
            }
            System.out.println("Jugadores exportados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar jugadores al archivo.");
            e.printStackTrace();
        }
    }

    private void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorNombre() {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES ORDENADOS POR NOMBRE) -----");
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre: " + equipo.getNombre());
                System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Jugadores (ordenados por nombre):");
                List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                jugadoresOrdenados.sort(Comparator.comparing(Jugador::getNombre));
                for (Jugador jugador : jugadoresOrdenados) {
                    System.out.println("- " + jugador.getNombreCompleto());
                }
                return;
            }
        }

        System.out.println("No se encontró ningún equipo con ese nombre.");
    }

    private void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorCamiseta() {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES ORDENADOS POR NÚMERO DE CAMISETA) -----");
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre: " + equipo.getNombre());
                System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Jugadores (ordenados por número de camiseta):");
                List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                jugadoresOrdenados.sort(Comparator.comparingInt(Jugador::getNumeroCamiseta));
                for (Jugador jugador : jugadoresOrdenados) {
                    System.out.println("- " + jugador.getNombreCompleto() + " (Camiseta: " + jugador.getNumeroCamiseta() + ")");
                }
                return;
            }
        }

        System.out.println("No se encontró ningún equipo con ese nombre.");
    }

    private void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorPosicionYCamiseta() {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES ORDENADOS POR POSICIÓN Y NÚMERO DE CAMISETA) -----");
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre: " + equipo.getNombre());
                System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Jugadores (ordenados por posición y número de camiseta):");
                List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                jugadoresOrdenados.sort(Comparator.comparing(Jugador::getPosicion).thenComparingInt(Jugador::getNumeroCamiseta));
                for (Jugador jugador : jugadoresOrdenados) {
                    System.out.println("- " + jugador.getNombreCompleto() + " (Posición: " + jugador.getPosicion() + ", Camiseta: " + jugador.getNumeroCamiseta() + ")");
                }
                return;
            }
        }

        System.out.println("No se encontró ningún equipo con ese nombre.");
    }
}
