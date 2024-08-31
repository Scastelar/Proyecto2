package frames;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Archivo {
    private File archivo;
    private String contenido;

    public Archivo(String ruta, String contenidoInicial) {
        this.archivo = new File(ruta);
        this.contenido = contenidoInicial;

        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenidoInicial);
            System.out.println("Archivo " + archivo.getName() + " creado con contenido inicial.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public String leerContenido() {
        try {
            return new String(Files.readAllBytes(Paths.get(archivo.getPath())));
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
    }

    public void escribirContenido(String nuevoContenido) {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(nuevoContenido);
            this.contenido = nuevoContenido;
            System.out.println("Archivo " + archivo.getName() + " actualizado.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public String getNombre() {
        return archivo.getName();
    }

    public String getRuta() {
        return archivo.getPath();
    }
}
