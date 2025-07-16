package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlloDocs extends JDialog {
    private JList<String> list1;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonInvia;
    private JButton buttonBack;

    public ControlloDocs() {
        setTitle("Controllo Documenti");
        setModal(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());

        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Documento1.pdf");
        model.addElement("Documento2.pdf");
        model.addElement("Documento3.pdf");
        list1 = new JList<>(model);
        contentPane.add(new JScrollPane(list1), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonInvia = new JButton("Invia");
        buttonBack = new JButton("Back");

        buttonInvia.addActionListener(e -> onInvia());
        buttonBack.addActionListener(e -> onCancel());

        buttonPanel.add(buttonInvia);
        buttonPanel.add(buttonBack);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    private void onInvia() {
        String selected = list1.getSelectedValue();
        if (selected != null) {
            JOptionPane.showMessageDialog(this, "Documento inviato: " + selected);
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona un documento da inviare.");
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControlloDocs dialog = new ControlloDocs();
            dialog.setVisible(true);
        });
    }
}