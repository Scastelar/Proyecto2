package frames;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    protected String nombre;
    protected String contrasena;
    protected Directorio directorioRaiz;
    protected List<String> folders;

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.folders = new ArrayList<>();
        this.directorioRaiz = new Directorio("Z:\\" + nombre);
        crearFoldersPredeterminados();
    }

    /*
    protected void inicializarDirectoriosBasicos() {
        directorioRaiz.crearSubdirectorio("Mis Documentos");
        directorioRaiz.crearSubdirectorio("Música");
        directorioRaiz.crearSubdirectorio("Mis Imágenes");
    }
    */  
    
    private void crearFoldersPredeterminados() {
        folders.add("Documents");
        folders.add("Downloads");
        folders.add("Pictures");
    }

    public boolean validarCredenciales(String nombre, String contrasena) {
        return this.nombre.equals(nombre) && this.contrasena.equals(contrasena);
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getContrasena() {
        return contrasena;
    }

    public List<String> getFolders() {
        return folders;
    }

    public Directorio getDirectorioRaiz() {
        return directorioRaiz;
    }
}