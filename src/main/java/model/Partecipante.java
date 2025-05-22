package main.java.model;

public class Partecipante extends Utente {

    public Partecipante(int id, String username, String email, String nome, String cognome) {
        super(id, username, email, nome, cognome);
    }

    public void accettaInvito(Invito invito) {
        if (invito.getStato() == Invito.StatoRichiesta.PENDING) {
            invito.getTeam().aggiungiMembro(this);
            invito.setStato(Invito.StatoRichiesta.ACCEPTED);
            System.out.println(this.getNome() + " ha accettato l'invito per il team " + invito.getTeam().getNome());
        } else {
            System.out.println("Impossibile accettare l'invito, stato dell'invito non valido.");
        }
    }

    public void rifiutaInvito(Invito invito) {
        if (invito.getStato() == Invito.StatoRichiesta.PENDING) {
            invito.setStato(Invito.StatoRichiesta.DECLINED);
            System.out.println(this.getNome() + " ha rifiutato l'invito per il team " + invito.getTeam().getNome());
        } else {
            System.out.println("Impossibile rifiutare l'invito, stato dell'invito non valido.");
        }
    }
}
