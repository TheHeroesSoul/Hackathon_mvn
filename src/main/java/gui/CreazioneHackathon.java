package main.java.gui;

import main.java.model.Utente;

import javax.swing.*;

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

        contentPane.add(new JLabel("Data Inizio (dd/MM/yyyy):"));
        dataInizioField = new JTextField();
        contentPane.add(dataInizioField);

        contentPane.add(Box.createVerticalStrut(10));

        contentPane.add(new JLabel("Data Fine (dd/MM/yyyy):"));
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

        contentPane.add(new JLabel("Data Inizio Iscrizioni (dd/MM/yyyy):"));
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
        StringBuilder missingFields = new StringBuilder();
        String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";

        if (titoloField.getText().trim().isEmpty()) missingFields.append("- Titolo\n");
        if (sedeField.getText().trim().isEmpty()) missingFields.append("- Sede\n");
        if (dataInizioField.getText().trim().isEmpty()) missingFields.append("- Data Inizio\n");
        if (dataFineField.getText().trim().isEmpty()) missingFields.append("- Data Fine\n");
        if (maxIscrittiField.getText().trim().isEmpty()) missingFields.append("- Max Iscritti\n");
        if (maxComponentiField.getText().trim().isEmpty()) missingFields.append("- Max Componenti Team\n");
        if (dataInizioIscrizioniField.getText().trim().isEmpty()) missingFields.append("- Data Inizio Iscrizioni\n");
        if (problemaArea.getText().trim().isEmpty()) missingFields.append("- Problema\n");

        if (missingFields.length() > 0) {
            JOptionPane.showMessageDialog(this,
                    "Compila tutti i campi. Mancano:\n" + missingFields,
                    "Campi mancanti",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!dataInizioField.getText().matches(datePattern)) {
            JOptionPane.showMessageDialog(this,
                    "La Data Inizio deve essere nel formato dd/MM/yyyy",
                    "Formato data non valido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!dataFineField.getText().matches(datePattern)) {
            JOptionPane.showMessageDialog(this,
                    "La Data Fine deve essere nel formato dd/MM/yyyy",
                    "Formato data non valido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!dataInizioIscrizioniField.getText().matches(datePattern)) {
            JOptionPane.showMessageDialog(this,
                    "La Data Inizio Iscrizioni deve essere nel formato dd/MM/yyyy",
                    "Formato data non valido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Hackathon creato", "Successo", JOptionPane.INFORMATION_MESSAGE);
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