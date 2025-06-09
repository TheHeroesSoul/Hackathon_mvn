package main.java.model;

import java.time.LocalDateTime;

/**
 * The type Commento.
 */
public class Commento {
    private final Giudice autore;
    private final String testo;
    private final LocalDateTime data;

    /**
     * Instantiates a new Commento.
     *
     * @param autore the autore
     * @param testo  the testo
     */
    public Commento(Giudice autore, String testo) {
        this.autore = autore;
        this.testo = testo;
        this.data = LocalDateTime.now();
    }

    /**
     * Gets autore.
     *
     * @return the autore
     */
    public Utente getAutore() {
        return autore;
    }

    /**
     * Gets testo.
     *
     * @return the testo
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
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
