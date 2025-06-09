package main.java.model;

/**
 * The type Giudice.
 */
public class Giudice extends Utente {

    /**
     * Instantiates a new Giudice.
     *
     * @param id       the id
     * @param username the username
     * @param email    the email
     * @param nome     the nome
     * @param cognome  the cognome
     * @param password the password
     */
    public Giudice(int id, String username, String email, String nome, String cognome, String password) {
        super(id, username, email, nome, cognome, password);
    }

    /**
     * Selezionato.
     *
     * @param hackathon the hackathon
     */
    public void selezionato(Hackathon hackathon) {
        System.out.println("Ciao " + this.getUsername() + ", sei stato selezionato come giudice per l'hackathon: " + hackathon.getTitolo());;
    }

    /**
     * Commenta progresso.
     *
     * @param p     the p
     * @param testo the testo
     */
    public void commentaProgresso(Progresso p, String testo) {
        Commento commento = new Commento(this, testo);
        p.aggiungiCommento(commento);
        System.out.println("Commento aggiunto al progresso: " + testo);
    }
}