/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fut5app.services;

import java.util.Scanner;
import fut5app.models.Entrenador;
import fut5app.services.interfaces.IEntrenadorService;
import fut5app.utils.Utils;

/**
 *
 * @author Jesé
 */
public class EntrenadorService implements IEntrenadorService {

    Scanner scanner = new Scanner(System.in);
    Utils utils = new Utils();

    @Override
    public Entrenador crearEntranador() {
        String nombreEntrenador = utils.capitalizeWords(obtenerNombreEntrenador());
        String apellidoEntrenador = obtenerApellidoEntrenador();
        int edadEntrenador = obtenerEdadEntrenador();

        Entrenador entrenador = new Entrenador(nombreEntrenador, apellidoEntrenador, edadEntrenador);
        return entrenador;
    }

    private String obtenerNombreEntrenador() {
        while (true) {
            System.out.println("Ingrese el nombre del entrenador: ");
            String inputNombre = scanner.nextLine();
            if (!inputNombre.isEmpty()) {
                return inputNombre;
            }
            System.out.println("El nombre del entrenador no puede estar vacío. Por favor, ingrese un nombre válido: ");
        }
    }

    private String obtenerApellidoEntrenador() {
        while (true) {
            System.out.println("Ingrese el apellido del entrenador: ");
            String inputApellido = scanner.nextLine();
            if (!inputApellido.isEmpty()) {
                return inputApellido;
            }
            System.out.println("El apellido del entrenador no puede estar vacío. Por favor, ingrese un apellido válido: ");
        }
    }

    private int obtenerEdadEntrenador() {
        while (true) {
            System.out.println("Ingrese la edad del entrenador: ");
            try {
                int inputEdad = Integer.parseInt(scanner.nextLine());
                if (inputEdad >= 0) {
                    return inputEdad;
                }
                System.out.println("La edad del entrenador debe ser un número positivo. Por favor, ingrese un valor válido:");
            } catch (Exception e) {
                System.out.println("La edad del entrenador debe ser un número. Por favor, ingrese un valor válido:");
                e.printStackTrace();
            }
        }
    }

}
