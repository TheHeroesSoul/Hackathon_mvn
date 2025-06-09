package main.java.model;

import java.time.LocalDate;

/**
 * The type Organizzatore.
 */
public class Organizzatore extends Utente {

    /**
     * Instantiates a new Organizzatore.
     *
     * @param id       the id
     * @param username the username
     * @param email    the email
     * @param nome     the nome
     * @param cognome  the cognome
     * @param password the password
     */
    public Organizzatore(int id, String username, String email, String nome, String cognome, String password) {
        super(id, username, email, nome, cognome,password);
    }

    /**
     * Crea hackathon hackathon.
     *
     * @param id                 the id
     * @param titolo             the titolo
     * @param sede               the sede
     * @param dataInizio         the data inizio
     * @param dataFine           the data fine
     * @param maxIscritti        the max iscritti
     * @param maxPersoneInUnTeam the max persone in un team
     * @param inizioIscrizioni   the inizio iscrizioni
     * @return the hackathon
     */
    public Hackathon creaHackathon(int id, String titolo, String sede, LocalDate dataInizio, LocalDate dataFine,
                                   int maxIscritti, int maxPersoneInUnTeam, LocalDate inizioIscrizioni) {
        return new Hackathon(id, titolo, sede, dataInizio, dataFine, maxIscritti, maxPersoneInUnTeam, inizioIscrizioni);
    }
}