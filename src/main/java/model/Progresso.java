package main.java.model;

import java.time.LocalDateTime;
import java.util.*;

public class Progresso {
    private final Team team;
    private final LocalDateTime data;
    private final String documento;
    private final List<Commento> commentiGiudici;

    public Progresso(Team team, String documento) {
        this.team = team;
        this.data = LocalDateTime.now();
        this.documento = documento;
        this.commentiGiudici = new ArrayList<>();
    }

    public void aggiungiCommento(Commento c) {
        commentiGiudici.add(c);
    }

    public Team getTeam() {
        return team;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getDocumento() {
        return documento;
    }

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