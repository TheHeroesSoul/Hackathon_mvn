package main.java.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Team.
 */
public class Team {
    private int id;
    private Hackathon hackathon;
    private String nome;
    private Utente fondatore;
    private List<Utente> membri = new ArrayList<>();
    private List<Voto> voti = new ArrayList<>();
    private List<Progresso> progressi = new ArrayList<>();

    /**
     * Instantiates a new Team.
     *
     * @param id           the id
     * @param hackathon    the hackathon
     * @param nome         the nome
     * @param fondatore    the fondatore
     * @param partecipanti the partecipanti
     */
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

    public Team(String teamProva) {
        this.nome = teamProva;
        this.id = 0;
        this.hackathon = null;
        this.fondatore = null;
    }


    /**
     * Aggiungi membro.
     *
     * @param utente the utente
     */
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

    /**
     * Puo accettare boolean.
     *
     * @return the boolean
     */
    public boolean puoAccettare() {
        return membri.size() < hackathon.getMaxPersoneInUnTeam();
    }

    /**
     * Aggiungi progresso.
     *
     * @param p the p
     */
    public void aggiungiProgresso(Progresso p) {
        progressi.add(p);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets hackathon.
     *
     * @return the hackathon
     */
    public Hackathon getHackathon() {
        return hackathon;
    }

    /**
     * Gets numero membri.
     *
     * @return the numero membri
     */
    public int getNumeroMembri() {
        return membri.size();
    }

    /**
     * Gets membri.
     *
     * @return the membri
     */
    public List<Utente> getMembri() {
        return Collections.unmodifiableList(membri);
    }

    /**
     * Gets voti.
     *
     * @return the voti
     */
    public List<Voto> getVoti() {
        return voti;
    }

    /**
     * Gets punteggio totale.
     *
     * @return the punteggio totale
     */
    public int getPunteggioTotale() {
        int punteggioTotale = 0;
        for (Voto voto : voti) {
            punteggioTotale += voto.getPunteggio();
        }
        return punteggioTotale;
    }

    /**
     * Gets progressi.
     *
     * @return the progressi
     */
    public List<Progresso> getProgressi() {
        return Collections.unmodifiableList(progressi);
    }

    @Override
    public String toString() {
        return nome;
    }
}