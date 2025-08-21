package org.formulario;

import javax.swing.*;
import java.awt.*;

public class form extends JFrame {
    public form() {
        setTitle("Minha Janela");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Bem-vindo ao Swing!");
        JButton button = new JButton("Clique");
        panel.add(label);
        panel.add(button);
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new form());
    }
}
