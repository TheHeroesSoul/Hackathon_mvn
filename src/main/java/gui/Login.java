package main.java.gui;

import main.java.controller.Controller;
import main.java.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The type Login.
 */
public class Login extends JFrame {
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton enterButton;
    private JPanel loginPanel;


    /**
     * Instantiates a new Login.
     *
     * @param controller      the controller
     * @param isAuthenticated the is authenticated
     * @param tuttiUtenti     the tutti utenti
     */
    public Login(Controller controller, boolean isAuthenticated, List<Utente> tuttiUtenti) {
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        textField1 = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField1 = new JPasswordField();

        enterButton = new JButton("Login");

        loginPanel.add(usernameLabel);
        loginPanel.add(textField1);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField1);
        loginPanel.add(new JLabel());
        loginPanel.add(enterButton);

        setContentPane(loginPanel);
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        for (Utente utente : tuttiUtenti) {
            if (utente.getUsername().equals(usernameLabel) && utente.getPassword().equals(passwordLabel)) {
                utente = utente;
                isAuthenticated = true;
                break;
            }}


        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(passwordField1.getPassword());
                controller.login(username, password);
            }
        });

    }


}