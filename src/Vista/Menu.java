package Vista;

import Controladores.ControladorTarea;
import Modelo.Tarea;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private ControladorTarea controlador;

    public Menu() {
        scanner = new Scanner(System.in);
        controlador = new ControladorTarea();
    }

    public void iniciar() {

        int opcion;

        do {
            mostrarMenu();
            opcion = pedirEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> crearTarea();
                case 2 -> listarTareas();
                case 3 -> modificarTarea();
                case 4 -> eliminarTarea();
                case 5 -> buscarTareas();
                case 0 -> System.out.println("\nSaliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        GESTOR DE TAREAS (IA)         ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Crear tarea                       ║");
        System.out.println("║ 2. Listar tareas                     ║");
        System.out.println("║ 3. Modificar tarea                   ║");
        System.out.println("║ 4. Eliminar tarea                    ║");
        System.out.println("║ 5. Buscar tareas                     ║");
        System.out.println("║ 0. Salir                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private void crearTarea() {
        System.out.println("\n--- CREAR TAREA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        boolean ok = controlador.crearTarea(titulo, descripcion);

        if (ok) {
            System.out.println("✔ Tarea creada correctamente (prioridad sugerida por IA).");
        } else {
            System.out.println("✘ Error al crear la tarea.");
        }
    }

    private void listarTareas() {
        System.out.println("\n--- LISTA DE TAREAS ---");

        List<Tarea> lista = controlador.listarTareas();

        if (lista.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        for (Tarea t : lista) {
            System.out.println("----------------------------------------");
            System.out.println(t);
        }
    }

    private void modificarTarea() {
        System.out.println("\n--- MODIFICAR TAREA ---");

        int id = pedirEntero("ID de la tarea: ");

        System.out.print("Nuevo título: ");
        String titulo = scanner.nextLine();

        System.out.print("Nueva descripción: ");
        String descripcion = scanner.nextLine();

        int prioridad = pedirEntero("Nueva prioridad (1-5): ");

        System.out.print("Nuevo estado (pendiente/completada): ");
        String estado = scanner.nextLine();

        boolean ok = controlador.modificarTarea(id, titulo, descripcion, prioridad, estado);

        if (ok) {
            System.out.println("✔ Tarea modificada correctamente.");
        } else {
            System.out.println("✘ Error al modificar la tarea.");
        }
    }

    private void eliminarTarea() {
        System.out.println("\n--- ELIMINAR TAREA ---");

        int id = pedirEntero("ID de la tarea: ");

        boolean ok = controlador.eliminarTarea(id);

        if (ok) {
            System.out.println("✔ Tarea eliminada correctamente.");
        } else {
            System.out.println("✘ Error al eliminar la tarea.");
        }
    }

    private void buscarTareas() {
        System.out.println("\n--- BUSCAR TAREAS ---");

        System.out.print("Texto a buscar: ");
        String texto = scanner.nextLine();

        List<Tarea> lista = controlador.buscarTareas(texto);

        if (lista.isEmpty()) {
            System.out.println("No se encontraron tareas.");
            return;
        }

        for (Tarea t : lista) {
            System.out.println("----------------------------------------");
            System.out.println(t);
        }
    }

    private int pedirEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Introduce un número válido: ");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return numero;
    }
}
