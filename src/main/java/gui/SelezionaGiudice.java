package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import main.java.model.Hackathon;
import main.java.model.Utente;

/**
 * The type Seleziona giudice.
 */
public class SelezionaGiudice extends JDialog {
    private Hackathon hackathon;
    private List<Utente> utentiDisponibili;
    private JList<Utente> listaUtenti;
    private JList list1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton cercaButton;
    private JButton confermaButton;
    private JButton cancellaButton;

    /**
     * Instantiates a new Seleziona giudice.
     *
     * @param parent            the parent
     * @param hackathon         the hackathon
     * @param utentiDisponibili the utenti disponibili
     */
    public SelezionaGiudice(java.awt.Window parent, Hackathon hackathon, List<Utente> utentiDisponibili) {
        super(parent, "Seleziona Giudice", ModalityType.APPLICATION_MODAL);
        this.hackathon = hackathon;
        this.utentiDisponibili = utentiDisponibili;

        listaUtenti = new JList<>(utentiDisponibili.toArray(new Utente[0]));
        listaUtenti.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JButton confermaButton = new JButton("Conferma");

        confermaButton.addActionListener(e -> {
            List<Utente> selezionati = listaUtenti.getSelectedValuesList();
            if (!selezionati.isEmpty()) {
                hackathon.setGiudici(selezionati); // Ora accetta List<Utente>
                JOptionPane.showMessageDialog(this, "Giudici selezionati: " + selezionati.size());
                dispose();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Seleziona giudici:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(listaUtenti), BorderLayout.CENTER);
        panel.add(confermaButton, BorderLayout.SOUTH);

        setContentPane(panel);
        setSize(300, 300);
        setLocationRelativeTo(parent);
    }
}