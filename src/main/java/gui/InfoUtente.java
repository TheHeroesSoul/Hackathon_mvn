package main.java.gui;

import main.java.model.Utente;

import javax.swing.*;
import java.awt.*;

public class InfoUtente extends JFrame {
    private JPanel mainPanel;

    public InfoUtente(Utente utente) {
        setTitle("Info Utente");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Username: " + utente.getUsername());
        mainPanel.add(label, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }
}