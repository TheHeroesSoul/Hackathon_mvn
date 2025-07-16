package main.java.gui;

import javax.swing.*;
import java.awt.*;

public class HomePartecipante extends JFrame {

    private JPanel panel1;
    private JList teamList;
    private JList utentiList;
    private JList invitoPanel;
    private JList list2;
    private JButton inviaDocumentiButton;
    private JTextPane hackathonBelloTextPane;
    private JButton backButton;

    public HomePartecipante() {
        setTitle("Home Partecipante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.add(mainPanel, BorderLayout.CENTER);

        JPanel sinistraPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        sinistraPanel.setBorder(BorderFactory.createTitledBorder("Team"));

        DefaultListModel<String> utentiModel = new DefaultListModel<>();
        utentiModel.addElement("Utente1");
        utentiModel.addElement("Utente2");
        JList<String> utentiList = new JList<>(utentiModel);
        sinistraPanel.add(new JScrollPane(utentiList));

        DefaultListModel<String> teamModel = new DefaultListModel<>();
        teamModel.addElement("Team1");
        teamModel.addElement("Team2");
        JList<String> teamList = new JList<>(teamModel);
        sinistraPanel.add(new JScrollPane(teamList));

        JPanel invitoPanel = new JPanel(new BorderLayout());
        invitoPanel.setBorder(BorderFactory.createTitledBorder("Invita al team"));
        DefaultListModel<String> liberiModel = new DefaultListModel<>();
        liberiModel.addElement("UtenteLibero1");
        liberiModel.addElement("UtenteLibero2");
        JList<String> liberiList = new JList<>(liberiModel);
        invitoPanel.add(new JScrollPane(liberiList), BorderLayout.CENTER);
        sinistraPanel.add(invitoPanel);

        JPanel destraPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel documentiPanel = new JPanel(new BorderLayout());
        documentiPanel.setBorder(BorderFactory.createTitledBorder("Documenti Inviati"));
        DefaultListModel<String> docModel = new DefaultListModel<>();
        docModel.addElement("Doc1");
        docModel.addElement("Doc2");
        JList<String> docList = new JList<>(docModel);
        documentiPanel.add(new JScrollPane(docList), BorderLayout.CENTER);

        JButton inviaDocButton = new JButton("Invia Documenti");
        inviaDocButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Documento inviato!")
        );
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(inviaDocButton);
        documentiPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informazioni Hackathon"));
        JTextArea infoArea = new JTextArea("Hackathon bello");
        infoArea.setEditable(false);
        infoPanel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        destraPanel.add(documentiPanel);
        destraPanel.add(infoPanel);

        mainPanel.add(sinistraPanel);
        mainPanel.add(destraPanel);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Torno indietro...");
            dispose();
        });
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(backButton);
        panel.add(backPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePartecipante::new);
    }
}