package main.java.model;

public class Invito {
    public enum StatoRichiesta {
        PENDING, ACCEPTED, DECLINED
    }

    private Partecipante mittente;
    private Partecipante destinatario;
    private Team team;
    private String messaggio;
    private StatoRichiesta stato;

    public Invito(Partecipante mittente, Partecipante destinatario, Team team, String messaggio) {
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.team = team;
        this.messaggio = messaggio;
        this.stato = StatoRichiesta.PENDING;
    }

    public void accetta() {
        if (team.getNumeroMembri() < team.getHackathon().getMaxPersoneInUnTeam()) {
            team.aggiungiMembro(destinatario);
            stato = StatoRichiesta.ACCEPTED;
            System.out.println(destinatario.getNome() + " ha accettato l'invito.");
        } else {
            System.out.println("Team pieno, impossibile aggiungere.");
        }
    }

    public void rifiuta() {
        stato = StatoRichiesta.DECLINED;
        System.out.println(destinatario.getNome() + " ha rifiutato l'invito.");
    }

    public StatoRichiesta getStato() {
        return stato;
    }

    public void setStato(StatoRichiesta stato) {
        this.stato = stato;
    }

    public Team getTeam() {
        return team;
    }
}