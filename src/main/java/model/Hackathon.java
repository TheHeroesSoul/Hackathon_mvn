package main.java.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un hackathon con informazioni su iscrizioni, partecipanti, giudici e team.
 */
public class Hackathon {
    private int id;
    private String titolo;
    private String sede;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int maxIscritti;
    private int maxPersoneInUnTeam;
    private LocalDate inizioIscrizioni;
    private Problema descrizioneProblema;
    private List<Utente> giudici = new ArrayList<>();
    private List<Utente> partecipanti = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private boolean iscrizioniAperte;
    private String creatore;
    private String problema;

    /**
     * Crea un nuovo oggetto Hackathon.
     *
     * @param id                 identificativo univoco
     * @param titolo             titolo dell'hackathon
     * @param sede               luogo di svolgimento
     * @param dataInizio         data di inizio
     * @param dataFine           data di fine
     * @param maxIscritti        numero massimo di partecipanti
     * @param maxPersoneInUnTeam massimo membri per team
     * @param inizioIscrizioni   data di apertura iscrizioni
     * @param username
     * @param trim
     */
    public Hackathon(int id, String titolo, String sede, LocalDate dataInizio, LocalDate dataFine,
                     int maxIscritti, int maxPersoneInUnTeam, LocalDate inizioIscrizioni, String username, String trim) {
        this.id = id;
        this.titolo = titolo;
        this.sede = sede;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.maxIscritti = maxIscritti;
        this.maxPersoneInUnTeam = maxPersoneInUnTeam;
        this.inizioIscrizioni = inizioIscrizioni;
        this.creatore = creatore;
        this.iscrizioniAperte = false;
        this.problema = trim;
    }

    /**
     * Apre le iscrizioni se la data corrente è valida.
     */
    public void apriIscrizioni() {
        if (LocalDate.now().isAfter(inizioIscrizioni.minusDays(1)) &&
                LocalDate.now().isBefore(dataInizio.minusDays(2).plusDays(1)) && !iscrizioniAperte) {
            this.iscrizioniAperte = true;
        }
    }

    /**
     * Chiude le iscrizioni.
     */
    public void chiudiIscrizioni() {
        this.iscrizioniAperte = false;
    }

    /**
     * Verifica se le iscrizioni sono aperte.
     *
     * @return true se aperte, false altrimenti
     */
    public boolean isIscrizioniAperte() {
        return iscrizioniAperte;
    }

    /**
     * Aggiunge un giudice all'hackathon.
     *
     * @param g giudice da aggiungere
     */
    public void aggiungiGiudice(Giudice g) {
        if (!giudici.contains(g)) {
            giudici.add(g);
        }
    }

    /**
     * Aggiunge un partecipante se non si supera il limite.
     *
     * @param u partecipante da aggiungere
     * @return true se aggiunto, false altrimenti
     */
    public boolean aggiungiPartecipante(Utente u) {
        if (partecipanti.size() < maxIscritti && !partecipanti.contains(u)) {
            partecipanti.add(u);
            return true;
        }
        return false;
    }

    /**
     * Pubblica il problema dell'hackathon.
     *
     * @param p problema da pubblicare
     */
    public void pubblicaProblema(Problema p) {
        this.descrizioneProblema = p;
    }

    /**
     * Aggiunge un team se non si supera il limite.
     *
     * @param t team da aggiungere
     */
    public void aggiungiTeam(Team t) {
        if (teams.size() < maxIscritti / maxPersoneInUnTeam) {
            teams.add(t);
        }
    }

    /**
     * Restituisce la classifica dei team ordinata per punteggio.
     *
     * @return lista ordinata dei team
     */
    public List<Team> getClassifica() {
        List<Team> classifica = new ArrayList<>(teams);

        classifica.sort((team1, team2) -> {
            int punteggioTeam1 = calcolaPunteggio(team1);
            int punteggioTeam2 = calcolaPunteggio(team2);
            return Integer.compare(punteggioTeam2, punteggioTeam1);
        });

        System.out.println("Classifica:");
        for (int i = 0; i < classifica.size(); i++) {
            Team team = classifica.get(i);
            System.out.println((i + 1) + ". " + team.getNome() + " - " + calcolaPunteggio(team) + " punti");
        }

        return classifica;
    }

    private int calcolaPunteggio(Team team) {
        int punteggioTotale = 0;
        for (Voto voto : team.getVoti()) {
            punteggioTotale += voto.getValore();
        }
        return punteggioTotale;
    }

    public String getSede() { return sede; }
    public String getCreatore() { return creatore; }
    public String getProblema() { return problema; }

    /**
     * Gets titolo.
     *
     * @return the titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Gets max iscritti.
     *
     * @return the max iscritti
     */
    public int getMaxIscritti() {
        return maxIscritti;
    }

    /**
     * Gets max persone in un team.
     *
     * @return the max persone in un team
     */
    public int getMaxPersoneInUnTeam() {
        return maxPersoneInUnTeam;
    }

    /**
     * Gets partecipanti.
     *
     * @return the partecipanti
     */
    public List<Utente> getPartecipanti() {
        return List.copyOf(partecipanti);
    }

    /**
     * Gets teams.
     *
     * @return the teams
     */
    public List<Team> getTeams() {
        return teams;
    }

    public String getProblema(String problema) {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }


    @Override
    public String toString() {
        return "Hackathon{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", sede='" + sede + '\'' +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", maxIscritti=" + maxIscritti +
                ", maxComponenti=" + maxPersoneInUnTeam +
                ", dataInizioIscrizioni=" + inizioIscrizioni +
                ", creatore='" + creatore + '\'' +
                ", problema='" + problema + '\'' +
                '}';
    }

    // Aggiungi in Hackathon.java
    private List<String> documenti = new ArrayList<>();

    public void aggiungiDocumento(String nomeDocumento) {
        documenti.add(nomeDocumento);
    }

    public List<String> getDocumenti() {
        return documenti;
    }

    public void setGiudici(List<Utente> giudici) {
        this.giudici = giudici;
    }
    protected List<Utente> getGiudici() {
        return giudici;
    }
}
