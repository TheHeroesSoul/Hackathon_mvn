package main.java.gui;

import javax.swing.*;
import java.awt.*;

public class Home {
    private JButton logoutButton;
    private JList list1;
    private JButton creaHackathonButton;

    public Home() {
        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());

        String[] dati = {"Elemento 1", "Elemento 2", "Elemento 3"};
        list1 = new JList(dati);
        mainPanel.add(new JScrollPane(list1), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        logoutButton = new JButton("Logout");
        creaHackathonButton = new JButton("Crea Hackathon");
        buttonPanel.add(logoutButton);
        buttonPanel.add(creaHackathonButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

}