package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import main.java.model.Giudice;
import main.java.model.Hackathon;

public class SelezionaGiudice extends JDialog {
    private Hackathon hackathon;
    private List<Giudice> giudiciDisponibili;
    private JList<Giudice> listaGiudici;
    private JButton confermaButton;
    private JList list1;
    private JTextField textField1;
    private JButton cercaButton;
    private JButton cancellaButton;
    private JPanel panel1;

    public SelezionaGiudice(java.awt.Window parent, Hackathon hackathon, List<Giudice> giudiciDisponibili) {
        super(parent, "Seleziona Giudice", ModalityType.APPLICATION_MODAL);
        this.hackathon = hackathon;
        this.giudiciDisponibili = giudiciDisponibili;

        listaGiudici = new JList<>(giudiciDisponibili.toArray(new Giudice[0]));
        listaGiudici.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        confermaButton = new JButton("Conferma");

        confermaButton.addActionListener(e -> {
            List<Giudice> selezionati = listaGiudici.getSelectedValuesList();
            if (!selezionati.isEmpty()) {
                hackathon.setGiudici(selezionati); // Implementa questo metodo in Hackathon
                JOptionPane.showMessageDialog(this, "Giudici selezionati: " + selezionati.size());
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Seleziona un giudice:"));
        panel.add(new JScrollPane(listaGiudici));
        panel.add(confermaButton);

        setContentPane(panel);
        setSize(300, 300);
        setLocationRelativeTo(parent);
    }
}