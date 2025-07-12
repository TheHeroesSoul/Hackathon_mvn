package main.java.gui;

import javax.swing.*;

public class Home {
    private JButton logoutButton;
    private JList list1;
    private JButton creaHackathonButton;

    public Home() {
        JFrame frame = new JFrame("Home");
        frame.setContentPane(new JPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        logoutButton = new JButton("Logout");
        creaHackathonButton = new JButton("Crea Hackathon");

        JPanel panel = (JPanel) frame.getContentPane();
        panel.add(logoutButton);
        panel.add(creaHackathonButton);

    }

    public static void main(String[] args) {
        new Home();
    }
}