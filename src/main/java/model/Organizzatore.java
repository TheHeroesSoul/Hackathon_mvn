package main.java.model;

    import java.time.LocalDate;

    /**
     * La classe Organizzatore rappresenta un utente che ha la capacità di creare hackathon.
     * Estende la classe Utente.
     */
    public class Organizzatore extends Utente {

        /**
         * Costruttore della classe Organizzatore.
         *
         * @param id        L'ID univoco dell'organizzatore.
         * @param username  Il nome utente dell'organizzatore.
         * @param email     L'indirizzo email dell'organizzatore.
         * @param nome      Il nome dell'organizzatore.
         * @param cognome   Il cognome dell'organizzatore.
         */
        public Organizzatore(int id, String username, String email, String nome, String cognome) {
            super(id, username, email, nome, cognome);
        }

        /**
         * Metodo per creare un nuovo hackathon.
         *
         * @param id                 L'ID univoco dell'hackathon.
         * @param titolo             Il titolo dell'hackathon.
         * @param sede               La sede in cui si terrà l'hackathon.
         * @param dataInizio         La data di inizio dell'hackathon.
         * @param dataFine           La data di fine dell'hackathon.
         * @param maxIscritti        Il numero massimo di partecipanti all'hackathon.
         * @param maxPersoneInUnTeam Il numero massimo di persone per team.
         * @param inizioIscrizioni   La data di inizio delle iscrizioni all'hackathon.
         * @return Un oggetto Hackathon rappresentante l'hackathon creato.
         */
        public Hackathon creaHackathon(int id, String titolo, String sede, LocalDate dataInizio, LocalDate dataFine,
                                       int maxIscritti, int maxPersoneInUnTeam, LocalDate inizioIscrizioni, String titoloProblema) {
            Problema problemaObj = new Problema(titoloProblema.trim());
            return new Hackathon(id, titolo, sede, dataInizio, dataFine, maxIscritti, maxPersoneInUnTeam,
                    inizioIscrizioni, this.getUsername(), problemaObj);
        }
    }