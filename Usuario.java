package frames;

public class Usuario {
    protected String nombre;
    protected String contrasena;
    protected Directorio directorioRaiz;

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.directorioRaiz = new Directorio("Z:\\" + nombre);
        inicializarDirectoriosBasicos();
    }

    protected void inicializarDirectoriosBasicos() {
        directorioRaiz.crearSubdirectorio("Mis Documentos");
        directorioRaiz.crearSubdirectorio("Música");
        directorioRaiz.crearSubdirectorio("Mis Imágenes");
    }

    public boolean validarCredenciales(String nombre, String contrasena) {
        return this.nombre.equals(nombre) && this.contrasena.equals(contrasena);
    }

    public String getNombre() {
        return nombre;
    }

    public Directorio getDirectorioRaiz() {
        return directorioRaiz;
    }
}
