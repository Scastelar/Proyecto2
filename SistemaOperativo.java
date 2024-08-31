package frames;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import javax.swing.JOptionPane;

public class SistemaOperativo implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public static SistemaOperativo getInstance() {
        if (instance == null) {
            instance = cargarSistema();
        }
        return instance;
    }
    
    private static SistemaOperativo cargarSistema() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sistema.ser"))) {
            return (SistemaOperativo) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new SistemaOperativo();
        }
    }

    public void guardarSistema() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sistema.ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                cargarFoldersUsuario(usuario);
                return usuario;
            }
        }
        JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
        System.out.println("");
        return null;
    }
    
    private void cargarFoldersUsuario(Usuario usuario) {
        for (String folder : usuario.getFolders()) {
            System.out.println("Carpeta cargada: " + folder);
        }
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
