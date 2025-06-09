package main.java.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * The type Progresso.
 */
public class Progresso {
    private final Team team;
    private final LocalDateTime data;
    private final String documento;
    private final List<Commento> commentiGiudici;

    /**
     * Instantiates a new Progresso.
     *
     * @param team      the team
     * @param documento the documento
     */
    public Progresso(Team team, String documento) {
        this.team = team;
        this.data = LocalDateTime.now();
        this.documento = documento;
        this.commentiGiudici = new ArrayList<>();
    }

    /**
     * Aggiungi commento.
     *
     * @param c the c
     */
    public void aggiungiCommento(Commento c) {
        commentiGiudici.add(c);
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
     * Gets data.
     *
     * @return the data
     */
    public LocalDateTime getData() {
        return data;
    }

    /**
     * Gets documento.
     *
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Gets commenti giudici.
     *
     * @return the commenti giudici
     */
    public List<Commento> getCommentiGiudici() {
        return Collections.unmodifiableList(commentiGiudici);
    }

    @Override
    public String toString() {
        return "Progresso{" +
                "team=" + team.getNome() +
                ", data=" + data +
                ", documento='" + documento + '\'' +
                ", commenti=" + commentiGiudici.size() +
                '}';
    }
}