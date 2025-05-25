package main.java.controller;

import main.java.gui.Login;
import main.java.model.Utente;

import javax.swing.*;

public class Controller {
    private Login loginView;
    private Utente authenticatedUser;

    public Controller() {
        this.loginView = new Login(this);
    }

    public void login(String username) {
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Username non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isAuthenticated = authenticateUser(username);

        if (isAuthenticated) {
            authenticatedUser = new Utente(username);
            JOptionPane.showMessageDialog(loginView, "Login effettuato per: " + username, "Successo", JOptionPane.INFORMATION_MESSAGE);
            loginView.dispose();
        } else {
            JOptionPane.showMessageDialog(loginView, "Credenziali non valide!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean authenticateUser(String username) {
        return !username.trim().isEmpty();
    }

    public static void main(String[] args) {
        new Controller();
    }
}