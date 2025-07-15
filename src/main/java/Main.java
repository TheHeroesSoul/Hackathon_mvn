package main.java;

import main.java.model.Hackathon;
import main.java.model.Problema;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Problema problema = new Problema("Costruire un'app per la gestione di hackathon");

        Hackathon hackathon = new Hackathon(
                1,
                "Hackathon Estivo",
                "Milano",
                LocalDate.of(2025, 7, 20),
                LocalDate.of(2025, 7, 22),
                100,
                5,
                LocalDate.of(2025, 6, 1),
                "admin",
                problema
        );

        System.out.println("Problema hackathon: " + hackathon.getProblema());
    }
}
