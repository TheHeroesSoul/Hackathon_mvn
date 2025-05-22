package main.java.model;

public class Utente {
    protected int id;
    protected String username;
    protected String email;
    protected String nome;
    protected String cognome;
    protected boolean isRegistrato;

    public Utente(int id, String username,String email, String nome, String cognome) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.isRegistrato = false;
    }

    public void registra() {
        this.isRegistrato = true;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "Utente{id=" + id + ", username='" + username + "', email='" + email + "', nome='" + nome + "', cognome='" + cognome + "'}";
    }

}