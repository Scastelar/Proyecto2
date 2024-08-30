package frames;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static SistemaOperativo sistemaOperativo = new SistemaOperativo();
    private static Usuario usuarioActual = null;

    public static void main(String[] args) {
        while (true) {
            mostrarMenuInicial();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    ingresarUsuario();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta nuevamente.");
            }
        }
    }

    private static void mostrarMenuInicial() {
        System.out.println("========== MENÚ INICIAL ==========");
        System.out.println("1. Crear Usuario");
        System.out.println("2. Ingresar Usuario");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void crearUsuario() {
        System.out.print("Introduce el nombre del nuevo usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce la contraseña del nuevo usuario: ");
        String contrasena = scanner.nextLine();

        sistemaOperativo.getAdministrador().crearUsuario(nombre, contrasena, sistemaOperativo);
    }

    private static void ingresarUsuario() {
        System.out.print("Introduce el nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce la contraseña: ");
        String contrasena = scanner.nextLine();

        usuarioActual = sistemaOperativo.iniciarSesion(nombre, contrasena);
        if (usuarioActual != null) {
            mostrarMenuPrincipal();
        }
    }

    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("========== MENÚ PRINCIPAL ==========");
            System.out.println("1. Ver carpetas/archivos");
            System.out.println("2. Crear carpetas/archivos");
            System.out.println("3. Eliminar carpetas/archivos");
            System.out.println("4. Cerrar sesión");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    verCarpetasArchivos();
                    break;
                case 2:
                    crearCarpetaArchivo();
                    break;
                case 3:
                    eliminarCarpetaArchivo();
                    break;
                case 4:
                    usuarioActual = null;
                    System.out.println("Sesión cerrada.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intenta nuevamente.");
            }
        }
    }

    private static void verCarpetasArchivos() {
        if (usuarioActual instanceof Administrador) {
            System.out.println("Eres administrador. Puedes ver las carpetas de todos los usuarios.");
            for (Usuario usuario : sistemaOperativo.getUsuarios()) {
                System.out.println("Carpetas del usuario: " + usuario.getNombre());
                mostrarContenidoDirectorio(usuario.getDirectorioRaiz());
            }
        } else {
            System.out.println("Carpetas de " + usuarioActual.getNombre());
            mostrarContenidoDirectorio(usuarioActual.getDirectorioRaiz());
        }
    }

    private static void mostrarContenidoDirectorio(Directorio directorio) {
        System.out.println("Directorio: " + directorio.getRuta());
        for (Directorio subdir : directorio.getSubdirectorios()) {
            System.out.println("  [Dir] " + subdir.getRuta());
        }
        for (Archivo archivo : directorio.getArchivos()) {
            System.out.println("  [File] " + archivo.getNombre());
        }
    }

    private static void crearCarpetaArchivo() {
        System.out.println("1. Crear Carpeta");
        System.out.println("2. Crear Archivo");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        System.out.print("Introduce el nombre: ");
        String nombre = scanner.nextLine();

        if (opcion == 1) {
            usuarioActual.getDirectorioRaiz().crearSubdirectorio(nombre);
        } else if (opcion == 2) {
            System.out.print("Introduce el contenido del archivo: ");
            String contenido = scanner.nextLine();
            usuarioActual.getDirectorioRaiz().crearArchivo(nombre, contenido);
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private static void eliminarCarpetaArchivo() {
        System.out.println("1. Eliminar Carpeta");
        System.out.println("2. Eliminar Archivo");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        System.out.print("Introduce el nombre: ");
        String nombre = scanner.nextLine();

        // Ejemplo básico de cómo eliminar carpetas o archivos
        if (opcion == 1) {
            // Implementar lógica para eliminar subdirectorio
        } else if (opcion == 2) {
            // Implementar lógica para eliminar archivo
        } else {
            System.out.println("Opción no válida.");
        }
    }
}

