package main.java.gui;

import main.java.controller.Controller;
import main.java.model.Hackathon;
import main.java.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type Invito.
 */
public class Invito extends JDialog {

    private final Controller controller;
    private final Hackathon hackathon;

    private final DefaultListModel<String> utentiModel = new DefaultListModel<>();
    private final JList<String> utentiList = new JList<>(utentiModel);
    private JButton invitaButton = new JButton("Invita selezionati");
    private JList list1;
    private JPanel contentPane;
    private JButton buttonInvita;

    /**
     * Instantiates a new Invito.
     *
     * @param parent     the parent
     * @param controller the controller
     * @param hackathon  the hackathon
     */
    public Invito(Window parent, Controller controller, Hackathon hackathon) {
        super(parent, "Invita utenti a " + hackathon.getTitolo(), ModalityType.APPLICATION_MODAL);
        this.controller = controller;
        this.hackathon = hackathon;

        buildUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void buildUI() {
        setLayout(new BorderLayout(10, 10));

        utentiModel.clear();
        List<Utente> utentiDisponibili = controller.getTuttiUtenti();

        for (Utente u : utentiDisponibili) {
            if (!hackathon.isUtenteInvitato(u) && !hackathon.isUtenteIscritto(u)) {
                utentiModel.addElement(u.getUsername());
            }
        }

        utentiList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(utentiList), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(invitaButton);
        add(southPanel, BorderLayout.SOUTH);

        invitaButton.addActionListener(e -> {
            var selezionati = utentiList.getSelectedValuesList();
            if (selezionati.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleziona almeno un utente da invitare.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int invitati = 0;
            for (String username : selezionati) {
                boolean inviato = controller.inviaInvito(hackathon, username);
                if (inviato) invitati++;
            }
            JOptionPane.showMessageDialog(this, "Invitati " + invitati + " utenti con successo.", "Inviti inviati", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }
}