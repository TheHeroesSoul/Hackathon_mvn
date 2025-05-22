package main.java.model;

import java.time.LocalDateTime;

public class Commento {
    private final Giudice autore;
    private final String testo;
    private final LocalDateTime data;

    public Commento(Giudice autore, String testo) {
        this.autore = autore;
        this.testo = testo;
        this.data = LocalDateTime.now();
    }

    public Utente getAutore() {
        return autore;
    }

    public String getTesto() {
        return testo;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Commento{" +
                "autore=" + autore.getNome() + " " + autore.getCognome() +
                ", testo='" + testo + '\'' +
                ", data=" + data +
                '}';
    }
}
