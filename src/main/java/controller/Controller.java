package main.java.controller;

import main.java.gui.*;
import main.java.model.*;
import main.java.model.Problema;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private Home homeView;
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";

    private Login loginView;
    private Utente authenticatedUser;
    private List<Hackathon> hackathonList = new ArrayList<>();
    private List<Utente> tuttiUtenti = new ArrayList<>();

    private Map<Team, List<Integer>> votiPerTeam = new HashMap<>();

    public Controller(boolean isAuthenticated) {
        this.loginView = new Login(this, isAuthenticated, tuttiUtenti);

        tuttiUtenti.add(new Utente(0, "admin", "admin@email.com", "Admin", "Admin", "1234"));
        tuttiUtenti.add(new Utente(1, "giudice1", "giudice1@email.com", "Mario", "Rossi", "pass1"));
        tuttiUtenti.add(new Utente(2, "giudice2", "giudice2@email.com", "Luigi", "Verdi", "pass2"));
        tuttiUtenti.add(new Utente(3, "giudice3", "giudice3@email.com", "Anna", "Bianchi", "pass3"));
        tuttiUtenti.add(new Utente(4, "partecipante1", "partecipante1@email.com", "Sara", "Neri", "pass4"));
        tuttiUtenti.add(new Utente(5, "partecipante2", "partecipante2@email.com", "Marco", "Gialli", "pass5"));
    }

    public void aggiungiHackathon(Hackathon h) {
        hackathonList.add(h);
    }

    public List<Hackathon> getHackathonList() {
        return hackathonList;
    }

    public List<Utente> getTuttiUtenti() {
        return tuttiUtenti;
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
            homeView = new Home(this, isAuthenticated);
        } else {
            JOptionPane.showMessageDialog(loginView, "Credenziali non valide!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean authenticateUser(String username, String password) {
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }

    public Utente getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void showLogin(boolean isAuthenticated) {
        this.loginView = new Login(this, isAuthenticated, tuttiUtenti);
    }

    public void mostraPaginaHackathon(Hackathon hackathon) {
        if (homeView != null) {
            homeView.nascondi();
            new PaginaHackathon(homeView.getFrame(), hackathon, this, homeView);
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
            Window parent
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

        Problema problemaObj = new Problema(problema.trim());
        Hackathon h = new Hackathon(
                0,
                titolo.trim(),
                sede.trim(),
                LocalDate.parse(dataInizio.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse(dataFine.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                Integer.parseInt(maxIscritti.trim()),
                Integer.parseInt(maxComponenti.trim()),
                LocalDate.parse(dataInizioIscrizioni.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                getAuthenticatedUser().getUsername(),
                problemaObj
        );
        aggiungiHackathon(h);

        List<Utente> utentiDisponibili = getTuttiUtenti();
        SelezionaGiudice selezionaGiudiceDialog = new SelezionaGiudice(parent, h, utentiDisponibili);
        selezionaGiudiceDialog.setVisible(true);

        JOptionPane.showMessageDialog(parent, "Hackathon creato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public boolean inviaInvito(Hackathon hackathon, String username) {
        Utente utente = trovaUtentePerNome(username);
        if (utente == null) return false;
        boolean invitato = hackathon.inviaInvitoA(utente);
        return invitato;
    }

    private Utente trovaUtentePerNome(String username) {
        return tuttiUtenti.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    public void mostraDocumentiDialog(Window parent, Hackathon hackathon) {
        new Documenti(parent, hackathon, this).setVisible(true);
    }

    public void registraVoto(Hackathon hackathon, Team team, int voto) throws Exception {
        if (hackathon == null) throw new Exception("Hackathon nullo");
        if (team == null) throw new Exception("Team non selezionato");
        if (!hackathon.getTeams().contains(team)) {
            throw new Exception("Il team non appartiene a questo hackathon");
        }
        if (voto < 1 || voto > 10) {
            throw new Exception("Il voto deve essere tra 1 e 10");
        }

        votiPerTeam.computeIfAbsent(team, k -> new ArrayList<>()).add(voto);
    }

    public List<Team> getClassificaSommaPunteggi(Hackathon hackathon) {
        if (hackathon == null) return Collections.emptyList();

        return hackathon.getTeams().stream()
                .sorted((t1, t2) -> Integer.compare(calcolaSommaVoti(t2), calcolaSommaVoti(t1)))
                .collect(Collectors.toList());
    }

    public int calcolaSommaVoti(Team team) {
        List<Integer> voti = votiPerTeam.getOrDefault(team, Collections.emptyList());
        return voti.stream().mapToInt(Integer::intValue).sum();
    }

    public List<String> getDocumentiHackathon(Hackathon hackathon) {
        if (hackathon == null) return Collections.emptyList();
        return hackathon.getDocumenti();
    }

    public String getDescrizioneProblema(Hackathon hackathon) {
        if (hackathon == null) {
            return "";
        }
        return String.valueOf(hackathon.getProblema());
    }

    public void aggiungiDocumentoAlHackathon(Hackathon hackathon, String documento) {
        if (hackathon == null) return;
        hackathon.aggiungiDocumento(documento);
    }

    public List<Team> getTeamsHackathon(Hackathon hackathon) {
        if (hackathon == null) {
            return Collections.emptyList();
        }
        return hackathon.getTeams();
    }

    public static void main(String[] args) {
        boolean isAuthenticated = false;
        new Controller(isAuthenticated);
    }
}