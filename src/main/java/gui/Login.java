package main.java.gui;

import main.java.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPasswordField passwordField1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton enterButton;

    public Login(Controller controller) {
        setContentPane(panel1);
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(passwordField1.getPassword());
                controller.login(username);
            }
        });
    }
}
