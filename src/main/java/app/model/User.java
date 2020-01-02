package app.model;

public class User {
    private String name;
    private String surname;
    private String email;

    //necessary for @ModelAttribute annotation that creates new instance of User-bean and it uses default constructor and then fills fields using setters
    public User() {
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    //necessary for @ModelAttribute annotation that creates new instance of User-bean and it uses default constructor and then fills fields using setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
