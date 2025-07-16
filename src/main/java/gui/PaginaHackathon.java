package main.java.gui;

import main.java.controller.Controller;
import main.java.model.Hackathon;
import main.java.model.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * The type Pagina hackathon.
 */
public class PaginaHackathon extends JDialog {

    private final Hackathon hackathon;
    private final Controller controller;

    private final JTextArea problemaArea = new JTextArea(5, 40);
    private final DefaultListModel<String> docModel = new DefaultListModel<>();
    private final JList<String> documentiList = new JList<>(docModel);

    private final JButton tornaIndietro = new JButton("Torna alla Home");
    private final JButton aggiungiDoc = new JButton("Upload documento");
    //private final JButton mostraProblema = new JButton("Mostra problema");
    private JButton invitaUtente = new JButton("Invita Utente");

    private JComboBox<Team> teamComboBox;
    private JSpinner votoSpinner;
    private JButton votaButton;
    private JPanel panel1;
    private JList Utenti;
    private JButton tornaIndietroButton;
    private JTextPane problemaTextPane;
    private JTable Classifica;
    private JList list1;
    private JButton creaDocumentoButton;
    private JPanel Votazioni;
    private JList teamHackathon;
    private JSpinner spinner1;
    private JTable classificaTable;
    private DefaultTableModel classificaModel;

    /**
     * Instantiates a new Pagina hackathon.
     *
     * @param parent     the parent
     * @param hackathon  the hackathon
     * @param controller the controller
     * @param homeView   the home view
     */
    public PaginaHackathon(Window parent,
                           Hackathon hackathon,
                           Controller controller,
                           Home homeView) {
        super(parent,
                "Dettagli Hackathon: " + hackathon.getTitolo(),
                ModalityType.APPLICATION_MODAL);

        this.hackathon = hackathon;
        this.controller = controller;

        buildUI();
        pack();
        setLocationRelativeTo(parent);

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosed(WindowEvent e)  {
                if (homeView != null) homeView.mostra(); }
        });

        setVisible(true);
    }

    private void buildUI() {
        JPanel info = new JPanel(new GridLayout(3, 2, 5, 5));
        info.setBorder(BorderFactory.createTitledBorder("Info evento"));
        info.add(new JLabel("Titolo:"));
        info.add(new JLabel(hackathon.getTitolo()));
        info.add(new JLabel("Sede:"));
        info.add(new JLabel(hackathon.getSede()));
        info.add(new JLabel("Creatore:"));
        info.add(new JLabel(hackathon.getCreatore()));

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        problemaArea.setText(controller.getDescrizioneProblema(hackathon));
        problemaArea.setEditable(false);
        problemaArea.setLineWrap(true);
        problemaArea.setWrapStyleWord(true);
        JScrollPane problemaScroll = new JScrollPane(problemaArea);
        problemaScroll.setBorder(BorderFactory.createTitledBorder("Problema"));

        JPanel docPanel = new JPanel(new BorderLayout());
        docPanel.setBorder(BorderFactory.createTitledBorder("Documenti caricati"));
        docPanel.add(new JScrollPane(documentiList), BorderLayout.CENTER);
        docPanel.add(aggiungiDoc, BorderLayout.SOUTH);

        centro.add(problemaScroll, BorderLayout.NORTH);
        centro.add(docPanel, BorderLayout.CENTER);

        JPanel votazioniPanel = new JPanel(new GridBagLayout());
        votazioniPanel.setBorder(BorderFactory.createTitledBorder("Votazioni"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        votazioniPanel.add(new JLabel("Seleziona Team:"), gbc);

        teamComboBox = new JComboBox<>();
        aggiornaListaTeam();
        gbc.gridx = 1; gbc.gridy = 0;
        votazioniPanel.add(teamComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        votazioniPanel.add(new JLabel("Inserisci voto (0-10):"), gbc);
        votoSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        gbc.gridx = 1; gbc.gridy = 1;
        votazioniPanel.add(votoSpinner, gbc);

        votaButton = new JButton("Vota");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        votazioniPanel.add(votaButton, gbc);

        // Tabella classifica
        String[] colonne = {"Team", "Somma Punteggi"};
        classificaModel = new DefaultTableModel(colonne, 0);
        classificaTable = new JTable(classificaModel);
        JScrollPane scrollClassifica = new JScrollPane(classificaTable);
        scrollClassifica.setBorder(BorderFactory.createTitledBorder("Classifica"));

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //south.add(mostraProblema);
        south.add(invitaUtente);
        south.add(tornaIndietro);

        JPanel east = new JPanel(new BorderLayout(10,10));
        east.add(votazioniPanel, BorderLayout.NORTH);
        east.add(scrollClassifica, BorderLayout.CENTER);

        setLayout(new BorderLayout(10, 10));
        add(info, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(east, BorderLayout.EAST);
        add(south, BorderLayout.SOUTH);

        tornaIndietro.addActionListener(e -> dispose());

        /*mostraProblema.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        controller.getDescrizioneProblema(hackathon),
                        "Problema dell’Hackathon",
                        JOptionPane.INFORMATION_MESSAGE));
        */


        aggiungiDoc.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog(this, "Nome del documento da caricare:");
            if (nome != null && !nome.isBlank()) {
                try {
                    controller.aggiungiDocumentoAlHackathon(hackathon, nome.trim());
                    aggiornaDocumenti();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Errore durante il caricamento: " + ex.getMessage());
                }
            }
        });

        invitaUtente.addActionListener(e -> {
            Invito invitiDialog = new Invito(this, controller, hackathon);
            invitiDialog.setVisible(true);
        });

        votaButton.addActionListener(e -> {
            Team selezionato = (Team) teamComboBox.getSelectedItem();
            int voto = (Integer) votoSpinner.getValue();
            if (selezionato == null) {
                JOptionPane.showMessageDialog(this, "Seleziona un team prima di votare.");
                return;
            }
            try {
                controller.registraVoto(hackathon, selezionato, voto);
                aggiornaClassifica();
                JOptionPane.showMessageDialog(this, "Voto registrato con successo!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore durante il voto: " + ex.getMessage());
            }
        });

        aggiornaDocumenti();
        aggiornaClassifica();
    }

    private void aggiornaDocumenti() {
        docModel.clear();
        List<String> documenti = controller.getDocumentiHackathon(hackathon);
        documenti.forEach(docModel::addElement);
    }

    private void aggiornaClassifica() {
        classificaModel.setRowCount(0);
        List<Team> classifica = controller.getClassificaSommaPunteggi(hackathon);
        for (Team t : classifica) {
            int somma = controller.calcolaSommaVoti(t);
            classificaModel.addRow(new Object[]{t.getNome(), somma});
        }
    }

    private void aggiornaListaTeam() {
        teamComboBox.removeAllItems();
        List<Team> teams = controller.getTeamsHackathon(hackathon);
        teams.forEach(teamComboBox::addItem);
    }
}