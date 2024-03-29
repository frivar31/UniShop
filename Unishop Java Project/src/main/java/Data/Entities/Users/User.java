package Data.Entities.Users;

import Data.Entities.Ticket;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
/**
 * Classe abstraite représentant un utilisateur du système.
 */
public abstract class User {
    private String firstName;
    private String lastName;
    private String email;
    private String pseudo;
    private long number;
    private ArrayList<Ticket> tickets;
    private String password;
    /**
     * Constructeur de la classe User.
     *
     * @param firstName Le prénom de l'utilisateur.
     * @param lastName  Le nom de famille de l'utilisateur.
     * @param email     L'adresse e-mail de l'utilisateur.
     * @param pseudo    Le pseudo de l'utilisateur.
     * @param number    Le numéro de téléphone de l'utilisateur.
     * @param password  Le mot de passe de l'utilisateur.
     * @param tickets   La liste des tickets associés à l'utilisateur.
     */
    @JsonCreator
    public User(@JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("email") String email,
                @JsonProperty("pseudo") String pseudo,
                @JsonProperty("number") Long number,
                @JsonProperty("password") String password,
                @JsonProperty("tickets") ArrayList<Ticket> tickets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pseudo = pseudo;
        this.number = number;
        this.password = password;
        this.tickets = tickets;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Méthode pour obtenir une représentation textuelle de l'objet User.
     *
     * @return Une chaîne de caractères représentant l'objet User.
     */
    @Override
    public String toString() {
        return "{" +
                "\n- firstName='" + firstName + '\'' +
                "\n- lastName='" + lastName + '\'' +
                "\n- email='" + email + '\'' +
                "\n- pseudo='" + pseudo + '\'' +
                "\n- number=" + number +
                "\n}";
    }

    public abstract void displayActivityStat();

    public void getNotification() {
        // TODO
    }
    /**
     * Ajoute un ticket à la liste des tickets de l'utilisateur.
     *
     * @param ticket Le ticket à ajouter.
     */
    public void addTicket(Ticket ticket) {
        getTickets().add(ticket);
    }
}
