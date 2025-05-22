package main.java.controller;

import main.java.model.ModelManager;
import main.java.model.Utente;

import javax.swing.*;

public class Controller {
    private ModelManager model;

    public Controller(ModelManager model) {
        this.model = this.model;
    }

    public void login(String username) {
        Utente utente = model.findUtenteByUsername(username);
        if (utente != null) {
            JOptionPane.showMessageDialog(null, "Benvenuto " + utente.getNome());

        } else {
            JOptionPane.showMessageDialog(null, "Utente non trovato.");
        }
    }
}