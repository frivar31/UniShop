package Data.Entities.Users;

public class UserProfile {

    private String firstName ;
    private String lastName ;
    private String email ;
    private String pseudo ;
    private int number ;

    public UserProfile (String firstName,
                 String lastName,
                 String email,
                 String pseudo,
                 int number) {
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.email = email ;
        this.pseudo = pseudo ;
        this.number = number ;
    }

    public String getFirstName() { return this.firstName ;}

    public void setFirstName(String firstName) { this.firstName = firstName ;}

    public String getLastName() { return this.lastName ;}

    public void setLastName(String lastName) { this.lastName = lastName ;}

    public String getEmail() { return this.email ;}

    public void setEmail(String email) { this.email = email ;}

    public String getPseudo() { return this.pseudo ;}

    public void setPseudo(String pseudo) { this.pseudo = pseudo ;}

    public int getNumber() { return this.number ;}

    public void setNumber(int number) { this.number = number ;}

}
