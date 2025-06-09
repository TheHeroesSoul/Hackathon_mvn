package main.java.model;

/**
 * The type Problema.
 */
public class Problema {
    private String descrizione;
    private Hackathon hackathon;

    /**
     * Instantiates a new Problema.
     *
     * @param descrizione the descrizione
     * @param hackathon   the hackathon
     */
    public Problema(String descrizione, Hackathon hackathon) {
        this.descrizione = descrizione;
        this.hackathon = hackathon;
    }

    /**
     * Gets descrizione.
     *
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets descrizione.
     *
     * @param descrizione the descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Gets hackathon.
     *
     * @return the hackathon
     */
    public Hackathon getHackathon() {
        return hackathon;
    }

    /**
     * Sets hackathon.
     *
     * @param hackathon the hackathon
     */
    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    @Override
    public String toString() {
        return "Problema{" +
                "descrizione='" + descrizione + '\'' +
                ", hackathon=" + hackathon.getTitolo() +
                '}';
    }
}
