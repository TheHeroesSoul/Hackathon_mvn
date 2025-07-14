package main.java;

import main.java.model.Hackathon;
import main.java.model.Problema;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 1. Creo l'hackathon
        Hackathon hackathon = new Hackathon(
                1,
                "Hackathon AI",
                "Milano",
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 3),
                100,
                5,
                LocalDate.of(2025, 7, 1),
                "organizzatore1",
                ""
        );

        // 2. Creo il problema (qui inserisci la descrizione)
        String descrizioneProblema = "Risolvere il problema di riconoscimento vocale usando AI.";

        Problema problema = new Problema(descrizioneProblema, hackathon);

        // 3. Pubblico il problema nell'hackathon
        hackathon.pubblicaProblema(problema);

        // (facoltativo) Stampo per verificare
        System.out.println("Problema pubblicato: " + hackathon.getDescrizioneProblema().getDescrizione());

        // 4. Mostro la GUI con il problema (se vuoi)
        main.java.gui.Problema dialog = new main.java.gui.Problema(hackathon.getDescrizioneProblema().getDescrizione());
        dialog.setVisible(true);
    }
}
