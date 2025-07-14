package main.java.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un hackathon con informazioni su iscrizioni, partecipanti, giudici, team e documenti associati.
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
    private List<String> documenti = new ArrayList<>();

    /**
     * Costruisce un nuovo hackathon.
     *
     * @param id                 Identificativo univoco.
     * @param titolo             Titolo dell'hackathon.
     * @param sede               Luogo di svolgimento.
     * @param dataInizio         Data di inizio.
     * @param dataFine           Data di fine.
     * @param maxIscritti        Numero massimo di partecipanti.
     * @param maxPersoneInUnTeam Numero massimo di membri per team.
     * @param inizioIscrizioni   Data di apertura iscrizioni.
     * @param creatore           Username del creatore dell'hackathon.
     * @param trim
     */
    public Hackathon(int id, String titolo, String sede, LocalDate dataInizio, LocalDate dataFine,
                     int maxIscritti, int maxPersoneInUnTeam, LocalDate inizioIscrizioni,
                     String creatore, String trim) {
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
    }

    /**
     * Apre le iscrizioni se la data corrente è compresa nel periodo consentito.
     */
    public void apriIscrizioni() {
        LocalDate oggi = LocalDate.now();
        if (!iscrizioniAperte && !oggi.isBefore(inizioIscrizioni) && oggi.isBefore(dataInizio.minusDays(1))) {
            iscrizioniAperte = true;
        }
    }

    /**
     * Chiude le iscrizioni.
     */
    public void chiudiIscrizioni() {
        iscrizioniAperte = false;
    }

    /**
     * Restituisce true se le iscrizioni sono aperte.
     *
     * @return true se aperte, false altrimenti.
     */
    public boolean isIscrizioniAperte() {
        return iscrizioniAperte;
    }

    /**
     * Aggiunge un giudice se non è già presente.
     *
     * @param g giudice da aggiungere.
     */
    public void aggiungiGiudice(Utente g) {
        if (!giudici.contains(g)) {
            giudici.add(g);
        }
    }

    /**
     * Aggiunge un partecipante se non supera il limite massimo.
     *
     * @param u partecipante da aggiungere.
     * @return true se aggiunto, false altrimenti.
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
     * @param p problema da pubblicare.
     */
    public void pubblicaProblema(Problema p) {
        this.descrizioneProblema = p;
    }

    /**
     * Aggiunge un team se non si supera il limite massimo calcolato in base a iscritti e dimensione team.
     *
     * @param t team da aggiungere.
     */
    public void aggiungiTeam(Team t) {
        int maxTeams = maxIscritti / maxPersoneInUnTeam;
        if (teams.size() < maxTeams) {
            teams.add(t);
        }
    }

    /**
     * Restituisce la classifica dei team ordinata per punteggio decrescente.
     *
     * @return lista ordinata dei team.
     */
    public List<Team> getClassifica() {
        List<Team> classifica = new ArrayList<>(teams);
        classifica.sort((team1, team2) -> Integer.compare(calcolaPunteggio(team2), calcolaPunteggio(team1)));

        for (int i = 0; i < classifica.size(); i++) {
            Team team = classifica.get(i);
            System.out.println((i + 1) + ". " + team.getNome() + " - " + calcolaPunteggio(team) + " punti");
        }

        return classifica;
    }

    /**
     * Calcola il punteggio totale di un team sommando i valori dei voti ricevuti.
     *
     * @param team il team da valutare.
     * @return punteggio totale.
     */
    private int calcolaPunteggio(Team team) {
        return team.getVoti().stream()
                .mapToInt(Voto::getValore)
                .sum();
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getSede() {
        return sede;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public int getMaxIscritti() {
        return maxIscritti;
    }

    public int getMaxPersoneInUnTeam() {
        return maxPersoneInUnTeam;
    }

    public LocalDate getInizioIscrizioni() {
        return inizioIscrizioni;
    }

    public Problema getDescrizioneProblema() {
        return descrizioneProblema;
    }

    public void setDescrizioneProblema(Problema descrizioneProblema) {
        this.descrizioneProblema = descrizioneProblema;
    }

    public List<Utente> getGiudici() {
        return List.copyOf(giudici);
    }

    public void setGiudici(List<Utente> giudici) {
        this.giudici = new ArrayList<>(giudici);
    }

    public List<Utente> getPartecipanti() {
        return List.copyOf(partecipanti);
    }

    public List<Team> getTeams() {
        return List.copyOf(teams);
    }

    public String getCreatore() {
        return creatore;
    }

    public String getProblema() {
        return descrizioneProblema != null ? descrizioneProblema.getTitolo() : "Nessun problema definito";
    }

    /**
     * Aggiunge un documento all'hackathon.
     *
     * @param nomeDocumento nome del documento.
     */
    public void aggiungiDocumento(String nomeDocumento) {
        documenti.add(nomeDocumento);
    }

    /**
     * Restituisce la lista dei documenti associati.
     *
     * @return lista di nomi documenti.
     */
    public List<String> getDocumenti() {
        return List.copyOf(documenti);
    }

    /**
     * Invia un invito a un utente, se non è già iscritto o giudice.
     *
     * @param utente utente da invitare.
     * @return true se l'invito è stato inviato, false se utente già iscritto o giudice.
     */
    public boolean inviaInvitoA(Utente utente) {
        if (partecipanti.contains(utente)) {
            System.out.println("L'utente " + utente.getNome() + " è già iscritto all'hackathon.");
            return false;
        }
        if (giudici.contains(utente)) {
            System.out.println("L'utente " + utente.getNome() + " è già giudice dell'hackathon.");
            return false;
        }
        partecipanti.add(utente);
        System.out.println("Invito inviato a " + utente.getNome() + ".");
        return true;
    }

    /**
     * Verifica se un utente è invitato (partecipante o giudice).
     *
     * @param u utente da controllare.
     * @return true se invitato, false altrimenti.
     */
    public boolean isUtenteInvitato(Utente u) {
        return partecipanti.contains(u) || giudici.contains(u);
    }

    /**
     * Verifica se un utente è iscritto come partecipante.
     *
     * @param u utente da controllare.
     * @return true se iscritto, false altrimenti.
     */
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
                ", descrizioneProblema=" + (descrizioneProblema != null ? descrizioneProblema.toString() : "N/A") +
                '}';
    }
}
