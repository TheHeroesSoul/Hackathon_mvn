package main.java.gui;

import main.java.model.Hackathon;
import main.java.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaginaHackathon extends JDialog {
    private String creatoreUsername;
    private JPanel panel1;
    private JList utentiList;
    private JTable classificaTable;
    private JButton tornaDietroButton;
    private JButton creaDocumentoButton;
    private JList Utenti;
    private JTable Classifica;
    private JList list1;
    private JButton problemaButton;

    private Hackathon hackathon;

    public PaginaHackathon(Window parent, Hackathon hackathon, Controller controller, Home homeView) {
        super(parent, "Dettagli Hackathon: " + hackathon.getTitolo(), ModalityType.APPLICATION_MODAL);
        this.creatoreUsername = hackathon.getCreatore();
        this.hackathon = hackathon;

        setSize(500, 400);
        setLocationRelativeTo(parent);
        setContentPane(panel1);

        setupActionListeners(controller, homeView);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (homeView != null) homeView.mostra();
            }
            @Override
            public void windowClosing(WindowEvent e) {
                if (homeView != null) homeView.mostra();
            }
        });

        setVisible(true);
    }

    private void setupActionListeners(Controller controller, Home homeView) {
        // Action listener per il bottone "Problema"
        problemaButton.addActionListener(e -> {
            String problema = hackathon.getProblema();

            // Debug: stampa per verificare che il problema sia presente
            System.out.println("Problema dell'hackathon: " + problema);

            if (problema == null || problema.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Nessun problema definito per questo hackathon.",
                        "Informazione",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Crea e mostra la finestra Problema
            Problema dialog = new Problema(problema);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        });

        // Action listener per il bottone "Torna dietro"
        tornaDietroButton.addActionListener(e -> {
            dispose();
            if (homeView != null) homeView.mostra();
        });

        creaDocumentoButton.addActionListener(e -> {
            controller.mostraDocumentiDialog(this, hackathon);
        });

    }

    public String getCreatoreUsername() {
        return creatoreUsername;
    }
}