/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.services;

import fut5app.models.Equipo;
import fut5app.models.Jugador;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jesé
 */
public class JugadorService {

    Scanner scanner = new Scanner(System.in);

    protected Jugador crearJugador(Equipo equipo) {
        String nombreJugador = ingresarNombreJugador();
        String apellidoJugador = ingresarApellidoJugador();
        int alturaJugador = ingresarAlturaJugador();
        String posicionJugador = ingresarPosicionJugador();
        int golesJugador = ingresarGolesJugador();
        int partidosJugador = ingresarPartidosJugador();
        boolean esCapitan = verificarCapitan(equipo);
        int numeroCamiseta = ingresarNumeroCamiseta(equipo);

        Jugador jugador = new Jugador(nombreJugador, apellidoJugador, alturaJugador, posicionJugador, golesJugador, partidosJugador, esCapitan, numeroCamiseta, equipo);
        return jugador;
    }

    private String ingresarNombreJugador() {
        while (true) {
            System.out.println("Ingrese el nombre del jugador: ");
            String inputJugador = scanner.nextLine();
            if (!inputJugador.isEmpty()) {
                return inputJugador;
            }
            System.out.println("El nombre del jugador no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    private String ingresarApellidoJugador() {
        while (true) {
            System.out.println("Ingrese el apellido del jugador: ");
            String inputApellido = scanner.nextLine();
            if (!inputApellido.isEmpty()) {
                return inputApellido;
            }
            System.out.println("El apellido del jugador no puede estar vacío. Por favor, ingrese un apellido válido: ");
        }
    }

    private int ingresarAlturaJugador() {
        while (true) {
            System.out.println("Ingrese la altura del jugador (Cm): ");
            try {
                int inputAltura = scanner.nextInt();
                scanner.nextLine();
                if (inputAltura >= 0) {
                    return inputAltura;
                } else {
                    System.out.println("La altura del jugador debe ser un número positivo. Por favor, ingrese un valor válido: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("La altura del jugador debe ser un número. Por favor, ingrese un valor válido: ");
                e.printStackTrace();
            }
        }
    }

    private String ingresarPosicionJugador() {
        while (true) {
            System.out.println("Ingrese la posición del jugador (Arquero, Defensor, Mediocampista, Delantero): ");
            String inputPosicion = scanner.nextLine();
            if (inputPosicion.matches("Arquero|Defensor|Mediocampista|Delantero|arquero|defensor|mediocampista|delantero")) {
                return inputPosicion;
            }
            System.out.println("La posición ingresada no es válida. Por favor, ingrese una posición válida: ");
        }
    }

    private int ingresarGolesJugador() {
        while (true) {
            System.out.println("Ingrese la cantidad de goles del jugador: ");
            try {
                int inputGoles = scanner.nextInt();
                scanner.nextLine();

                if (inputGoles >= 0) {
                    return inputGoles;
                } else {
                    System.out.println("La cantidad de goles no puede ser negativo. Por favor, ingrese un valor válido: ");
                }
            } catch (Exception e) {
                System.out.println("La cantidad de goles debe ser un número. Por favor, ingrese un valor válido: ");
                e.printStackTrace();
            }
        }
    }

    private int ingresarPartidosJugador() {
        while (true) {
            System.out.println("Ingrese la cantidad de partidos del jugador: ");
            try {
                int inputPartidos = scanner.nextInt();
                scanner.nextLine();

                if (inputPartidos >= 0) {
                    return inputPartidos;
                } else {
                    System.out.println("La cantidad de partidos no puede ser negativo. Por favor, ingrese un valor válido: ");
                }
            } catch (Exception e) {
                System.out.println("La cantidad de partidos debe ser un número. Por favor, ingrese un valor válido: ");
                e.printStackTrace();
            }
        }
    }

    private boolean verificarCapitan(Equipo equipo) {
        if (!equipo.existeCapitan()) {
            while (true) {
                System.out.println("¿El jugador es capitán? (s/n): ");
                String inputCapitan = scanner.nextLine();
                if (inputCapitan.equalsIgnoreCase("s") || inputCapitan.equalsIgnoreCase("n")) {
                    return inputCapitan.equalsIgnoreCase("s");
                }
                System.out.println("Valor inválido. Por favor, ingrese un valor válido: ");
            }
        } else {
            return false;
        }
    }

    private int ingresarNumeroCamiseta(Equipo equipo) {
        while (true) {
            System.out.println("Ingrese el número de camiseta del jugador: ");
            try {
                int numeroCamiseta = scanner.nextInt();
                scanner.nextLine();
                if (numeroCamiseta > 0 && !equipo.existeNumeroCamiseta(numeroCamiseta)) {
                    return numeroCamiseta;
                } else if (numeroCamiseta <= 0) {
                    System.out.println("El número de camiseta debe ser un valor positivo. Por favor, ingrese un número de camiseta válido: ");
                } else {
                    System.out.println("El número de camiseta " + numeroCamiseta + " ya está en uso. Por favor, ingrese otro número de camiseta: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("El número de camiseta debe ser un número. Por favor, ingrese un valor válido: ");
                e.printStackTrace();
            }
        }
    }

    protected void buscarJugadorPorNombre(List<Equipo> equipos) {
        System.out.println("----- BUSCAR JUGADOR POR NOMBRE -----");
        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese el nombre del jugador: ");
            String nombreJugador = scanner.nextLine();
            if (!nombreJugador.isEmpty()) {
                for (Equipo equipo : equipos) {
                    for (Jugador jugador : equipo.getJugadores()) {
                        if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                            System.out.println(jugador.getInfo());
                            flag = false;
                            return;
                        }
                    }
                }
                flag = false;
                System.out.println("No se encontró ningún jugador con ese nombre.");
                return;
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");

        }
    }

}
