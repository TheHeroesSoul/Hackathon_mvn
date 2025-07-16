package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InvitoTeam extends JDialog {
    private JTextPane intestazioneTextPane;
    private JTextPane messaggioTextPane;
    private JButton accettaButton;
    private JButton standbyButton;
    private JButton rifiutaButton;
    private JPanel contentPane;

    public InvitoTeam(JFrame parent) {
        super(parent, "Invito Team", true);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        intestazioneTextPane = new JTextPane();
        intestazioneTextPane.setText("Utente1 Ti ha invitato al Team!");
        intestazioneTextPane.setEditable(false);
        intestazioneTextPane.setBackground(null);
        intestazioneTextPane.setBorder(null);
        contentPane.add(intestazioneTextPane, BorderLayout.NORTH);

        messaggioTextPane = new JTextPane();
        messaggioTextPane.setText("Ehi Pippo, entra nel team per lavorare insieme al progetto!");
        messaggioTextPane.setEditable(false);
        messaggioTextPane.setBackground(null);
        messaggioTextPane.setBorder(BorderFactory.createTitledBorder("Messaggio"));
        contentPane.add(messaggioTextPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        accettaButton = new JButton("ACCETTA");
        standbyButton = new JButton("STAND BY");
        rifiutaButton = new JButton("RIFIUTA");

        buttonPanel.add(accettaButton);
        buttonPanel.add(standbyButton);
        buttonPanel.add(rifiutaButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setSize(500, 250);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        accettaButton.addActionListener(e -> {
            System.out.println("Hai accettato l'invito al team.");
            dispose();
        });

        standbyButton.addActionListener(e -> {
            System.out.println("Hai messo l'invito in standby.");
            dispose();
        });

        rifiutaButton.addActionListener(e -> {
            System.out.println("Hai rifiutato l'invito al team.");
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InvitoTeam dialog = new InvitoTeam(null);
            dialog.setVisible(true);
            System.exit(0);
        });
    }
}