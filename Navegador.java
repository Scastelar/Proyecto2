package frames;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Comparator;

public class Navegador extends JPanel{
    private Usuario usuario;
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
        
        JTree tree = createTree(usuario.getDirectorioRaiz());
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

    private JTree createTree(Directorio raiz) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(raiz.getName());
        DefaultMutableTreeNode folder1 = new DefaultMutableTreeNode("Folder1");
        DefaultMutableTreeNode folder2 = new DefaultMutableTreeNode("Folder2");

        root.add(folder1);
        root.add(folder2);
        
        folder1.add(new DefaultMutableTreeNode("File1.txt"));
        folder1.add(new DefaultMutableTreeNode("File2.docx"));
        folder2.add(new DefaultMutableTreeNode("File3.pdf"));
        folder2.add(new DefaultMutableTreeNode("File4.png"));
        
        JTree tree = new JTree(root);
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        
        return tree;
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
