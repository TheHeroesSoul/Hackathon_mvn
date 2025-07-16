package main.java.gui;

import javax.swing.*;
import java.awt.event.*;

/**
 * The type Problema.
 */
public class Problema extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane textPane1;

    /**
     * Instantiates a new Problema.
     *
     * @param problema the problema
     */
    public Problema(String problema) {
        setTitle("Problema Hackathon");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        textPane1.setText(problema);
        textPane1.setEditable(false);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

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