package frames;

import frames.Archivo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directorio {
    private File directorio;
    private List<Directorio> subdirectorios;
    private List<Archivo> archivos;
    private String name;

    public Directorio(String ruta) {
        this.directorio = new File(ruta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        this.subdirectorios = new ArrayList<>();
        this.archivos = new ArrayList<>();
        this.name = "";
    }
    
    public String getName(){
        return name;
    }

    public void crearSubdirectorio(String nombre) {
        Directorio subdirectorio = new Directorio(directorio.getPath() + "\\" + nombre);
        subdirectorios.add(subdirectorio);
        System.out.println("Subdirectorio " + nombre + " creado en " + directorio.getPath());
    }

    public void crearArchivo(String nombre, String contenido) {
        Archivo archivo = new Archivo(directorio.getPath() + "\\" + nombre, contenido);
        archivos.add(archivo);
        System.out.println("Archivo " + nombre + " creado en " + directorio.getPath());
    }

    public List<Directorio> getSubdirectorios() {
        return subdirectorios;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public String getRuta() {
        return directorio.getPath();
    }
}

