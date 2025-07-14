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
        if (stato != StatoRichiesta.PENDING) {
            System.out.println("L'invito è già stato visualizzato.");
            return;
        }

        if (team.getNumeroMembri() < team.getHackathon().getMaxPersoneInUnTeam()) {
            team.aggiungiMembro(destinatario);
            stato = StatoRichiesta.ACCEPTED;
            System.out.println(destinatario.getNome() + " ha accettato l'invito.");
        } else {
            System.out.println("Team pieno, impossibile aggiungere altri utenti.");
        }
    }

    /**
     * Rifiuta.
     */
    public void rifiuta() {
        if (stato != StatoRichiesta.PENDING) {
            System.out.println("L'invito è già stato visualizzato.");
            return;
        }
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

    /**
     * Gets mittente.
     *
     * @return the mittente
     */
    public Partecipante getMittente() {
        return mittente;
    }

    /**
     * Gets destinatario.
     *
     * @return the destinatario
     */
    public Partecipante getDestinatario() {
        return destinatario;
    }

    /**
     * Gets messaggio.
     *
     * @return the messaggio
     */
    public String getMessaggio() {
        return messaggio;
    }

    /**
     * Sets messaggio.
     *
     * @param messaggio the messaggio
     */
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    @Override
    public String toString() {
        return "Da: " + mittente.getNome() +
                ", Per: " + destinatario.getNome() +
                ", Team: " + team.getNome() +
                ", Stato: " + stato +
                ", Messaggio: \"" + messaggio + "\"";
    }
}