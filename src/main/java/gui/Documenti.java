package main.java.gui;

import main.java.controller.Controller;
import main.java.model.Hackathon;
import javax.swing.*;
import java.awt.*;

public class Documenti extends JDialog {
    private JList<String> documentiList;
    private JPanel mainPanel;
    private JButton aggiungiButton;
    private DefaultListModel<String> listModel;
    private JList list1;
    private JPanel panel1;


    public Documenti(Window parent, Hackathon hackathon, Controller controller) {
        super(parent, "Documenti - " + hackathon.getTitolo(), ModalityType.APPLICATION_MODAL);

        setSize(400, 300);
        setLocationRelativeTo(parent);

        listModel = new DefaultListModel<>();
        documentiList = new JList<>(listModel);
        mainPanel = new JPanel(new BorderLayout());
        aggiungiButton = new JButton("Aggiungi Documento");

        // Layout
        mainPanel.add(new JScrollPane(documentiList), BorderLayout.CENTER);
        mainPanel.add(aggiungiButton, BorderLayout.SOUTH);
        setContentPane(mainPanel);

        // Action listener
        aggiungiButton.addActionListener(e -> {
            String nomeDoc = JOptionPane.showInputDialog(
                    this,
                    "Nome documento:",
                    "Nuovo Documento",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (nomeDoc != null && !nomeDoc.trim().isEmpty()) {
                listModel.addElement(nomeDoc.trim());
            }
        });

        aggiornaDocumenti(hackathon);
    }

    public void aggiornaDocumenti(Hackathon hackathon) {
        listModel.clear();

    }
}