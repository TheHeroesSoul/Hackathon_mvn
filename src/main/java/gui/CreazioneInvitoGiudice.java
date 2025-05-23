package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreazioneInvitoGiudice extends JDialog {
    private JTextField nomeUtenteGiudiceField;
    private JTextArea messaggioMotivazionaleTextArea;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel contentPane;

    public CreazioneInvitoGiudice() {
        setTitle("Invita Giudice");
        setModal(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(new JLabel("Nome Utente Giudice:"), BorderLayout.WEST);
        nomeUtenteGiudiceField = new JTextField();
        topPanel.add(nomeUtenteGiudiceField, BorderLayout.CENTER);
        contentPane.add(topPanel, BorderLayout.NORTH);

        messaggioMotivazionaleTextArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(messaggioMotivazionaleTextArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        getRootPane().setDefaultButton(buttonOK);

        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreazioneInvitoGiudice dialog = new CreazioneInvitoGiudice();
            dialog.setVisible(true);
        });
    }
}