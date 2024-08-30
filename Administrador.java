package frames;

public class Administrador extends Usuario {

    public Administrador(String nombre, String contrasena) {
        super(nombre, contrasena);
    }

    public void crearUsuario(String nombre, String contrasena, SistemaOperativo so) {
        so.crearUsuario(nombre, contrasena);
    }

    public void accederDirectorioDeUsuario(Usuario usuario) {
        System.out.println("Accediendo al directorio de " + usuario.getNombre());
        // Implementar la lógica para navegar en el directorio del usuario
    }
}

