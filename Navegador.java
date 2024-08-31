package frames;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Comparator;

public class Navegador extends JPanel{
    private Usuario usuario;
    private JTree tree;
    JButton ordenar = new JButton("Ordenar");
    JButton renombrar = new JButton("Renombrar");
    JButton crear = new JButton("Crear");
    JButton copiar = new JButton("Copiar");
    JButton pegar = new JButton("Pegar");
    
    public Navegador(Usuario usuario) {
        this.usuario = usuario;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(730, 480));     
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245)); 
        //JTree tree = createTree(usuario.getDirectorioRaiz());
        tree = new JTree(createTree());
        JScrollPane scrollPane = new JScrollPane(tree);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));
        buttonPanel.setBackground(new Color(255, 240, 245)); 

        JButton ordenar = createButton("Ordenar", e -> showSortOptions(tree));
        JButton renombrar = createButton("Renombrar", e -> renameNode(tree));
        JButton crear = createButton("Crear", e -> createNode(tree));
        JButton copiar = createButton("Copiar", e -> JOptionPane.showMessageDialog(null, ""));
        JButton pegar = createButton("Pegar", e -> JOptionPane.showMessageDialog(null, ""));

        buttonPanel.add(ordenar);
        buttonPanel.add(renombrar);
        buttonPanel.add(crear);
        buttonPanel.add(copiar);
        buttonPanel.add(pegar);

        panel.add(buttonPanel, BorderLayout.NORTH);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private DefaultMutableTreeNode createTree() {
        // Nodo raíz con el nombre del usuario
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(usuario.getNombre());

        // Agregar los folders del usuario actual
        for (String folder : usuario.getFolders()) {
            DefaultMutableTreeNode folderNode = new DefaultMutableTreeNode(folder);
            root.add(folderNode);
        }

        return root;
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(new Color(255, 182, 193)); // Rosa claro
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.addActionListener(action);
        return button;
    }

    private void showSortOptions(JTree tree) {
        String[] options = {"Sort by Name", "Sort by Date", "Sort by Type", "Sort by Size"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose sorting criteria:",
                "Sort Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            sortTree(tree, "name");
        }
    }

    private void sortTree(JTree tree, String criteria) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        DefaultMutableTreeNode[] children = getChildren(root);

        Arrays.sort(children, new Comparator<DefaultMutableTreeNode>() {
            @Override
            public int compare(DefaultMutableTreeNode n1, DefaultMutableTreeNode n2) {
                if (criteria.equals("name")) {
                    return n1.toString().compareTo(n2.toString());
                }
                return 0;
            }
        });

        root.removeAllChildren();
        for (DefaultMutableTreeNode child : children) {
            root.add(child);
        }

        ((DefaultTreeModel) tree.getModel()).reload();
    }

    private DefaultMutableTreeNode[] getChildren(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode[] children = new DefaultMutableTreeNode[node.getChildCount()];
        for (int i = 0; i < node.getChildCount(); i++) {
            children[i] = (DefaultMutableTreeNode) node.getChildAt(i);
        }
        return children;
    }

    private void renameNode(JTree tree) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            String newName = JOptionPane.showInputDialog(null, "Enter new name:", selectedNode.getUserObject().toString());
            if (newName != null && !newName.trim().isEmpty()) {
                selectedNode.setUserObject(newName);
                ((DefaultTreeModel) tree.getModel()).nodeChanged(selectedNode);
            }
        }
    }

    private void createNode(JTree tree) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            String newName = JOptionPane.showInputDialog(null, "Enter name for new node:");
            if (newName != null && !newName.trim().isEmpty()) {
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newName);
                selectedNode.add(newNode);
                ((DefaultTreeModel) tree.getModel()).reload();
            }
        }
    }
   
    public static void main(String[] args) {
    }
}