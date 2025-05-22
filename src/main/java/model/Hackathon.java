package main.java.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private List<Giudice> giudici = new ArrayList<>();
    private List<Utente> partecipanti = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private boolean iscrizioniAperte;

    public Hackathon(int id, String titolo, String sede, LocalDate dataInizio, LocalDate dataFine,
                     int maxIscritti, int maxPersoneInUnTeam, LocalDate inizioIscrizioni) {
        this.id = id;
        this.titolo = titolo;
        this.sede = sede;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.maxIscritti = maxIscritti;
        this.maxPersoneInUnTeam = maxPersoneInUnTeam;
        this.inizioIscrizioni = inizioIscrizioni;
        this.iscrizioniAperte = false;
    }

    public void apriIscrizioni() {
        if (LocalDate.now().isAfter(inizioIscrizioni.minusDays(1)) &&
                LocalDate.now().isBefore(dataInizio.minusDays(2).plusDays(1)) && !iscrizioniAperte) {
            this.iscrizioniAperte = true;
        }
    }

    public void chiudiIscrizioni() {
        this.iscrizioniAperte = false;
    }

    public boolean isIscrizioniAperte() {
        return iscrizioniAperte;
    }

    public void aggiungiGiudice(Giudice g) {
        if (!giudici.contains(g)) {
            giudici.add(g);
        }
    }

    public boolean aggiungiPartecipante(Utente u) {
        if (partecipanti.size() < maxIscritti && !partecipanti.contains(u)) {
            partecipanti.add(u);
            return true;
        }
        return false;
    }

    public void pubblicaProblema(Problema p) {
        this.descrizioneProblema = p;
    }

    public void aggiungiTeam(Team t) {
        if (teams.size() < maxIscritti / maxPersoneInUnTeam) {
            teams.add(t);
        }
    }

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


    public String getTitolo() {
        return titolo;
    }

    public int getMaxIscritti() {
        return maxIscritti;
    }

    public int getMaxPersoneInUnTeam() {
        return maxPersoneInUnTeam;
    }

    public List<Giudice> getGiudici() {
        return List.copyOf(giudici);
    }

    public List<Utente> getPartecipanti() {
        return List.copyOf(partecipanti);
    }

    public List<Team> getTeams() {
        return teams;
    }
}
