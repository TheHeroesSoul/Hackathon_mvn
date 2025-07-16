package main.java.gui;

import main.java.controller.Controller;
import main.java.model.Hackathon;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type Documenti.
 */
public class Documenti extends JDialog {
    private JList<String> documentiList;
    private JPanel mainPanel;
    private JButton aggiungiButton;
    private DefaultListModel<String> listModel;
    private JList list1;
    private JPanel panel1;
    private Hackathon hackathon;
    private Controller controller;


    /**
     * Instantiates a new Documenti.
     *
     * @param parent     the parent
     * @param hackathon  the hackathon
     * @param controller the controller
     */
    public Documenti(Window parent, Hackathon hackathon, Controller controller) {
        super(parent, "Documenti - " + hackathon.getTitolo(), ModalityType.APPLICATION_MODAL);
        this.hackathon = hackathon;
        this.controller = controller;

        setSize(400, 300);
        setLocationRelativeTo(parent);

        listModel = new DefaultListModel<>();
        documentiList = new JList<>(listModel);
        mainPanel = new JPanel(new BorderLayout());
        aggiungiButton = new JButton("Aggiungi Documento");


        mainPanel.add(new JScrollPane(documentiList), BorderLayout.CENTER);
        mainPanel.add(aggiungiButton, BorderLayout.SOUTH);
        setContentPane(mainPanel);


        aggiungiButton.addActionListener(e -> {
            String nomeDoc = JOptionPane.showInputDialog(
                    this,
                    "Nome documento:",
                    "Nuovo Documento",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (nomeDoc != null && !nomeDoc.trim().isEmpty()) {

                controller.aggiungiDocumentoAlHackathon(hackathon, nomeDoc.trim());

                aggiornaDocumenti();
            }
        });

        aggiornaDocumenti();
    }

    /**
     * Aggiorna documenti.
     */
    public void aggiornaDocumenti() {
        listModel.clear();
        List<String> documenti = controller.getDocumentiHackathon(hackathon);
        for (String doc : documenti) {
            listModel.addElement(doc);

    }
}
}