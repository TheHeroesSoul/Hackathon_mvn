package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private JPanel contentPane;

    public CreazioneHackathon() {
        setTitle("Creazione Hackathon");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(new JLabel("Titolo"), gbc);
        gbc.gridx = 1;
        titoloField = new JTextField(20);
        formPanel.add(titoloField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("Specifiche"), gbc);
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Sede"), gbc);
        gbc.gridx = 1;
        sedeField = new JTextField();
        formPanel.add(sedeField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Data Inizio"), gbc);
        gbc.gridx = 1;
        dataInizioField = new JTextField();
        formPanel.add(dataInizioField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Data Fine"), gbc);
        gbc.gridx = 1;
        dataFineField = new JTextField();
        formPanel.add(dataFineField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Max Iscritti"), gbc);
        gbc.gridx = 1;
        maxIscrittiField = new JTextField();
        formPanel.add(maxIscrittiField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Max Componenti Team"), gbc);
        gbc.gridx = 1;
        maxComponentiField = new JTextField();
        formPanel.add(maxComponentiField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Data Inizio Iscrizioni"), gbc);
        gbc.gridx = 1;
        dataInizioIscrizioniField = new JTextField();
        formPanel.add(dataInizioIscrizioniField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Problema"), gbc);
        gbc.gridx = 1;
        problemaArea = new JTextArea(4, 20);
        formPanel.add(new JScrollPane(problemaArea), gbc);

        contentPane.add(formPanel, BorderLayout.CENTER);

        invitaGiudiceButton = new JButton("Invita Giudice");
        JPanel centerBottomPanel = new JPanel();
        centerBottomPanel.add(invitaGiudiceButton);
        contentPane.add(centerBottomPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        contentPane.add(buttonPanel, BorderLayout.PAGE_END);

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
            CreazioneHackathon dialog = new CreazioneHackathon();
            dialog.setVisible(true);
        });
    }
}