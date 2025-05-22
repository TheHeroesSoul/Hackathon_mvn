package main.java.model;

import java.time.LocalDate;

public class Organizzatore extends Utente {

    public Organizzatore(int id, String username, String email, String nome, String cognome) {
        super(id, username, email, nome, cognome);
    }

    public Hackathon creaHackathon(int id, String titolo, String sede, LocalDate dataInizio, LocalDate dataFine,
                                   int maxIscritti, int maxPersoneInUnTeam, LocalDate inizioIscrizioni) {
        return new Hackathon(id, titolo, sede, dataInizio, dataFine, maxIscritti, maxPersoneInUnTeam, inizioIscrizioni);
    }
}