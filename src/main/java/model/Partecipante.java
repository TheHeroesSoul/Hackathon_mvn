package main.java.model;

/**
 * The type Partecipante.
 */
public class Partecipante extends Utente {

    /**
     * Instantiates a new Partecipante.
     *
     * @param id       the id
     * @param username the username
     * @param email    the email
     * @param nome     the nome
     * @param cognome  the cognome
     * @param password the password
     */
    public Partecipante(int id, String username, String email, String nome, String cognome, String password) {
        super(id, username, email, nome, cognome, password);
    }

    /**
     * Accetta invito.
     *
     * @param invito the invito
     */
    public void accettaInvito(Invito invito) {
        if (invito.getStato() == Invito.StatoRichiesta.PENDING) {
            invito.getTeam().aggiungiMembro(this);
            invito.setStato(Invito.StatoRichiesta.ACCEPTED);
            System.out.println(this.getNome() + " ha accettato l'invito per il team " + invito.getTeam().getNome());
        } else {
            System.out.println("Impossibile accettare l'invito, stato dell'invito non valido.");
        }
    }

    /**
     * Rifiuta invito.
     *
     * @param invito the invito
     */
    public void rifiutaInvito(Invito invito) {
        if (invito.getStato() == Invito.StatoRichiesta.PENDING) {
            invito.setStato(Invito.StatoRichiesta.DECLINED);
            System.out.println(this.getNome() + " ha rifiutato l'invito per il team " + invito.getTeam().getNome());
        } else {
            System.out.println("Impossibile rifiutare l'invito, stato dell'invito non valido.");
        }
    }
}