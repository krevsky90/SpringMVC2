package app.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
    @NotBlank(message = "Name is required")
    private String name;
    @Size(min = 1, max = 20, message = "Surname should be from 1 to 10 symbols")
    private String surname;
    @Email(message = "Email is incorrect")
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
