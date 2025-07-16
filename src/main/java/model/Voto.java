package main.java.model;

/**
 * The type Voto.
 */
public class Voto {
        private Giudice giudice;
        private Team team;
        private int valore;

    /**
     * Instantiates a new Voto.
     *
     * @param giudice the giudice
     * @param team    the team
     * @param valore  the valore
     */
    public Voto(Giudice giudice, Team team, int valore) {
        if (valore < 0 || valore > 10) {
            throw new IllegalArgumentException("Il voto deve essere compreso tra 0 e 10.");
        }
            this.giudice = giudice;
            this.team = team;
            this.valore = valore;
        }

    /**
     * Gets giudice.
     *
     * @return the giudice
     */
    public Giudice getGiudice() {
        return giudice;
    }

    /**
     * Sets giudice.
     *
     * @param giudice the giudice
     */
    public void setGiudice(Giudice giudice) {
        this.giudice = giudice;
    }

    /**
     * Gets team.
     *
     * @return the team
     */
    public Team getTeam() {
            return team;
    }

    /**
     * Sets team.
     *
     * @param team the team
     */
    public void setTeam(Team team) {
            this.team = team;
    }

    /**
     * Gets valore.
     *
     * @return the valore
     */
    public int getValore() {
            return valore;
    }

    /**
     * Sets valore.
     *
     * @param valore the valore
     */
    public void setValore(int valore) {
            this.valore = valore;
    }

    /**
     * Gets punteggio.
     *
     * @return the punteggio
     */
    public int getPunteggio() {
        return valore;
    }
}