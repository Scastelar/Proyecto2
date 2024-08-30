package frames;

import java.util.*;
import javax.swing.JOptionPane;

public class SistemaOperativo {
    private static SistemaOperativo instance;
    private Usuario usuarioActual;
    private List<Usuario> usuarios;
    private Administrador administrador;

    public SistemaOperativo() {
        this.usuarios = new ArrayList<>();
        this.administrador = new Administrador("admin", "123");
        usuarios.add(administrador);
        System.out.println("Sistema operativo iniciado con usuario administrador por defecto.");
    }
    
    public static SistemaOperativo getInstance(){
        if (instance == null){
            instance = new SistemaOperativo();
        }
        return instance;
    }
    
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }
    
     public void cerrarSesion() {
        usuarioActual = null;
    }

    public void crearUsuario(String nombre, String contrasena) {
        Usuario nuevoUsuario = new Usuario(nombre, contrasena);
        usuarios.add(nuevoUsuario);
        JOptionPane.showMessageDialog(null, "Usuario " + nombre + " creado con éxito.");
    }

    public Usuario iniciarSesion(String nombre, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.validarCredenciales(nombre, contrasena)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso para " + nombre);
                return usuario;
            }
        }
        JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
        System.out.println("");
        return null;
    }

    public void mostrarUsuarios() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre());
        }
    }

    public Administrador getAdministrador() {
        return administrador;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    
}
