package main.java.model;

public class Voto {
        private Giudice giudice;
        private Team team;
        private int valore;

        public Voto(Giudice giudice, Team team, int valore) {
            this.giudice = giudice;
            this.team = team;
            this.valore = valore;
        }

    public Giudice getGiudice() {
        return giudice;
    }

    public void setGiudice(Giudice giudice) {
        this.giudice = giudice;
    }

    public Team getTeam() {
            return team;
    }

    public void setTeam(Team team) {
            this.team = team;
    }

    public int getValore() {
            return valore;
    }

    public void setValore(int valore) {
            this.valore = valore;
    }

}