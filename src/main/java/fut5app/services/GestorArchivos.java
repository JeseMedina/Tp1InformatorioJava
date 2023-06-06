/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.services;

import fut5app.models.Entrenador;
import fut5app.models.Equipo;
import fut5app.models.Jugador;
import fut5app.services.interfaces.IGestorArchivosService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

/**
 *
 * @author Jesé
 */
public class GestorArchivos implements IGestorArchivosService {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void importarJugadoresDesdeArchivo(List<Equipo> equipos) {
        System.out.println("----- IMPORTAR JUGADORES DESDE ARCHIVO -----");
        String rutaArchivo = obtenerRutaArchivo();
        if (rutaArchivo.isEmpty()) {
            System.out.println("La ruta del archivo es vacía. Por favor, ingrese una ruta válida.");
            return;
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                importarJugadorDesdeLinea(linea, equipos);
            }
            System.out.println("Jugadores importados correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Error al importar jugadores desde el archivo");
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void exportarJugadoresAArchivo(List<Equipo> equipos) {
        System.out.println("----- EXPORTAR JUGADORES A ARCHIVO -----");

        String rutaArchivo = obtenerRutaArchivo();
        if (rutaArchivo.isEmpty()) {
            System.out.println("La ruta del archivo es vacía. Por favor, ingrese una ruta válida.");
            return;
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(rutaArchivo));
            for (Equipo equipo : equipos) {
                exportarJugadoresDeEquipo(equipo, bw);
            }
            System.out.println("Jugadores exportados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar jugadores al archivo.");
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String obtenerRutaArchivo() {
        String rutaArchivo;
        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese la ruta del archivo (Ej. E:\\datos\\Desktop\\Fut5App.txt): ");
            rutaArchivo = scanner.nextLine();
            if (!rutaArchivo.isEmpty()) {
                return rutaArchivo;
            }
        }
        return "";
    }

    private void importarJugadorDesdeLinea(String linea, List<Equipo> equipos) {
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
        String fechaCreacionEquipo = datosJugador[9];
        String nombreEntrenador = datosJugador[10];
        String apellidoEntrenador = datosJugador[11];
        int edadEntrenador = Integer.parseInt(datosJugador[12]);

        Equipo equipo = buscarEquipoPorNombre(nombreEquipo, equipos);
        if (equipo == null) {
            equipo = new Equipo(nombreEquipo, fechaCreacionEquipo, new Entrenador(nombreEntrenador, apellidoEntrenador, edadEntrenador));
            equipos.add(equipo);
        }
        Jugador jugador = new Jugador(nombre, apellido, altura, posicion, goles, partidos, esCapitan, numero, equipo);
        equipo.agregarJugador(jugador);
    }

    private Equipo buscarEquipoPorNombre(String nombreEquipo, List<Equipo> equipos) {
        for (Equipo eq : equipos) {
            if (eq.getNombre().equals(nombreEquipo)) {
                return eq;
            }
        }
        return null;
    }

    private void exportarJugadoresDeEquipo(Equipo equipo, BufferedWriter bw) throws IOException {
        for (Jugador jugador : equipo.getJugadores()) {
            String linea = jugador.getLine();
            bw.write(linea);
            bw.newLine();
        }
    }
}
