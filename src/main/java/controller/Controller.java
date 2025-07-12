package main.java.controller;
import main.java.model.Hackathon;
import java.util.ArrayList;
import java.util.List;

import main.java.gui.Login;
import main.java.gui.Home;
import main.java.model.Utente;


import javax.swing.*;

public class Controller {
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";

    private Login loginView;
    private Utente authenticatedUser;
    private List<Hackathon> hackathonList = new ArrayList<>();

    public void aggiungiHackathon(Hackathon h) {
        hackathonList.add(h);
    }

    public List<Hackathon> getHackathonList() {
        return hackathonList;
    }

    public Controller() {
        this.loginView = new Login(this);
    }

    public void login(String username, String password) {
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Username o password non validi!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isAuthenticated = authenticateUser(username, password);

        if (isAuthenticated) {
            authenticatedUser = new Utente(username);
            JOptionPane.showMessageDialog(loginView, "Login effettuato per: " + username, "Successo", JOptionPane.INFORMATION_MESSAGE);
            loginView.dispose();
            new Home(this);
        } else {
            JOptionPane.showMessageDialog(loginView, "Credenziali non valide!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Utente getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void showLogin() {
        this.loginView = new Login(this);
    }

    private boolean authenticateUser(String username, String password) {
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }

    public static void main(String[] args) {
        new Controller();
    }
}