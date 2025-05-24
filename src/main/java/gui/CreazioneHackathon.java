package main.java.gui;

import main.java.model.Utente;

import javax.swing.*;
import java.awt.*;

public class CreazioneHackathon extends JDialog {
    private JTextField titoloField;
    private JTextField sedeField;
    private JTextField dataInizioField;
    private JTextField dataFineField;
    private JTextField maxIscrittiField;
    private JTextField maxComponentiField;
    private JTextField dataInizioIscrizioniField;
    private JTextArea problemaArea;
    private JButton invitaGiudiceButton;
    private JButton buttonOK;
    private JButton buttonCancel;

    public CreazioneHackathon(Utente utente) {
        setTitle("Creazione Hackathon");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPane.add(new JLabel("Titolo:"));
        titoloField = new JTextField();
        contentPane.add(titoloField);

        contentPane.add(Box.createVerticalStrut(10));

        contentPane.add(new JLabel("Sede:"));
        sedeField = new JTextField();
        contentPane.add(sedeField);

        contentPane.add(Box.createVerticalStrut(10));

        contentPane.add(new JLabel("Data Inizio:"));
        dataInizioField = new JTextField();
        contentPane.add(dataInizioField);

        contentPane.add(Box.createVerticalStrut(10));

        contentPane.add(new JLabel("Data Fine:"));
        dataFineField = new JTextField();
        contentPane.add(dataFineField);

        contentPane.add(Box.createVerticalStrut(10));

        contentPane.add(new JLabel("Max Iscritti:"));
        maxIscrittiField = new JTextField();
        contentPane.add(maxIscrittiField);

        contentPane.add(Box.createVerticalStrut(10));

        contentPane.add(new JLabel("Max Componenti Team:"));
        maxComponentiField = new JTextField();
        contentPane.add(maxComponentiField);

        contentPane.add(Box.createVerticalStrut(10));

        contentPane.add(new JLabel("Data Inizio Iscrizioni:"));
        dataInizioIscrizioniField = new JTextField();
        contentPane.add(dataInizioIscrizioniField);

        contentPane.add(Box.createVerticalStrut(10));

        contentPane.add(new JLabel("Problema:"));
        problemaArea = new JTextArea(4, 20);
        JScrollPane scrollPane = new JScrollPane(problemaArea);
        contentPane.add(scrollPane);

        contentPane.add(Box.createVerticalStrut(10));

        invitaGiudiceButton = new JButton("Invita Giudice");
        contentPane.add(invitaGiudiceButton);

        contentPane.add(Box.createVerticalStrut(10));

        JPanel buttonsPanel = new JPanel();
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        buttonsPanel.add(buttonOK);
        buttonsPanel.add(buttonCancel);

        contentPane.add(buttonsPanel);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        setContentPane(contentPane);
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Utente u = new Utente("pippoepluto");
            CreazioneHackathon dialog = new CreazioneHackathon(u);
            dialog.setVisible(true);
        });
    }
}