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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

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
                    buscarEquipoPorNombreMostrarJugadoresOrdenadosPorNombre();
                    break;
                case 7:
                    buscarEquipoPorNombreMostrarJugadoresOrdenadosPorCamiseta();
                    break;
                case 8:
                    buscarEquipoPorNombreMostrarJugadoresOrdenadosPorPosicionYCamiseta();
                    break;
                case 9:
                    importarJugadoresDesdeArchivo();
                    break;
                case 10:
                    exportarJugadoresAArchivo();
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
        String nombreEquipo;
        String fechaCreacion;

        System.out.println("----- CREAR EQUIPO -----");

        while (true) {
            System.out.println("Ingrese el nombre del equipo: ");
            String inputNombre = scanner.nextLine();
            if (!inputNombre.isEmpty()) {
                nombreEquipo = inputNombre;
                break;
            }
            System.out.println("El nombre del equipo no puede estar vacío. Por favor, ingrese un nombre válido.");
        }

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
        String nombreEntrenador;
        String apellidoEntrenador;
        int edadEntrenador = 0;

        while (true) {
            System.out.println("Ingrese el nombre del entrenador: ");
            String inputNombre = scanner.nextLine();
            if (!inputNombre.isEmpty()) {
                nombreEntrenador = inputNombre;
                break;
            }
            System.out.println("El nombre del entrenador no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }

        while (true) {
            System.out.println("Ingrese el apellido del entrenador: ");
            String inputApellido = scanner.nextLine();
            if (!inputApellido.isEmpty()) {
                apellidoEntrenador = inputApellido;
                break;
            }
            System.out.println("El apellido del entrenador no puede estar vacío. Por favor, ingrese un apellido válido: ");
        }

        while (true) {
            System.out.println("Ingrese la edad del entrenador: ");
            try {
                int inputEdad = Integer.parseInt(scanner.nextLine());
                if (edadEntrenador >= 0) {
                    edadEntrenador = inputEdad;
                    break;
                }
                System.out.println("La edad del entrenador debe ser un número positivo. Por favor, ingrese un valor válido:");
            } catch (Exception e) {
                System.out.println("La edad del entrenador debe ser un número. Por favor, ingrese un valor válido:");
                e.printStackTrace();
            }
        }

        Entrenador entrenador = new Entrenador(nombreEntrenador, apellidoEntrenador, edadEntrenador);
        return entrenador;
    }

    private Jugador crearJugador(Equipo equipo) {
        String nombreJugador;
        String apellidoJugador;
        int alturaJugador = 0;
        String posicionJugador;
        int golesJugador = 0;
        int partidosJugador = 0;
        boolean esCapitan;
        int numeroCamiseta = 0;

        while (true) {
            System.out.println("Ingrese el nombre del jugador: ");
            String inputJugador = scanner.nextLine();
            if (!inputJugador.isEmpty()) {
                nombreJugador = inputJugador;
                break;
            }
            System.out.println("El nombre del jugador no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }

        while (true) {
            System.out.println("Ingrese el apellido del jugador: ");
            String inputApellido = scanner.nextLine();
            if (!inputApellido.isEmpty()) {
                apellidoJugador = inputApellido;
                break;
            }
            System.out.println("El apellido del jugador no puede estar vacío. Por favor, ingrese un apellido válido: ");
        }

        while (true) {
            System.out.println("Ingrese la altura del jugador (Cm): ");
            try {
                int inputAltura = scanner.nextInt();
                scanner.nextLine();
                if (alturaJugador >= 0) {
                    alturaJugador = inputAltura;
                    break;
                } else {
                    System.out.println("La altura del jugador debe ser un número positivo. Por favor, ingrese un valor válido: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("La altura del jugador debe ser un número. Por favor, ingrese un valor válido: ");
                e.printStackTrace();
            }
        }

        while (true) {
            System.out.println("Ingrese la posición del jugador (Arquero, Defensor, Mediocampista, Delantero): ");
            String inputPosicion = scanner.nextLine();
            if (inputPosicion.matches("Arquero|Defensor|Mediocampista|Delantero|arquero|defensor|mediocampista|delantero")) {
                posicionJugador = inputPosicion;
                break;
            }
            System.out.println("La posición ingresada no es válida. Por favor, ingrese una posición válida: ");
        }

        while (true) {
            System.out.println("Ingrese la cantidad de goles del jugador: ");
            try {
                int inputGoles = scanner.nextInt();
                scanner.nextLine();

                if (inputGoles < 0) {
                    System.out.println("La cantidad de goles no puede ser negativo. Por favor, ingrese un valor válido: ");
                } else {
                    golesJugador = inputGoles;
                    break;
                }
            } catch (Exception e) {
                System.out.println("La cantidad de goles debe ser un número. Por favor, ingrese un valor válido: ");
                e.printStackTrace();
            }
        }

        while (true) {
            System.out.println("Ingrese la cantidad de partidos del jugador: ");
            try {
                int inputPartidos = scanner.nextInt();
                scanner.nextLine();

                if (inputPartidos < 0) {
                    System.out.println("La cantidad de partidos no puede ser negativo. Por favor, ingrese un valor válido: ");
                } else {
                    partidosJugador = inputPartidos;
                    break;
                }
            } catch (Exception e) {
                System.out.println("La cantidad de partidos debe ser un número. Por favor, ingrese un valor válido: ");
                e.printStackTrace();
            }

        }

        if (!equipo.existeCapitan()) {
            while (true) {
                System.out.println("¿El jugador es capitán? (s/n): ");
                String inputCapitan = scanner.nextLine();
                if (inputCapitan.equalsIgnoreCase("s") || inputCapitan.equalsIgnoreCase("n")) {
                    esCapitan = inputCapitan.equalsIgnoreCase("s");
                    break;
                }
                System.out.println("Valor invalido. Por favor, ingrese un valor válido: ");
            }
        } else {
            esCapitan = false;
        }

        while (true) {
            System.out.println("Ingrese el número de camiseta del jugador: ");
            try {
                numeroCamiseta = scanner.nextInt();
                scanner.nextLine();
                if (numeroCamiseta > 0 && !equipo.existeNumeroCamiseta(numeroCamiseta)) {
                    break;
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

        Jugador jugador = new Jugador(nombreJugador, apellidoJugador, alturaJugador, posicionJugador, golesJugador, partidosJugador, esCapitan, numeroCamiseta, equipo);
        return jugador;
    }

    private void buscarJugadorPorNombre() {
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
                System.out.println("No se encontró ningún jugador con ese nombre.");
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");

        }
    }

    private void buscarEquipoPorNombreInformacionBasica() {
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
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    private void buscarEquipoPorNombreMostrarJugadores() {
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
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    private void eliminarEquipoPorNombre() {
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
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    private void importarJugadoresDesdeArchivo() {
        System.out.println("----- IMPORTAR JUGADORES DESDE ARCHIVO -----");

        String rutaArchivo;
        BufferedReader br = null;
        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese la ruta del archivo (Ej. E:\\datos\\Desktop\\Fut5App.txt): ");
            rutaArchivo = scanner.nextLine();
            if (rutaArchivo.isEmpty()) {
                System.out.println("La ruta del archivo es vacía. Por favor, ingrese una ruta válida.");
            } else {
                try {
                    br = new BufferedReader(new FileReader(rutaArchivo));
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
                        String fechaCreacionEquipo = datosJugador[9];
                        String nombreEntrenador = datosJugador[10];
                        String apellidoEntrenador = datosJugador[11];
                        int edadEntrenador = Integer.parseInt(datosJugador[12]);

                        Equipo equipo = null;
                        for (Equipo eq : equipos) {
                            if (eq.getNombre().equals(nombreEquipo)) {
                                equipo = eq;
                                break;
                            }
                        }
                        if (equipo == null) {
                            equipo = new Equipo(nombreEquipo, fechaCreacionEquipo, new Entrenador(nombreEntrenador, apellidoEntrenador, edadEntrenador));
                            equipos.add(equipo);
                        }
                        Jugador jugador = new Jugador(nombre, apellido, altura, posicion, goles, partidos, esCapitan, numero, equipo);
                        equipo.agregarJugador(jugador);
                    }
                    System.out.println("Jugadores importados correctamente.");
                    flag = false;
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
        }
    }

    private void exportarJugadoresAArchivo() {
        System.out.println("----- EXPORTAR JUGADORES A ARCHIVO -----");

        String rutaArchivo;
        BufferedWriter bw = null;
        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese la ruta del archivo de destino (Ej. E:\\datos\\Desktop\\Fut5App.txt): ");
            rutaArchivo = scanner.nextLine();
            if (rutaArchivo.isEmpty()) {
                System.out.println("La ruta del archivo es vacía. Por favor, ingrese una ruta válida.");
            } else {
                try {
                    bw = new BufferedWriter(new FileWriter(rutaArchivo));
                    for (Equipo equipo : equipos) {
                        for (Jugador jugador : equipo.getJugadores()) {
                            String linea = jugador.getLine();
                            bw.write(linea);
                            bw.newLine();
                        }
                    }
                    System.out.println("Jugadores exportados correctamente.");
                    flag = false;
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
        }
    }

    private void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorNombre() {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES ORDENADOS POR NOMBRE) -----");

        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese el nombre del equipo: ");
            String nombreEquipo = scanner.nextLine();
            if (!nombreEquipo.isEmpty()) {
                for (Equipo equipo : equipos) {
                    if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                        System.out.println("Nombre: " + equipo.getNombre());
                        System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                        System.out.println("Jugadores (ordenados por nombre):");
                        List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                        jugadoresOrdenados.sort(Comparator.comparing(Jugador::getNombre));
                        for (Jugador jugador : jugadoresOrdenados) {
                            System.out.println("- " + jugador.getInfoLine());
                        }
                        flag = false;
                        return;
                    }
                }
                System.out.println("No se encontró ningún equipo con ese nombre.");
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    private void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorCamiseta() {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES ORDENADOS POR NÚMERO DE CAMISETA) -----");

        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese el nombre del equipo: ");
            String nombreEquipo = scanner.nextLine();
            if (!nombreEquipo.isEmpty()) {
                for (Equipo equipo : equipos) {
                    if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                        System.out.println("Nombre: " + equipo.getNombre());
                        System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                        System.out.println("Jugadores (ordenados por número de camiseta):");
                        List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                        jugadoresOrdenados.sort(Comparator.comparingInt(Jugador::getNumeroCamiseta));
                        for (Jugador jugador : jugadoresOrdenados) {
                            System.out.println("- " + jugador.getInfoLine());
                        }
                        flag = false;
                        return;
                    }
                }
                System.out.println("No se encontró ningún equipo con ese nombre.");
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    private void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorPosicionYCamiseta() {
        System.out.println("----- BUSCAR EQUIPO POR NOMBRE (MOSTRAR JUGADORES ORDENADOS POR POSICIÓN Y NÚMERO DE CAMISETA) -----");

        boolean flag = true;
        while (flag) {
            System.out.println("Ingrese el nombre del equipo: ");
            String nombreEquipo = scanner.nextLine();
            if (!nombreEquipo.isEmpty()) {
                for (Equipo equipo : equipos) {
                    if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                        System.out.println("Nombre: " + equipo.getNombre());
                        System.out.println("Entrenador: " + equipo.getEntrenador().getNombreCompleto());
                        System.out.println("Jugadores (ordenados por posición y número de camiseta):");
                        List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                        jugadoresOrdenados.sort(Comparator.comparing(Jugador::getPosicion).thenComparingInt(Jugador::getNumeroCamiseta));
                        for (Jugador jugador : jugadoresOrdenados) {
                            System.out.println("- " + jugador.getInfoLine());
                        }
                        flag = false;
                        return;
                    }
                }
                System.out.println("No se encontró ningún equipo con ese nombre.");
            }
            System.out.println("El nombre no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }
}
