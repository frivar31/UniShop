package Data.Entities.Users;

public class User {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String pseudo;
    protected long number;

    public User(String firstName,
                String lastName,
                String email,
                String pseudo,
                Long number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pseudo = pseudo;
        this.number = number;
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

    public  long getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
