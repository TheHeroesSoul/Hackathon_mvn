package main.java.gui;

import main.java.model.Utente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {
    private JPanel mainPanel;
    private JButton infoUtenteButton;
    private JButton creaHackathonButton;
    private JButton logOutButton;
    private JTable table1; // Hackathons
    private JTable table2; // Inviti

    private Utente utente;

    public Home(Utente utente) {
        this.utente = utente;

        // Pannello principale
        mainPanel = new JPanel(new BorderLayout());

        // Pannello con i bottoni in alto
        JPanel topPanel = new JPanel();
        infoUtenteButton = new JButton("Info Utente");
        creaHackathonButton = new JButton("Crea Hackathon");
        logOutButton = new JButton("Logout");
        topPanel.add(infoUtenteButton);
        topPanel.add(creaHackathonButton);
        topPanel.add(logOutButton);

        // Tabelle
        table1 = new JTable();
        table2 = new JTable();

        // Split tra hackathon e inviti
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(table1),
                new JScrollPane(table2));
        splitPane.setDividerLocation(250);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        setTitle("Home - " + utente.getUsername());
        setContentPane(mainPanel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inizializzaTabelle();
        configuraAzioni();
    }

    private void inizializzaTabelle() {
        String[] colonne = {"Nome", "Tema"};

        Object[][] datiInviti = {
                {"Hackathon AI", "Intelligenza Artificiale"},
                {"Hackathon Cyber", "Cybersecurity"}
        };

        Object[][] datiHackathon = {
                {"Hackathon Fintech", "Finanza"},
                {"Hackathon Green", "Sostenibilità"}
        };

        table2.setModel(new DefaultTableModel(datiInviti, colonne));
        table1.setModel(new DefaultTableModel(datiHackathon, colonne));
    }

    private void configuraAzioni() {
        infoUtenteButton.addActionListener(e -> {
            new InfoUtente(utente).setVisible(true);
        });

        creaHackathonButton.addActionListener(e -> {
            new CreazioneHackathon(utente).setVisible(true);
        });

        logOutButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        table1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table1.getSelectedRow() != -1) {
                    String nomeHackathon = (String) table1.getValueAt(table1.getSelectedRow(), 0);
                    new GestioneHackathon(utente, nomeHackathon).setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Utente u = new Utente("MarioRossi");
            new Home(u).setVisible(true);
        });
    }
}