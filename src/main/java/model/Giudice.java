package main.java.model;

public class Giudice extends Utente {

    public Giudice(int id, String username, String email, String nome, String cognome) {
        super(id, username, email, nome, cognome);
    }

    public void selezionato(Hackathon hackathon) {
        System.out.println("Ciao " + this.getUsername() + ", sei stato selezionato come giudice per l'hackathon: " + hackathon.getTitolo());;
    }

    public void commentaProgresso(Progresso p, String testo) {
        Commento commento = new Commento(this, testo);
        p.aggiungiCommento(commento);
        System.out.println("Commento aggiunto al progresso: " + testo);
    }
}