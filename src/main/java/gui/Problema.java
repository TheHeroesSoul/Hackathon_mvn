package main.java.gui;

import javax.swing.*;
import java.awt.event.*;

public class Problema extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane textPane1;

    public Problema(String problema) {
        setTitle("Problema Hackathon");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Imposta il testo del problema nel JTextPane
        textPane1.setText(problema);
        textPane1.setEditable(false); // Rende il testo non editabile

        // Action listener per il bottone OK
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // Gestisce la chiusura della finestra
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        // Gestisce la pressione del tasto ESC
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
    }

    private void onOK() {
        dispose();
    }
}