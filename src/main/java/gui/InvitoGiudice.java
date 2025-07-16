package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InvitoGiudice extends JDialog {
    private JTextPane intestazioneTextPane;
    private JTextPane messaggioTextPane;
    private JButton accettaButton;
    private JButton standbyButton;
    private JButton rifiutaButton;
    private JPanel mainPanel;

    public InvitoGiudice(JFrame parent) {
        super(parent, "Invito Giudice", true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        intestazioneTextPane = new JTextPane();
        intestazioneTextPane.setText("L'organizzatore1 ti ha invitato a partecipare all'Hackathon1 come Giudice!");
        intestazioneTextPane.setEditable(false);
        intestazioneTextPane.setBackground(null);
        intestazioneTextPane.setBorder(null);
        mainPanel.add(intestazioneTextPane, BorderLayout.NORTH);

        messaggioTextPane = new JTextPane();
        messaggioTextPane.setText("Ciao Tizio Caio Sempronio, vuoi giudicare questo Hackathon?");
        messaggioTextPane.setEditable(false);
        messaggioTextPane.setBackground(null);
        messaggioTextPane.setBorder(BorderFactory.createTitledBorder("Messaggio"));
        mainPanel.add(messaggioTextPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));

        accettaButton = new JButton("ACCETTA");
        standbyButton = new JButton("STAND BY");
        rifiutaButton = new JButton("RIFIUTA");

        buttonPanel.add(accettaButton);
        buttonPanel.add(standbyButton);
        buttonPanel.add(rifiutaButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setSize(500, 250);
        setLocationRelativeTo(parent);

        accettaButton.addActionListener(e -> {
            System.out.println("Hai accettato l'invito.");
            dispose();
        });

        standbyButton.addActionListener(e -> {
            System.out.println("Hai messo l'invito in standby.");
            dispose();
        });

        rifiutaButton.addActionListener(e -> {
            System.out.println("Hai rifiutato l'invito.");
            dispose();
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        mainPanel.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InvitoGiudice dialog = new InvitoGiudice(null);
            dialog.setVisible(true);
            System.exit(0);
        });
    }
}