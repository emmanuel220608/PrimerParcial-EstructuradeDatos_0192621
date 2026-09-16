package aeropuerto;
import java.util.Scanner;

public class Aeropuerto {

    static Scanner sc = new Scanner(System.in);
    static ListaVuelos vuelos = new ListaVuelos();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println();
            System.out.println("========== AEROPUERTO ==========");
            System.out.println("1. Registrar vuelo");
            System.out.println("2. Asignar puesto");
            System.out.println("3. Iniciar abordaje");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            if (opcion == 1) {
                registrarVuelo();
            } else if (opcion == 2) {
                asignarPuesto();
            } else if (opcion == 3) {
                iniciarAbordaje();
            } else if (opcion == 0) {
                System.out.println("Saliendo del sistema...");
            } else {
                System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    // ---------- OPCION 1: REGISTRAR VUELO ----------
    static void registrarVuelo() {
        System.out.println();
        System.out.println("----- Registrar vuelo -----");

        String codigo;
        while (true) {
            System.out.print("Codigo del vuelo: ");
            codigo = sc.nextLine();

            if (vuelos.existeCodigo(codigo)) {
                System.out.println("Ya existe un vuelo con ese codigo. Intente con otro.");
            } else {
                break;
            }
        }

        System.out.print("Ciudad de destino: ");
        String ciudad = sc.nextLine();

        System.out.print("Pais de destino: ");
        String pais = sc.nextLine();

        System.out.print("Hora de salida: ");
        String hora = sc.nextLine();

        System.out.print("Duracion estimada: ");
        String duracion = sc.nextLine();

        Vuelo nuevo = new Vuelo(codigo, ciudad, pais, hora, duracion);
        vuelos.agregar(nuevo);

        System.out.println("Vuelo registrado correctamente.");
    }

    // ---------- OPCION 2: ASIGNAR PUESTO ----------
    static void asignarPuesto() {
        if (vuelos.estaVacia()) {
            System.out.println("No hay vuelos registrados.");
            return;
        }

        System.out.println();
        System.out.println("========== VUELOS ==========");

        NodoVuelo actual = vuelos.primero;
        int contador = 1;
        while (actual != null) {
            System.out.println(contador + ". " + actual.vuelo.codigo + " - " + actual.vuelo.ciudadDestino);
            actual = actual.siguiente;
            contador++;
        }

        System.out.print("Seleccione un vuelo: ");
        int seleccion = leerEntero();

        if (seleccion < 1 || seleccion >= contador) {
            System.out.println("Opcion invalida.");
            return;
        }

        actual = vuelos.primero;
        for (int i = 1; i < seleccion; i++) {
            actual = actual.siguiente;
        }
        Vuelo vueloSeleccionado = actual.vuelo;

        mostrarVuelo(vueloSeleccionado);

        boolean asignado = false;
        while (!asignado) {
            System.out.print("Ingrese el numero de puesto que desea asignar: ");
            int puesto = leerEntero();

            if (puesto < 1 || puesto > 20) {
                System.out.println("El puesto debe estar entre 1 y 20.");
                continue;
            }

            int indice = puesto - 1;

            if (vueloSeleccionado.puestos[indice].equals("OCUPADO")) {
                System.out.println("Ese puesto no se encuentra disponible. Seleccione otro.");
                continue;
            }

            System.out.print("Nombre del pasajero: ");
            String nombre = sc.nextLine();

            System.out.print("Documento: ");
            String documento = sc.nextLine();

            vueloSeleccionado.puestos[indice] = "OCUPADO";
            vueloSeleccionado.nombrePasajero[indice] = nombre;
            vueloSeleccionado.documentoPasajero[indice] = documento;

            System.out.println("Puesto asignado correctamente.");
            asignado = true;
        }
    }

    static void mostrarVuelo(Vuelo v) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("        VUELO SELECCIONADO");
        System.out.println("====================================");
        System.out.println("Vuelo: " + v.codigo);
        System.out.println("Destino: " + v.ciudadDestino);
        System.out.println("Hora de salida: " + v.horaSalida);
        System.out.println("Duracion: " + v.duracion);
        System.out.println();
        System.out.println("Puestos:");
        System.out.println();

        for (int i = 0; i < 20; i++) {
            String estado = v.puestos[i].equals("DISPONIBLE") ? "D" : "O";
            System.out.print((i + 1) + "[" + estado + "] ");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("D = Disponible");
        System.out.println("O = Ocupado");
        System.out.println("====================================");
    }

    // ---------- OPCION 3: INICIAR ABORDAJE ----------
    static void iniciarAbordaje() {
        if (vuelos.estaVacia()) {
            System.out.println("No hay vuelos registrados.");
            return;
        }

        NodoVuelo actual = vuelos.primero;

        while (actual != null) {
            Vuelo v = actual.vuelo;

            int ocupados = 0;
            for (int i = 0; i < 20; i++) {
                if (v.puestos[i].equals("OCUPADO")) {
                    ocupados++;
                }
            }
            int disponibles = 20 - ocupados;

            System.out.println();
            System.out.println("====================================");
            System.out.println("           VUELO ACTUAL");
            System.out.println("====================================");
            System.out.println("Vuelo: " + v.codigo);
            System.out.println("Destino: " + v.ciudadDestino);
            System.out.println("Hora de salida: " + v.horaSalida);
            System.out.println("Duracion: " + v.duracion);
            System.out.println();
            System.out.println("Puestos ocupados: " + ocupados);
            System.out.println("Puestos disponibles: " + disponibles);

            actual = actual.siguiente;

            if (actual != null) {
                System.out.println();
                System.out.print("Presione una tecla para continuar...");
                sc.nextLine();
            }
        }

        System.out.println();
        System.out.println("Todos los vuelos han sido procesados.");
        System.out.println("Fin del programa.");
        System.exit(0);
    }

    // ---------- METODO AUXILIAR PARA LEER ENTEROS ----------
    static int leerEntero() {
        while (true) {
            String linea = sc.nextLine();
            try {
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
}