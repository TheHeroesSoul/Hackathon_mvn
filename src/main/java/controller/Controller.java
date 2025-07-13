package main.java.controller;
import main.java.gui.CreazioneHackathon;
import main.java.gui.SelezionaGiudice;
import main.java.model.Giudice;
import main.java.model.Hackathon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.gui.Login;
import main.java.gui.Home;
import main.java.model.Utente;


import javax.swing.*;



public class Controller {

    private Home homeView;
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

        giudici.add(new Giudice(1, "giudice1", "giudice1@email.com", "Mario", "Rossi", "pass1"));
        giudici.add(new Giudice(2, "giudice2", "giudice2@email.com", "Luigi", "Verdi", "pass2"));
        giudici.add(new Giudice(3, "giudice3", "giudice3@email.com", "Anna", "Bianchi", "pass3"));
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
            homeView = new Home(this);
        } else {
            JOptionPane.showMessageDialog(loginView, "Credenziali non valide!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean creaHackathonDaForm(
            String titolo,
            String sede,
            String dataInizio,
            String dataFine,
            String maxIscritti,
            String maxComponenti,
            String dataInizioIscrizioni,
            String problema,
            java.awt.Window parent
    ) {

        StringBuilder missingFields = new StringBuilder();
        String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";

        if (titolo.trim().isEmpty()) missingFields.append("- Titolo\n");
        if (sede.trim().isEmpty()) missingFields.append("- Sede\n");
        if (dataInizio.trim().isEmpty()) missingFields.append("- Data Inizio\n");
        if (dataFine.trim().isEmpty()) missingFields.append("- Data Fine\n");
        if (maxIscritti.trim().isEmpty()) missingFields.append("- Max Iscritti\n");
        if (maxComponenti.trim().isEmpty()) missingFields.append("- Max Componenti Team\n");
        if (dataInizioIscrizioni.trim().isEmpty()) missingFields.append("- Data Inizio Iscrizioni\n");
        if (problema.trim().isEmpty()) missingFields.append("- Problema\n");

        if (missingFields.length() > 0) {
            JOptionPane.showMessageDialog(parent,
                    "Compila tutti i campi. Mancano:\n" + missingFields,
                    "Campi mancanti",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int maxIscr = Integer.parseInt(maxIscritti.trim());
            int maxComp = Integer.parseInt(maxComponenti.trim());
            if (maxIscr <= 0 || maxComp <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parent,
                    "Max Iscritti e Max Componenti Team devono essere numeri interi positivi.",
                    "Valore non valido",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!dataInizio.matches(datePattern) || !dataFine.matches(datePattern) || !dataInizioIscrizioni.matches(datePattern)) {
            JOptionPane.showMessageDialog(parent,
                    "Le date devono essere nel formato dd/MM/yyyy",
                    "Formato data non valido",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dInizio = sdf.parse(dataInizio);
            Date dFine = sdf.parse(dataFine);
            if (!dInizio.before(dFine)) {
                JOptionPane.showMessageDialog(parent,
                        "La Data Inizio deve essere precedente alla Data Fine",
                        "Formato data non valido",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(parent,
                    "Errore nelle date.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Hackathon h = new Hackathon(
                0,
                titolo.trim(),
                sede.trim(),
                LocalDate.parse(dataInizio.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse(dataFine.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                Integer.parseInt(maxIscritti.trim()),
                Integer.parseInt(maxComponenti.trim()),
                LocalDate.parse(dataInizioIscrizioni.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                getAuthenticatedUser().getUsername()

        );
        aggiungiHackathon(h);

        List<Giudice> giudiciDisponibili = getGiudiciDisponibili();

        SelezionaGiudice selezionaGiudiceDialog = new SelezionaGiudice(parent, h, giudiciDisponibili);
        selezionaGiudiceDialog.setVisible(true);

        JOptionPane.showMessageDialog(parent, "Hackathon creato", "Successo", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    private List<Giudice> giudici = new ArrayList<>();

    public List<Giudice> getGiudiciDisponibili() {
        return giudici;
    }

    public Utente getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void showLogin() {
        this.loginView = new Login(this);
    }

    public void mostraPaginaHackathon(Hackathon hackathon) {
        if (homeView != null) {
            homeView.nascondi();
            new main.java.gui.PaginaHackathon(homeView.getFrame(), hackathon, this, homeView);
        }
    }

    private boolean authenticateUser(String username, String password) {
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }

    public static void main(String[] args) {
        new Controller();
    }


}