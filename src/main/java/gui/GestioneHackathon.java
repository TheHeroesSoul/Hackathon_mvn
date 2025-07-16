package main.java.gui;

import main.java.model.Utente;

import javax.swing.*;
import java.awt.*;

public class GestioneHackathon extends JFrame {
    private JPanel panel;
    private JTextField cercaField;
    private JList<String> partecipantiList;
    private JButton ricercaButton;
    private JComboBox<String> filtraComboBox;
    private JButton backButton;

    public GestioneHackathon(Utente utente, String nomeHackathon) {
        setTitle("Gestione - " + nomeHackathon);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout(10, 10));
        JPanel topPanel = new JPanel();
        cercaField = new JTextField(15);
        ricercaButton = new JButton("Cerca");
        filtraComboBox = new JComboBox<>(new String[]{"Tutti", "Team", "Giudici", "Utenti"});

        topPanel.add(new JLabel("Cerca:"));
        topPanel.add(cercaField);
        topPanel.add(ricercaButton);
        topPanel.add(filtraComboBox);

        partecipantiList = new JList<>(new String[]{"Mario", "Luca", "Anna"});
        JScrollPane scrollPane = new JScrollPane(partecipantiList);

        backButton = new JButton("Indietro");
        backButton.addActionListener(e -> dispose());

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        setContentPane(panel);
    }
}