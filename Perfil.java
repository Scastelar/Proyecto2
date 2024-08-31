package frames;

import static frames.Login.sistemaOperativo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Perfil extends JPanel implements ActionListener {
    JTextField usuario = new JTextField();
    JButton crear = new JButton("Crear Usuario");
    JButton cerrarSesion = new JButton("Cerrar Sesión");
    Usuario usuarioActual = SistemaOperativo.getInstance().getUsuarioActual();
    Login Log;

    
    public Perfil(Login Log) {
        this.Log = Log;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(730, 480));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 192, 203)); 
        panel.setPreferredSize(new Dimension(730, 480));

        ImageIcon perfilImg = new ImageIcon("src\\imgs\\user.png"); 
        Image profileImage = perfilImg.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        perfilImg = new ImageIcon(profileImage);
        JLabel profileLabel = new JLabel(perfilImg);
        profileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titulo = new JLabel("Mi Perfil");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setFont(new java.awt.Font("Trebuchet MS", 1, 25));
        titulo.setForeground(Color.white);
        titulo.setMaximumSize(new Dimension(120, 50));
        
        usuario.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        usuario.setForeground(new Color(255, 105, 180)); // Color rosa más suave
        usuario.setBackground(Color.white);
        usuario.setMaximumSize(new Dimension(250, 40)); // Aumenta el tamaño para mejor visualización
        usuario.setHorizontalAlignment(JTextField.CENTER); // Centra el texto dentro del campo
        usuario.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.pink)); // Borde inferior rosa
        usuario.setText(usuarioActual.getNombre());
        usuario.setEditable(false);
        
        crear.setBackground(new Color(255, 105, 180));
        crear.setForeground(Color.WHITE);
        crear.setFont(new java.awt.Font("Trebuchet MS", 1, 17));
        crear.setAlignmentX(Component.CENTER_ALIGNMENT);
        crear.addActionListener(this);

        cerrarSesion.setBackground(new Color(255, 105, 180));
        cerrarSesion.setForeground(Color.WHITE);
        cerrarSesion.setFont(new java.awt.Font("Trebuchet MS", 1, 17));
        cerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        cerrarSesion.addActionListener(this);
        
        panel.add(Box.createVerticalStrut(40));
        panel.add(profileLabel);
        panel.add(Box.createVerticalStrut(10)); 
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10)); 
        panel.add(usuario);
        panel.add(Box.createVerticalStrut(25));
        panel.add(crear);
        panel.add(Box.createVerticalStrut(25)); 
        panel.add(cerrarSesion);

        add(Box.createVerticalStrut(20)); 
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (crear==e.getSource()){
        String nombre = JOptionPane.showInputDialog("Ingrese username nuevo:");
        String password = JOptionPane.showInputDialog("Ingrese contraseña");
        sistemaOperativo.crearUsuario(nombre, password);
        //SistemaOperativo.getInstance().crearUsuario(nombre, password);
        //Log.sistemaOperativo.getAdministrador().crearUsuario(nombre, password, sistemaOperativo);
        }
        if (cerrarSesion==e.getSource()){
            SistemaOperativo.getInstance().cerrarSesion();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            Log.setVisible(true);
            frame.setVisible(false);
        }
    }
}