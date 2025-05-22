package main.java.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private int id;
    private Hackathon hackathon;
    private String nome;
    private Utente fondatore;
    private List<Utente> membri = new ArrayList<>();
    private List<Voto> voti = new ArrayList<>();
    private List<Progresso> progressi = new ArrayList<>();

    public Team(int id, Hackathon hackathon, String nome, Utente fondatore, List<Partecipante> partecipanti) {
        this.id = id;
        this.hackathon = hackathon;
        this.nome = nome;
        this.fondatore = fondatore;
        this.membri.add(fondatore);

        for (Partecipante p : partecipanti) {
            this.membri.add(p);
        }
        hackathon.aggiungiTeam(this);
    }

    public void aggiungiMembro(Partecipante utente) {
        if (utente.equals(fondatore)) {
            System.out.println(utente.getNome() + " è già fondatore di un altro team, non può essere aggiunto come membro.");
            return;
        }

        if (!membri.contains(utente)) {
            membri.add(utente);
            System.out.println(utente.getNome() + " aggiunto con successo al team " + nome);
        } else {
            System.out.println(utente.getNome() + " è già membro del team.");
        }
    }

    public boolean puoAccettare() {
        return membri.size() < hackathon.getMaxPersoneInUnTeam();
    }

    public void aggiungiProgresso(Progresso p) {
        progressi.add(p);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public int getNumeroMembri() {
        return membri.size();
    }

    public List<Utente> getMembri() {
        return Collections.unmodifiableList(membri);
    }

    public List<Voto> getVoti() {
        return voti;
    }

    public List<Progresso> getProgressi() {
        return Collections.unmodifiableList(progressi);
    }
}