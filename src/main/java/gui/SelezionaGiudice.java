package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import main.java.model.Giudice;
import main.java.model.Hackathon;

public class SelezionaGiudice extends JDialog {
    private JButton cancellaButton;
    private JButton confermaButton;
    private JList<Giudice> list1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton cercaButton;
    private Hackathon hackathon;


    public SelezionaGiudice(CreazioneHackathon parent, Hackathon hackathon, List<Giudice> giudiciDisponibili) {
        super(parent, "Seleziona Giudici", true); // Modale
        this.hackathon = hackathon;

        JPanel panel = new JPanel(new BorderLayout());
        list1 = new JList<>(giudiciDisponibili.toArray(new Giudice[0]));
        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel.add(new JScrollPane(list1), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        confermaButton = new JButton("Conferma");
        cancellaButton = new JButton("Cancella");
        buttonPanel.add(confermaButton);
        buttonPanel.add(cancellaButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        confermaButton.addActionListener(e -> {
            List<Giudice> selezionati = list1.getSelectedValuesList();
            for (Giudice g : selezionati) {
                hackathon.aggiungiGiudice(g);
            }
            dispose();
        });

        cancellaButton.addActionListener(e -> dispose());

        setContentPane(panel);
        setSize(300, 400);
        setLocationRelativeTo(parent);
    }

}