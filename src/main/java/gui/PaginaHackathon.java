package main.java.gui;

import main.java.model.Hackathon;
import main.java.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaginaHackathon extends JDialog {
    private String creatoreUsername;
    private JPanel panel1;
    private JList utentiList;
    private JTextPane problemaTextPane;
    private JTable classificaTable;
    private JButton tornaDietroButton;
    private JButton creaDocumentoButton;
    private JList Utenti;
    private JTable Classifica;
    private JList list1;

    public PaginaHackathon(Window parent, Hackathon hackathon, Controller controller, Home homeView) {
        super(parent, "Dettagli Hackathon: " + hackathon.getTitolo(), ModalityType.APPLICATION_MODAL);
        this.creatoreUsername = hackathon.getCreatore();

        setSize(400, 300);
        setLocationRelativeTo(parent);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(new JLabel("Titolo: " + hackathon.getTitolo()));
        contentPane.add(new JLabel("Sede: " + hackathon.getSede()));
        contentPane.add(new JLabel("Creatore: " + hackathon.getCreatore()));
        contentPane.add(new JLabel("Problema: " + hackathon.getProblema()));

        setContentPane(contentPane);

        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (homeView != null) homeView.mostra();
            }
            @Override
            public void windowClosing(WindowEvent e) {
                if (homeView != null) homeView.mostra();
            }
        });

        setVisible(true);
    }

    public String getCreatoreUsername() {
        return creatoreUsername;
    }
}