package frames;

import cmd.CMD;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class Windows extends JFrame implements ActionListener {
    JButton perfil = new JButton();
    JButton docs = new JButton(); 
    JButton cmd = new JButton(); 
    JButton music = new JButton(); 
    JButton font = new JButton(); 
    JButton visor = new JButton(); 
    JButton ig = new JButton(); 
    JToolBar barra = new JToolBar(JToolBar.VERTICAL);
    JPanel contentPanel = new JPanel(new CardLayout());
    JLabel defaultLabel = new JLabel();
    Login Log;

    // Referencias a los paneles para evitar recrearlos
    PerfilPanel perfilPanel;
    CmdPanel cmdPanel;
    NavegadorPanel navegadorPanel;

    public Windows(Login Log) {
        this.Log = Log;
        setTitle("Windows");
        setSize(730, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(this);

        barra.setBackground(new Color(250, 250, 250));
        barra.setFloatable(false);

        setupButton(perfil, "src\\imgs\\user.png");
        setupButton(docs, "src\\imgs\\folder.png");
        setupButton(cmd, "src\\imgs\\cmd.png");
        setupButton(music, "src\\imgs\\music.png");
        setupButton(font, "src\\imgs\\adobe-fonts.png");
        setupButton(visor, "src\\imgs\\slideshow.png");
        setupButton(ig, "src\\imgs\\instagram.png");

        JPanel barra2 = new JPanel();
        barra2.setBackground(new Color(251, 216, 227)); 
        barra2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        String fechaActual = getFecha();
        JLabel fechaLabel = new JLabel(fechaActual);
        fechaLabel.setForeground(Color.WHITE);
        fechaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        barra2.add(fechaLabel);

        // Crear paneles una sola vez
        perfilPanel = new PerfilPanel();
        cmdPanel = new CmdPanel();
        navegadorPanel = new NavegadorPanel();

        contentPanel.add(defaultLabel, "default");
        contentPanel.add(perfilPanel, "perfil");
        contentPanel.add(cmdPanel, "cmd");
        contentPanel.add(navegadorPanel, "navegador");

        contentPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                updateBackgroundImage();  // Redimensionar imagen cuando el panel cambie de tamaño
            }
        });
        
        add(barra, BorderLayout.WEST);
        add(barra2, BorderLayout.SOUTH);
        add(contentPanel, BorderLayout.CENTER);
        SwingUtilities.invokeLater(() -> updateBackgroundImage());
        setVisible(true);
    }

    private String getFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm a - dd MMM yyyy");
        Date date = new Date();
        return formato.format(date);
    }
    
     private void updateBackgroundImage() {
        // Verificar que las dimensiones del panel sean válidas
        if (contentPanel.getWidth() > 0 && contentPanel.getHeight() > 0) {
            ImageIcon originalIcon = new ImageIcon("src\\imgs\\bg.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(contentPanel.getWidth(), contentPanel.getHeight(), Image.SCALE_SMOOTH);
            defaultLabel.setIcon(new ImageIcon(scaledImage));
            defaultLabel.repaint();
        }
    }
    
    private void setupButton(JButton button, String iconPath) {
        button.setSize(40, 40);
        button.setBackground(new Color(250, 250, 250)); 
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.addActionListener(this);
        setImageLabel(button, iconPath);
        barra.add(button);
        barra.add(Box.createVerticalStrut(10));
    }

    private void setImageLabel(JButton name, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(name.getWidth(), name.getHeight(), Image.SCALE_DEFAULT));
        name.setIcon(icon);
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardActual = (CardLayout) (contentPanel.getLayout());
          
        if (e.getSource() == perfil) {
            cardActual.show(contentPanel, "perfil");
        } 
        else if (e.getSource() == cmd) {
            cardActual.show(contentPanel, "cmd");
        } 
        else if (e.getSource() == docs) {
            cardActual.show(contentPanel, "navegador");
        } 
    }

    class PerfilPanel extends JPanel {
        PerfilPanel() {
            setLayout(new BorderLayout());   
            Perfil perfilPanel = new Perfil(Log);  
            this.add(perfilPanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
    }

    class CmdPanel extends JPanel {
        CmdPanel() {
            Navegador nav = new Navegador(SistemaOperativo.getInstance().getUsuarioActual());
            setLayout(new BorderLayout());   
            CMD frame = new CMD(SistemaOperativo.getInstance().getUsuarioActual());
            JPanel panel = frame.getJFrame();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
            this.add(panel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
    }
    
    class NavegadorPanel extends JPanel {
        NavegadorPanel(){
            setLayout(new BorderLayout());   
            Navegador frame = new Navegador(SistemaOperativo.getInstance().getUsuarioActual());
            this.add(frame, BorderLayout.CENTER);
            this.revalidate();
            this.repaint(); 
        }
    }
    
}
