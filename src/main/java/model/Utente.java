package main.java.model;

/**
 * The type Utente.
 */
public class Utente {
    /**
     * The Id.
     */
    protected int id;
    /**
     * The Username.
     */
    protected String username;
    /**
     * The Email.
     */
    protected String email;
    /**
     * The Nome.
     */
    protected String nome;
    /**
     * The Cognome.
     */
    protected String cognome;
    /**
     * The Is registrato.
     */
    protected boolean isRegistrato;
    /**
     * The Password.
     */
    protected String password;

    /**
     * Instantiates a new Utente.
     *
     * @param username the username
     */
    public Utente(String username){
        this.username = username;
    }

    /**
     * Instantiates a new Utente.
     *
     * @param id       the id
     * @param username the username
     * @param email    the email
     * @param nome     the nome
     * @param cognome  the cognome
     * @param password the password
     */
    public Utente(int id, String username,String email, String nome, String cognome, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.isRegistrato = false;
        this.password = password;
    }

    public Utente(int id, String username,String email, String nome, String cognome) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.isRegistrato = false;
    }

    /**
     * Registra.
     */
    public void registra() {
        this.isRegistrato = true;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets cognome.
     *
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets cognome.
     *
     * @param cognome the cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username + " - " + nome + " " + cognome;
    }

}