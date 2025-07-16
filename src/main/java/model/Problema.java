package main.java.model;

/**
 * Rappresenta un problema da risolvere all'interno di un hackathon.
 * Ogni problema può essere associato a un oggetto {@link Hackathon}.
 */
public class Problema {

    /** Descrizione testuale del problema. */
    private String descrizione;

    /** Hackathon associato al problema (opzionale). */
    private Hackathon hackathon;

    /**
     * Costruisce un nuovo problema con la descrizione specificata.
     *
     * @param descrizione la descrizione del problema
     */
    public Problema(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce la descrizione del problema.
     *
     * @return la descrizione del problema
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione del problema.
     *
     * @param descrizione la nuova descrizione del problema
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce l'hackathon associato al problema.
     *
     * @return l 'hackathon associato oppure {@code null} se non è stato ancora assegnato
     */
    public Hackathon getHackathon() {
        return hackathon;
    }

    /**
     * Associa un hackathon al problema.
     *
     * @param hackathon l'hackathon da associare
     */
    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    /**
     * Restituisce il titolo del problema, corrispondente alla sua descrizione.
     *
     * @return la descrizione testuale del problema
     */
    public String getTitolo() {
        return descrizione;
    }

    /**
     * Restituisce una rappresentazione testuale del problema, includendo anche il titolo dell'hackathon associato,
     * se presente.
     *
     * @return descrizione del problema, seguita dal titolo dell'hackathon (se associato)
     */
    @Override
    public String toString() {
        return descrizione + (hackathon != null ? " - Hackathon: " + hackathon.getTitolo() : "");
    }
}
