package main.java.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un Hackathon con i suoi dati principali e dinamici:
 * partecipanti, team, giudici, documenti e problema da risolvere.
 * <p>
 * Incapsula la logica relativa alle iscrizioni, alla pubblicazione del problema
 * e alla gestione della classifica.
 */
public class Hackathon {

    /** Identificativo numerico univoco dell’hackathon */
    private final int id;

    /** Titolo dell’hackathon */
    private final String titolo;

    /** Sede fisica o virtuale dell’evento */
    private final String sede;

    /** Data di inizio dell’hackathon */
    private final LocalDate dataInizio;

    /** Data di fine dell’hackathon */
    private final LocalDate dataFine;

    /** Numero massimo di partecipanti ammessi */
    private final int maxIscritti;

    /** Numero massimo di persone per team */
    private final int maxPersoneInUnTeam;

    /** Data a partire dalla quale è possibile iscriversi */
    private final LocalDate inizioIscrizioni;

    /** Username dell’organizzatore che ha creato l’hackathon */
    private final String creatore;

    /** Stato delle iscrizioni (aperte o chiuse) */
    private boolean iscrizioniAperte = false;

    /** Problema ufficiale pubblicato per l’hackathon (può essere null) */
    private Problema problema;

    /** Documenti ufficiali associati all’hackathon */
    private final List<String> documenti = new ArrayList<>();

    /** Team iscritti all’hackathon */
    private final List<Team> teams = new ArrayList<>();

    /** Partecipanti registrati */
    private final List<Utente> partecipanti = new ArrayList<>();

    /** Giudici assegnati */
    private final List<Utente> giudici = new ArrayList<>();

    /**
     * Costruisce un nuovo oggetto Hackathon.
     *
     * @param id                 identificativo univoco
     * @param titolo             titolo dell’evento
     * @param sede               sede fisica o virtuale
     * @param dataInizio         data di inizio
     * @param dataFine           data di fine
     * @param maxIscritti        numero massimo di partecipanti
     * @param maxPersoneInUnTeam numero massimo di persone per team
     * @param inizioIscrizioni   data da cui è possibile iscriversi
     * @param creatore           username dell’organizzatore
     * @param problema           problema (opzionale, può essere null)
     */
    public Hackathon(
            int id,
            String titolo,
            String sede,
            LocalDate dataInizio,
            LocalDate dataFine,
            int maxIscritti,
            int maxPersoneInUnTeam,
            LocalDate inizioIscrizioni,
            String creatore,
            Problema problema
    ) {
        this.id = id;
        this.titolo = titolo;
        this.sede = sede;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.maxIscritti = maxIscritti;
        this.maxPersoneInUnTeam = maxPersoneInUnTeam;
        this.inizioIscrizioni = inizioIscrizioni;
        this.creatore = creatore;
        this.problema = problema;
    }

    /**
     * Apre le iscrizioni se la data attuale è corretta.
     */
    
    public void apriIscrizioni() {
        LocalDate oggi = LocalDate.now();
        if (!iscrizioniAperte &&
                !oggi.isBefore(inizioIscrizioni) &&
                oggi.isBefore(dataInizio.minusDays(1))) {
            iscrizioniAperte = true;
        }
    }

    /** Chiude le iscrizioni */
    public void chiudiIscrizioni() {
        iscrizioniAperte = false;
    }

    /** @return true se le iscrizioni sono aperte */
    public boolean isIscrizioniAperte() {
        return iscrizioniAperte;
    }

    /**
     * Aggiunge un partecipante se non è già iscritto e c’è ancora posto.
     *
     * @param u utente da aggiungere
     * @return true se aggiunto con successo
     */
    public boolean aggiungiPartecipante(Utente u) {
        if (partecipanti.size() < maxIscritti && !partecipanti.contains(u)) {
            return partecipanti.add(u);
        }
        return false;
    }

    /**
     * Aggiunge un giudice se non è già presente.
     *
     * @param g giudice da aggiungere
     */
    public void aggiungiGiudice(Utente g) {
        if (!giudici.contains(g)) giudici.add(g);
    }

    /**
     * Aggiunge un team se non è stato raggiunto il numero massimo.
     *
     * @param t team da aggiungere
     */
    public void aggiungiTeam(Team t) {
        int maxTeams = maxIscritti / maxPersoneInUnTeam;
        if (teams.size() < maxTeams) teams.add(t);
    }

    /**
     * Pubblica un nuovo problema ufficiale.
     *
     * @param p problema da pubblicare
     */
    public void pubblicaProblema(Problema p) {
        this.problema = p;
    }

    /** @return problema associato, può essere null */
    public Problema getProblema() {
        return problema;
    }

    /** @return titolo sintetico del problema, o messaggio default */
    public String getProblemaTitolo() {
        return problema != null ? problema.getTitolo() : "Nessun problema definito";
    }

    /**
     * Aggiunge un documento all’hackathon.
     *
     * @param nome nome del documento
     */

    public void aggiungiDocumento(String documento) {
        if (documento != null && !documento.trim().isEmpty()) {
            documenti.add(documento.trim());
        }
    }

    /** @return lista immutabile dei documenti */
    public List<String> getDocumenti() {
        return documenti;
    }

    /**
     * Calcola e restituisce la classifica dei team ordinata per punteggio decrescente.
     *
     * @return lista dei team ordinata per punteggio
     */
    public List<Team> getClassifica() {
        List<Team> c = new ArrayList<>(teams);
        c.sort((a, b) -> Integer.compare(b.getPunteggioTotale(), a.getPunteggioTotale()));
        return c;
    }

    public int getId()                         { return id; }
    public String getTitolo()                 { return titolo; }
    public String getSede()                   { return sede; }
    public LocalDate getDataInizio()          { return dataInizio; }
    public LocalDate getDataFine()            { return dataFine; }
    public int getMaxIscritti()               { return maxIscritti; }
    public int getMaxPersoneInUnTeam()        { return maxPersoneInUnTeam; }
    public LocalDate getInizioIscrizioni()    { return inizioIscrizioni; }
    public String getCreatore()               { return creatore; }
    public List<Team> getTeams()              { return List.copyOf(teams); }
    public List<Utente> getPartecipanti()     { return List.copyOf(partecipanti); }
    public List<Utente> getGiudici()          { return List.copyOf(giudici); }

    public void setGiudici(List<Utente> selezionati) {
        if (selezionati != null) {
            this.giudici.clear();
            this.giudici.addAll(selezionati);
        } else {
            this.giudici.clear();
        }
    }

    public boolean inviaInvitoA(Utente utente) {
        if (partecipanti.contains(utente)) {
            System.out.println("Utente già iscritto: " + utente.getNome());
            return false;
        }
        if (partecipanti.size() < maxIscritti) {
            partecipanti.add(utente);
            System.out.println("Invito inviato a: " + utente.getNome());
            return true;
        } else {
            System.out.println("Hackathon pieno, impossibile inviare invito a: " + utente.getNome());
            return false;
        }
    }

    public boolean isUtenteInvitato(Utente u) {
        return partecipanti.contains(u);
    }

    public boolean isUtenteIscritto(Utente u) {
        return partecipanti.contains(u);
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
                ", maxPersoneInUnTeam=" + maxPersoneInUnTeam +
                ", inizioIscrizioni=" + inizioIscrizioni +
                ", creatore='" + creatore + '\'' +
                ", problema=" + (problema != null ? problema.getDescrizione() : "N/A") +
                '}';
    }
}