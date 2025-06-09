package main.java.model;

/**
 * The type Invito.
 */
public class Invito {
    /**
     * The enum Stato richiesta.
     */
    public enum StatoRichiesta {
        /**
         * Pending stato richiesta.
         */
        PENDING,
        /**
         * Accepted stato richiesta.
         */
        ACCEPTED,
        /**
         * Declined stato richiesta.
         */
        DECLINED
    }

    private Partecipante mittente;
    private Partecipante destinatario;
    private Team team;
    private String messaggio;
    private StatoRichiesta stato;

    /**
     * Instantiates a new Invito.
     *
     * @param mittente     the mittente
     * @param destinatario the destinatario
     * @param team         the team
     * @param messaggio    the messaggio
     */
    public Invito(Partecipante mittente, Partecipante destinatario, Team team, String messaggio) {
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.team = team;
        this.messaggio = messaggio;
        this.stato = StatoRichiesta.PENDING;
    }

    /**
     * Accetta.
     */
    public void accetta() {
        if (team.getNumeroMembri() < team.getHackathon().getMaxPersoneInUnTeam()) {
            team.aggiungiMembro(destinatario);
            stato = StatoRichiesta.ACCEPTED;
            System.out.println(destinatario.getNome() + " ha accettato l'invito.");
        } else {
            System.out.println("Team pieno, impossibile aggiungere.");
        }
    }

    /**
     * Rifiuta.
     */
    public void rifiuta() {
        stato = StatoRichiesta.DECLINED;
        System.out.println(destinatario.getNome() + " ha rifiutato l'invito.");
    }

    /**
     * Gets stato.
     *
     * @return the stato
     */
    public StatoRichiesta getStato() {
        return stato;
    }

    /**
     * Sets stato.
     *
     * @param stato the stato
     */
    public void setStato(StatoRichiesta stato) {
        this.stato = stato;
    }

    /**
     * Gets team.
     *
     * @return the team
     */
    public Team getTeam() {
        return team;
    }
}