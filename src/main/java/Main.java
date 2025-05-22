package main.java;

import main.java.controller.Controller;
import main.java.gui.Login;
import main.java.model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Organizzatore organizzatore = new Organizzatore(1, "GiovaTheFrog", "Giovanni.Rana@outlook.com", "Giovanni", "Rana");

        Hackathon hackathon = organizzatore.creaHackathon(
                1,
                "Hackathon Innovativo",
                "Napoli",
                LocalDate.of(2025, 5, 10),
                LocalDate.of(2025, 5, 12),
                10,
                5,
                LocalDate.of(2025, 4, 15)
        );
        System.out.println("Hackathon creato: " + hackathon.getTitolo());

        Giudice giudice1 = new Giudice(2, "BestChef", "PannoCarta@libero.it", "Antonino", "Cannavacciuolo");
        Giudice giudice2 = new Giudice(3, "NiceHair2010", "JB@gmail.com", "Justin", "Bieber");
        hackathon.aggiungiGiudice(giudice1);
        hackathon.aggiungiGiudice(giudice2);
        System.out.println("Giudici aggiunti: " + hackathon.getGiudici().size());

        Partecipante partecipante1 = new Partecipante(4, "Capellone", "Sanremo@alice.it", "Pippo", "Baudo");
        Partecipante partecipante2 = new Partecipante(5, "OilKing", "Johnson@yahoo.com", "Puff", "Diddy");
        Partecipante partecipante3 = new Partecipante(6, "Nostradamus", "SoTuttoIo@Mail.com", "Fabrizio", "Corona");

        hackathon.aggiungiPartecipante(partecipante1);
        hackathon.aggiungiPartecipante(partecipante2);
        hackathon.aggiungiPartecipante(partecipante3);
        System.out.println("Partecipanti registrati: " + hackathon.getPartecipanti().size());

        Team team1 = new Team(1, hackathon, "Team BRR BRR PATAPIM", partecipante1, List.of(partecipante2));
        Team team2 = new Team(2, hackathon, "Team Trallallero Trallalla", partecipante3, List.of());
        System.out.println("Team creati: " + hackathon.getTeams().size());

        Problema problema = new Problema("Sviluppare un'app per la gestione delle risorse educative.", hackathon);
        hackathon.pubblicaProblema(problema);
        System.out.println("Problema pubblicato: " + problema.getDescrizione());

        Progresso progresso1 = new Progresso(team1, "Documento iniziale del progetto.");
        team1.aggiungiProgresso(progresso1);
        System.out.println("Progresso aggiunto per " + team1.getNome());

        giudice1.commentaProgresso(progresso1, "Buona idea! Continuate così.");

        Voto voto1 = new Voto(giudice1, team1, 8);
        Voto voto2 = new Voto(giudice2, team1, 7);
        Voto voto3 = new Voto(giudice1, team2, 6);
        team1.getVoti().add(voto1);
        team1.getVoti().add(voto2);
        team2.getVoti().add(voto3);
        System.out.println("Voti assegnati.");

        List<Team> classifica = hackathon.getClassifica();
        System.out.println("Classifica finale:");
        for (int i = 0; i < classifica.size(); i++) {
            Team team = classifica.get(i);
            int punteggio = classifica.get(i).getVoti().stream().mapToInt(Voto::getValore).sum();
            System.out.println((i + 1) + ". " + team.getNome() + " - " + punteggio + " punti");
        }
    }
}
